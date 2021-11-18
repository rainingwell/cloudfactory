package com.service.user;

import com.dao.user.UserMapper;
import com.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    @Resource
    UserMapper userMapper;
    @Override
    public boolean add(User user) {
        if (userMapper.addUser(user)>0){
           return true;
        }else{
            return false;
        }
    }

    @Override
    public User login(String account, String password) {
        return userMapper.login(account,password);
    }

    public List<User> getUserList(int currentPageNo, int pageSize){
        return userMapper.getUserList( currentPageNo,  pageSize);
    }

    @Override
    public int getUserCount() {
        if(userMapper.getUserCount()!=null) {
            return userMapper.getUserCount().size();
        }else{
            return 0;
        }
    }

    @Override
    public boolean register(User user, String factoryName,String factoryProfile) {
         if(userMapper.register(user,factoryName,factoryProfile).equals("success")){
             return true;
         }else {
             return false;
         }

    }

    @Override
    public boolean deleteUser(String id) {
        if(userMapper.delete(id)>0){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public boolean updateUser(User user) {
        if(userMapper.update(user)>0){
            return true;
        }else{
            return false;
        }
    }
}
