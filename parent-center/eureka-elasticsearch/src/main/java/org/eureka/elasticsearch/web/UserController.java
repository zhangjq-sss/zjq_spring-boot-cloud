package org.eureka.elasticsearch.web;

import java.util.Date;

import org.eureka.elasticsearch.domain.User;
import org.eureka.elasticsearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zjq.euraka_core.utils.DateUtil;


@RestController
@RequestMapping("/es")
public class UserController {
	
	@Autowired
	private UserService userService;
	 /**
     * 添加
     * @return
     */
    @RequestMapping("/add")
    public String add() {
        User user = new User();
        user.setId(3l);
        user.setUserName("hello");
        user.setPassword("123456");
        user.setCreateTime(DateUtil.formatDateTime(new Date()));
        user.setDeleted(0);
        user.setVersion(1);
        user.setLastUpdateTime(DateUtil.formatDateTime(new Date()));
        userService.save(user);
        return "success";
    }
 
    /**
     * 删除
     * @return
     */
    @RequestMapping("/delete")
    public String delete() {
    	User user = new User();
        user.setId(3l);
        userService.delete(user);
        return "success";
    }
 
    /**
     * 局部更新
     * @return
     */
    @RequestMapping("/update")
    public String update() {
    	User user = userService.queryUserBYId(3l);
    	user.setUserName("word");
    	userService.update(user);
        return "success";
    }
    /**
     * 查询
     * @return
     */
    @RequestMapping("/query")
    public String query(Long id) {
    	User user = userService.queryUserBYId(id);
    	if (user==null) {
			return "该id不存在！";
		}
    	String usernfo = user.toString();
        return usernfo;
    }
}
