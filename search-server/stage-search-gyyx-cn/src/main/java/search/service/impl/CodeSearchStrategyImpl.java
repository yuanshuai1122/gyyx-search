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
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Component;
import search.beans.filelist.FileInfo;
import search.beans.filelist.FilePageList;
import search.beans.codefileinfo.CodeFileInfo;
import search.beans.vo.CodeFileDetail;
import search.enums.SearchEnums;
import search.strategy.SearchStrategy;

import java.util.ArrayList;
import java.util.Collections;

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
            keywords = "*" + keywords + "*";
            builder.query(QueryBuilders.fuzzyQuery("content", keywords));
        }
        //结果集合分页
        builder.from(pageNum-1).size(pageSize);
        //排序
        builder.sort("file.created", SortOrder.DESC);
        //搜索
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(search.getChannel());
        searchRequest.source(builder);
        try {
            // 执行请求
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            // 解析查询结果
            SearchHit[] hits = response.getHits().getHits();
            log.info("查询完整结果,response:{}", response);
            if (hits.length == 0) {
                return new ResultBean<>(RetCodeEnum.SUCCESS, "查询成功", Collections.emptyList());
            }
            FilePageList filePageList = new FilePageList();
            ArrayList<FileInfo> fileInfos = new ArrayList<>();
            for (SearchHit hit : hits) {
                CodeFileInfo codeFileInfo = gson.fromJson(gson.toJson(hit.getSourceAsMap()), CodeFileInfo.class);
                codeFileInfo.setId(hit.getId());
                FileInfo fileInfo = new FileInfo();
                fileInfo.setId(codeFileInfo.getId());
                // 摘要 截取模糊查询左右各20个字符 TODO 暂时截取40个字符展示
                String resume = "";
                if (StringUtils.isNotBlank(codeFileInfo.getContent())) {
                    resume = codeFileInfo.getContent().substring(0, 300);
                }
                fileInfo.setResume(resume);
                fileInfo.setExtension(codeFileInfo.getFile().getExtension());
                fileInfo.setFilesize(codeFileInfo.getFile().getFilesize());
                fileInfo.setFilename(codeFileInfo.getFile().getFilename());
                String fileProjectName = "";
                if (StringUtils.isNotBlank(codeFileInfo.getPath().getVirtual())) {
                    fileProjectName = codeFileInfo.getPath().getVirtual().split("/")[1];
                }
                fileInfo.setProjectName(fileProjectName);
                fileInfo.setIndexingDate(codeFileInfo.getFile().getIndexingDate());
                fileInfos.add(fileInfo);
            }
            filePageList.setFileInfos(fileInfos);
            filePageList.setPageNum(pageNum);
            filePageList.setPageSize(pageSize);
            filePageList.setTotal(response.getHits().getTotalHits().value);

            return new ResultBean<>(RetCodeEnum.SUCCESS, "查询成功", filePageList);
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
            CodeFileInfo fileInfo = gson.fromJson(gson.toJson(response.getSourceAsMap()), CodeFileInfo.class);
            fileInfo.setId(response.getId());
            CodeFileDetail fileDetail = new CodeFileDetail();
            fileDetail.setId(fileInfo.getId());
            fileDetail.setFilename(fileInfo.getFile().getFilename());
            fileDetail.setContent(fileInfo.getContent());
            fileDetail.setExtension(fileInfo.getFile().getExtension());
            fileDetail.setContentType(fileInfo.getFile().getContentType());
            fileDetail.setCreated(fileInfo.getFile().getCreated());
            fileDetail.setLastModified(fileInfo.getFile().getLastModified());
            fileDetail.setLastAccessed(fileInfo.getFile().getLastAccessed());
            fileDetail.setIndexingDate(fileInfo.getFile().getIndexingDate());
            fileDetail.setFilesize(fileInfo.getFile().getFilesize());
            String fileProjectName = "";
            if (StringUtils.isNotBlank(fileInfo.getPath().getVirtual())) {
                fileProjectName = fileInfo.getPath().getVirtual().split("/")[1];
            }
            fileDetail.setProjectName(fileProjectName);
            fileDetail.setFilePath(fileInfo.getPath().getVirtual());
            return new ResultBean<>(RetCodeEnum.SUCCESS, "查询成功", fileDetail);
        }catch (Exception e) {
            log.info("根据id查询文档详情发生异常,e:{}", e.toString());
            return new ResultBean<>(RetCodeEnum.SERVER_ERROR, "查询文档详情失败", null);
        }

    }


}
