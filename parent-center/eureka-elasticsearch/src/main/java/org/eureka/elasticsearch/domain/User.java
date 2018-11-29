package org.eureka.elasticsearch.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Data
@Document(indexName="user", type = "t_users")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	private String userName;
	
	private String password;
	
	private String createTime;
	
	private Integer version;
	
	private String lastUpdateTime;
	
	private int deleted;
	
}
