package com.dao;

import com.dao.user.UserMapper;
import com.pojo.User;
import com.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
//@Service
public class UserDaoTest {
//    @Resource
//    private UserMapper userMapper;
    @Test
    public void testRegister(){

        User user=new User();
        user.setId("5");
        user.setAccount("77661332");
        user.setPassword("77661332");
        user.setName("GG");
        user.setMobile("15252030061");
        user.setRoleid("2");
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
        UserMapper userMapper = (UserMapper) act.getBean(UserMapper.class);
        System.out.println(userMapper);
        userMapper.addUser(user);
//        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
//        userMapper=app.getBean(UserMapper.class);
//        System.out.println(userMapper.register(user,"GG","GG"));
//        System.out.println(app.getBean(UserMapper.class));
    }

    @Test
    public void testLogin(){
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
        UserMapper userMapper = (UserMapper) act.getBean(UserMapper.class);
        System.out.println(userMapper);
        User user=userMapper.login("77664","7766");
        System.out.println(user);
    }
    @Test
    public void testlist(){
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
        UserMapper userMapper = (UserMapper) act.getBean(UserMapper.class);
        System.out.println(userMapper);
        List<User> users=userMapper.getUserList(8,9);
        System.out.println(users.size());
    }

    @Test
    public void testCount(){
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
        UserMapper userMapper = (UserMapper) act.getBean(UserMapper.class);
        System.out.println(userMapper.getUserCount());
    }
}
