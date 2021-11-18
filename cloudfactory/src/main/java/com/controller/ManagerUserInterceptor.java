package com.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pojo.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ManagerUserInterceptor extends HandlerInterceptorAdapter{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        /*
         * 从session中获取用户信息
         */
        User user = (User) request.getSession().getAttribute("user");

        if(user == null){//如果session中没有用户的信息，跳转到登录页面，内部网页不能访问
            request.getRequestDispatcher("/user/login");
            return false;
        }else
            return true;
    }


}