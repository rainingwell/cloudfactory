package com.dao.user;


import com.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserMapper {


     List<User> getUserList(@Param("currentPageNo")int currentPageNo,@Param("pageSize") int pageSize);

     User login(@Param("account") String account,@Param("password") String password);

     String register(@Param("user") User user,@Param("factory_name")String factoryName,@Param("factory_profile")String factoryProfile);

     int addUser(User user);

     int delete(String id);

    List<User> getUserCount();

     int update(User user);

    List<User> getUserListById(@Param("id") String id);
    }

