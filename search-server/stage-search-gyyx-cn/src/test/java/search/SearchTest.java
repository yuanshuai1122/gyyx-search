package search;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @program: gyyx-search
 * @description: 查询测试
 * @author: yuanshuai
 * @create: 2023-08-08 12:46
 **/
@SpringBootTest(classes = SearchMainApplication.class)
public class SearchTest {

    @Autowired
    private RestHighLevelClient client;

    /**
     * 模糊查询，支持通配符
     * @throws IOException
     */
    @Test
    public void search5() throws IOException {
        // 创建请求
        SearchSourceBuilder builder = new SearchSourceBuilder()
                .query(QueryBuilders.wildcardQuery("content","测试"));

        //搜索
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("test-job");
        searchRequest.source(builder);
        // 执行请求
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        // 解析查询结果
        System.out.println(response.toString());
    }

    /**
     * 单条件精确查询
     * @throws IOException
     */
    @Test
    public void search0() throws IOException {
        // 创建请求
        SearchSourceBuilder builder = new SearchSourceBuilder()
                .query(QueryBuilders.termsQuery("file.extension", "js"));

        //搜索
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("test-job");
        searchRequest.types("_doc");
        searchRequest.source(builder);
        // 执行请求
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        // 解析查询结果
        System.out.println(response.toString());
    }

}
