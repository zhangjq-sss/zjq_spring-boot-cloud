package org.eureka.elasticsearch.dao;

import org.eureka.elasticsearch.domain.EsUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends ElasticsearchRepository<EsUser, Long>{

	EsUser findById(Long id);
}
