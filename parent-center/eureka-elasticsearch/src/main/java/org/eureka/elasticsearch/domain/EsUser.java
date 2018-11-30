package org.eureka.elasticsearch.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.zjq.euraka_core.utils.DateUtil;
import com.zjq.euraka_domain.user.User;

import lombok.Data;

@Data
@Document(indexName="user", type = "t_users")
public class EsUser implements Serializable{
	
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
	
	public static EsUser getEsUser(User user) {
		EsUser esUser = new EsUser();
		esUser.setId(user.getId().longValue());
		esUser.setCreateTime(DateUtil.formatDateTime(user.getCreateTime()));
		esUser.setLastUpdateTime(DateUtil.formatDateTime(user.getLastUpdateTime()));
		esUser.setDeleted(user.getDeleted()==true?0:1);
		esUser.setPassword(user.getPassword());
		esUser.setUserName(user.getUserName());
		esUser.setVersion(user.getVersion());
		return esUser;
	}
	
}
