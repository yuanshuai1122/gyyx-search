package search.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import search.beans.testjob.TestJob;

/**
 * @program: gyyx-search
 * @description: 测试作业dao
 * @author: yuanshuai
 * @create: 2023-08-07 11:05
 **/
@Repository
public interface TestJobRepository extends ElasticsearchRepository<TestJob, String> {
}
