package search.service.impl;

import cn.gydev.lib.bean.ResultBean;
import cn.gydev.lib.bean.RetCodeEnum;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
import search.beans.testjob.TestJob;
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
                TestJob testJob = gson.fromJson(gson.toJson(hit.getSourceAsMap()), TestJob.class);
                testJob.setId(hit.getId());
                FileInfo fileInfo = new FileInfo();
                fileInfo.setId(testJob.getId());
                // 摘要 截取模糊查询左右各20个字符 TODO 暂时截取40个字符展示
                String resume = "";
                if (StringUtils.isNotBlank(testJob.getContent())) {
                    resume = testJob.getContent().substring(0, 200);
                }
                fileInfo.setResume(resume);
                fileInfo.setExtension(testJob.getFile().getExtension());
                fileInfo.setFilesize(testJob.getFile().getFilesize());
                fileInfo.setFilename(testJob.getFile().getFilename());
                String fileProjectName = "";
                if (StringUtils.isNotBlank(testJob.getPath().getVirtual())) {
                    fileProjectName = testJob.getPath().getVirtual().split("/")[1];
                }
                fileInfo.setProjectName(fileProjectName);
                fileInfo.setIndexingDate(testJob.getFile().getIndexingDate());
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
}
