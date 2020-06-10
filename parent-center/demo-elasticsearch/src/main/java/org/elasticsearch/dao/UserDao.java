package org.elasticsearch.dao;

import org.elasticsearch.domain.EsUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends ElasticsearchRepository<EsUser, Long>{
	Optional<EsUser> findById(Long id);
}
