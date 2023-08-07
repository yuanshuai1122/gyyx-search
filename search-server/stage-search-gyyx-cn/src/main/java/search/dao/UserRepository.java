package search.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import search.beans.User;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {
}
