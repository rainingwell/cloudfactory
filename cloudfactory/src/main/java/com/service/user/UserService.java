package com.service.user;

import com.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
     boolean add(User user);

     //登录
     User login(String account,String password);

     //获取所有用户的列表
     List<User> getUserList(int pagenumber, int pageSize);

     //获取用户数量
     int getUserCount();

     //register
     boolean register(User user, String factoryName,String factoryProfile);

     boolean deleteUser(String id);

     boolean updateUser(User user);

}
