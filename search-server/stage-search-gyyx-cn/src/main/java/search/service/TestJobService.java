package search.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;
import search.beans.testjob.TestJob;
import search.dao.TestJobRepository;

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

}
