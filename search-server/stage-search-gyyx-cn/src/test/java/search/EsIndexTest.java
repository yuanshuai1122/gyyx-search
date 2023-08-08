package search;

import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsRequest;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsResponse;
import org.elasticsearch.client.GetAliasesResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.cluster.metadata.AliasMetadata;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @program: gyyx-search
 * @description: es索引测试类
 * @author: yuanshuai
 * @create: 2023-08-08 12:30
 **/
@SpringBootTest(classes = SearchMainApplication.class)
public class EsIndexTest {

    @Autowired
    private RestHighLevelClient client;

    /**
     * 查询索引
     * @throws IOException
     */
    @Test
    public void getIndex() throws IOException {
        // 创建请求
        GetIndexRequest request = new GetIndexRequest();
        request.indices("test-job");
        // 执行请求,获取响应
        GetIndexResponse response = client.indices().get(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());
    }

    /**
     * 检查索引是否存在
     * @throws IOException
     */
    @Test
    public void exists() throws IOException {
        // 创建请求
        GetIndexRequest request = new GetIndexRequest();
        request.indices("test-job");
        // 执行请求,获取响应
        boolean response = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }


    /**
     * 查询所有的索引名称
     * @throws IOException
     */
    @Test
    public void getAllIndices() throws IOException {
        GetAliasesRequest request = new GetAliasesRequest();
        GetAliasesResponse response =  client.indices().getAlias(request,RequestOptions.DEFAULT);
        Map<String, Set<AliasMetadata>> map = response.getAliases();
        Set<String> indices = map.keySet();
        for (String key : indices) {
            System.out.println(key);
        }
    }

    /**
     * 查询索引映射字段
     * @throws IOException
     */
    @Test
    public void getMapping() throws IOException {
        GetMappingsRequest request = new GetMappingsRequest();
        request.indices("test-job");
        request.types("_doc");
        GetMappingsResponse response = client.indices().getMapping(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());
    }

}
