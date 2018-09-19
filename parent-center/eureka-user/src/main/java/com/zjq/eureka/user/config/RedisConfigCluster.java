package com.zjq.eureka.user.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

//@Configuration
//@EnableAutoConfiguration
public class RedisConfigCluster {
	
	@Value("${spring.redis.cluster.nodes}")
	private List<String> nodes;

	@Bean
    @ConfigurationProperties(prefix = "spring.redis.pool")
    public RedisClusterConfiguration getRedisConfig(){
		System.out.println(nodes);
		RedisClusterConfiguration config = new RedisClusterConfiguration(nodes);
        return config;
    }
	
    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisConnectionFactory getConnectionFactory() {
    	JedisConnectionFactory factory = new JedisConnectionFactory(getRedisConfig());
        factory.setUsePool(true);
        return factory;
    }

    @Bean
    public RedisTemplate<?, ?> getRedisTemplate() {
        JedisConnectionFactory factory = getConnectionFactory();
        RedisTemplate<?, ?> template = new StringRedisTemplate(factory);
        return template;
    }

}