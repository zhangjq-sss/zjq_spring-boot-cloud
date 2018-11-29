package org.eureka.elasticsearch.dao;

import org.eureka.elasticsearch.domain.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends ElasticsearchRepository<User, Long>{

	User findById(Long id);
}
