package com.mydemo.zookeeper.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mydemo.zookeeper.model.user.User;
import com.mydemo.zookeeper.service.UserService;

public class UserTest {

	private UserService userService;
    
    @Before
    public void before(){                                                                   
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/mysql/spring.xml"
                ,"classpath:conf/mysql/spring-mybaitis.xml"});
        try {
			userService = (UserService) context.getBean("userServiceImpl");
		} catch (BeansException e) {
			e.printStackTrace();
		}
        if(userService == null){
        	System.err.println("can't find bean userServiceImpl");
        	userService = (UserService)context.getBean("userService");
        	if(userService == null){
            	System.err.println("can't find bean userService");
        	}else{
        		System.err.println("can find bean userService");
        	}
        }
    }
     
    @Test
    public void addUser(){
    	String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        User user = new User();
        user.setNickName("hello zookeeper-dubbo-service "+date);
        user.setState(2);
        System.out.println(userService.insertUser(user));
        while(true){
        	// 模拟项目启动状态
        }
    }
}
