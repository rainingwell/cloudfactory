package com.service;

import com.dao.user.UserMapper;
import com.pojo.User;
import com.service.user.UserService;
import com.service.user.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class userServiceTest {
    @Test
    public void Test(){
        User user=new User();
        user.setId("5");
        user.setAccount("77661332");
        user.setPassword("77661332");
        user.setName("GG");
        user.setMobile("15252030061");
        user.setRoleid("2");
        UserService userService=new UserServiceImpl();
        userService.add(user);
//        ApplicationContext app=new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
//        System.out.println(app.getBean(UserMapper.class));
    }

    @Test
    public void Test1(){
        UserService userService=new UserServiceImpl();
        System.out.println(userService.getUserCount());
//        ApplicationContext app=new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
//        System.out.println(app.getBean(UserMapper.class));
    }
}
