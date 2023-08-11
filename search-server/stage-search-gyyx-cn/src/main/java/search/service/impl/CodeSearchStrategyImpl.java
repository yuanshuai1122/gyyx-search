package search.service.impl;

import cn.gydev.lib.bean.ResultBean;
import cn.gydev.lib.bean.RetCodeEnum;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Component;
import search.beans.codedocinfo.CodeDocInfo;
import search.beans.filelist.CodeDocConstant;
import search.beans.filelist.DocPageList;
import search.beans.vo.CodeDocDetail;
import search.enums.SearchEnums;
import search.strategy.SearchStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * @program: gyyx-search
 * @description: 代码搜索策略实现
 * @author: yuanshuai
 * @create: 2023-08-08 09:59
 **/
@Component("codeSearchStrategy")
@RequiredArgsConstructor
@Slf4j
public class CodeSearchStrategyImpl implements SearchStrategy {

    private final RestHighLevelClient client;
    Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    @Override
    public ResultBean<Object> searchList(String channel, String keywords, String extension, String projectName, Integer pageNum, Integer pageSize) {
        // 根据渠道获取索引列表
        SearchEnums search = SearchEnums.search(channel);
        if (null == search) {
            return new ResultBean<>(RetCodeEnum.PARAM_ERROR, "搜索渠道有误", null);
        }

        // 创建请求
        SearchSourceBuilder builder = new SearchSourceBuilder();
        // 文件后缀
        if (StringUtils.isNotBlank(extension)) {
            builder.query(QueryBuilders.matchQuery("file.extension", extension));
        }
        // 项目名称
        if (StringUtils.isNotBlank(projectName)) {

        }
        // 模糊查询
        if (StringUtils.isNotBlank(keywords)) {
            builder.query(QueryBuilders.matchQuery("content", keywords)
                    .fuzziness(Fuzziness.AUTO)
                    .analyzer("ik_smart"));
        }
        //结果集合分页
        builder.from(pageNum-1).size(pageSize);
        //排序
        builder.sort("file.created", SortOrder.DESC);
        //搜索
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(search.getChannel());
        searchRequest.source(builder);
        searchRequest.source().highlighter(new HighlightBuilder()
                .field("content")
                // 是否需要与查询字段匹配
                .requireFieldMatch(false)
        );
        try {
            // 执行请求
            log.info("分页查询文档列表开始，searchRequest:{}", searchRequest);
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            // 解析查询结果
            SearchHit[] hits = response.getHits().getHits();
            log.info("查询完整结果,response:{}", response);
            if (hits.length == 0) {
                return new ResultBean<>(RetCodeEnum.SUCCESS, "查询成功", Collections.emptyList());
            }
            DocPageList docPageList = new DocPageList();
            ArrayList<CodeDocConstant> codeDocConstants = new ArrayList<>();
            for (SearchHit hit : hits) {
                CodeDocInfo codeDocInfo = gson.fromJson(gson.toJson(hit.getSourceAsMap()), CodeDocInfo.class);
                codeDocInfo.setId(hit.getId());
                CodeDocConstant codeDocConstant = new CodeDocConstant();
                codeDocConstant.setId(codeDocInfo.getId());
                codeDocConstant.setResume("");
                if (StringUtils.isNotBlank(keywords)) {
                    // 关键字不为空 设置字段高亮
                    Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                    if (!highlightFields.isEmpty()) {
                        HighlightField highlightField = highlightFields.get("content");
                        if (null != highlightField) {
                            String high = highlightField.getFragments()[0].string();
                            log.info("高亮字段, high:{}", high);
                            codeDocConstant.setResume(high);
                        }
                    }
                }else {
                    // 关键字为空 截取字段
                    String resume = "";
                    if (StringUtils.isNotBlank(codeDocInfo.getContent())) {
                        resume = codeDocInfo.getContent().substring(0, Math.min(codeDocInfo.getContent().length(), 300));
                    }
                    codeDocConstant.setResume(resume);
                }
                codeDocConstant.setExtension(codeDocInfo.getFile().getExtension());
                codeDocConstant.setFilesize(codeDocInfo.getFile().getFilesize());
                codeDocConstant.setFilename(codeDocInfo.getFile().getFilename());
                String fileProjectName = "";
                if (StringUtils.isNotBlank(codeDocInfo.getPath().getVirtual())) {
                    fileProjectName = codeDocInfo.getPath().getVirtual().split("/")[1];
                }
                codeDocConstant.setProjectName(fileProjectName);
                codeDocConstant.setIndexingDate(codeDocInfo.getFile().getIndexingDate());
                codeDocConstants.add(codeDocConstant);
            }
            docPageList.setCodeDocConstants(codeDocConstants);
            docPageList.setPageNum(pageNum);
            docPageList.setPageSize(pageSize);
            docPageList.setTotal(response.getHits().getTotalHits().value);

            return new ResultBean<>(RetCodeEnum.SUCCESS, "查询成功", docPageList);
        }catch (Exception e) {
            log.error("查询文件列表出现异常, e:{}", e.toString());
            return new ResultBean<>(RetCodeEnum.SERVER_ERROR, "查询信息列表失败", null);
        }

    }

    @Override
    public ResultBean<Object> searchInfo(String channel, String id) {
        // 根据渠道获取索引列表
        SearchEnums search = SearchEnums.search(channel);
        if (null == search) {
            return new ResultBean<>(RetCodeEnum.PARAM_ERROR, "搜索渠道有误", null);
        }
        // 创建索引,即获取索引
        GetRequest request = new GetRequest();
        // 外层参数
        request.id(id);
        request.index(search.getChannel());
        try {
            // 发送请求
            log.info("根据id查询文档详情开始发送请求，request:{}", request);
            GetResponse response = client.get(request, RequestOptions.DEFAULT);
            // 反序列化
            CodeDocInfo fileInfo = gson.fromJson(gson.toJson(response.getSourceAsMap()), CodeDocInfo.class);
            fileInfo.setId(response.getId());
            CodeDocDetail docDetail = new CodeDocDetail();
            docDetail.setId(fileInfo.getId());
            docDetail.setFilename(fileInfo.getFile().getFilename());
            docDetail.setContent(fileInfo.getContent());
            docDetail.setExtension(fileInfo.getFile().getExtension());
            docDetail.setContentType(fileInfo.getFile().getContentType());
            docDetail.setCreated(fileInfo.getFile().getCreated());
            docDetail.setLastModified(fileInfo.getFile().getLastModified());
            docDetail.setLastAccessed(fileInfo.getFile().getLastAccessed());
            docDetail.setIndexingDate(fileInfo.getFile().getIndexingDate());
            docDetail.setFilesize(fileInfo.getFile().getFilesize());
            String fileProjectName = "";
            if (StringUtils.isNotBlank(fileInfo.getPath().getVirtual())) {
                fileProjectName = fileInfo.getPath().getVirtual().split("/")[1];
            }
            docDetail.setProjectName(fileProjectName);
            docDetail.setFilePath(fileInfo.getPath().getVirtual());
            return new ResultBean<>(RetCodeEnum.SUCCESS, "查询成功", docDetail);
        }catch (Exception e) {
            log.info("根据id查询文档详情发生异常,e:{}", e.toString());
            return new ResultBean<>(RetCodeEnum.SERVER_ERROR, "查询文档详情失败", null);
        }

    }


}
