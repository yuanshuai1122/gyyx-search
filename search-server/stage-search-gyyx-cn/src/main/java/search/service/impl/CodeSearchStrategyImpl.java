package search.service.impl;

import cn.gydev.lib.bean.ResultBean;
import cn.gydev.lib.bean.RetCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Component;
import search.enums.SearchEnums;
import search.strategy.SearchStrategy;

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
            builder.query(QueryBuilders.matchQuery("file.extension", "张王"));
        }
        // 项目名称
        if (StringUtils.isNotBlank(projectName)) {

        }
        // 模糊查询

        //结果集合分页
        builder.from(pageNum).size(pageSize);
        //排序
        builder.sort("file.created", SortOrder.ASC);
        //搜索
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(search.getChannel());
        searchRequest.source(builder);
        try {
            // 执行请求
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            // 解析查询结果
            return new ResultBean<>(RetCodeEnum.SUCCESS, "查询成功", response);
        }catch (Exception e) {
            log.error("查询文件列表出现异常, e:{}", e.toString());
            return new ResultBean<>(RetCodeEnum.SERVER_ERROR, "查询信息列表失败", null);
        }

    }
}
