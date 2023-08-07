package search.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;
import search.beans.testJob.TestJob;
import search.dao.TestJobRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: gyyx-search
 * @description: 测试作业服务
 * @author: yuanshuai
 * @create: 2023-08-07 11:06
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class TestJobService {


    private final TestJobRepository testJobRepository;

    private final RestHighLevelClient elasticsearchClient;

    Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();


    public Iterable<TestJob> getAll() {
        return testJobRepository.findAll();
    }



    public List<TestJob> searchAllData(String indexName) {

        try {
            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(QueryBuilders.matchAllQuery());
            searchRequest.source(sourceBuilder);

            SearchResponse searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);
            List<TestJob> testJobs = new ArrayList<>();
            for (SearchHit hit : searchResponse.getHits().getHits()) {
                TestJob testJob = gson.fromJson(gson.toJson(hit.getSourceAsMap()), TestJob.class);
                testJob.setId(hit.getId());
                //TestJob testJob = JsonUtils.jsonToObj(JsonUtils.objToJson(hit.getSourceAsMap()), TestJob.class);
                testJobs.add(testJob);
            }
            return testJobs;

        }catch (Exception e) {
            log.error("e:{}", e.toString());
            return null;
        }

    }

}
