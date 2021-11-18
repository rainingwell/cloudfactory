package com.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pojo.Device;
import com.pojo.Product;
import com.pojo.User;
import com.service.factory.FactoryService;
import com.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FactoryService factoryService;

    @RequestMapping(value="login")
    public String login(){
        return "login";
    }
    @RequestMapping(value="Touserlist")
    public String ToUserlist(){
        return "userlist";
    }

    @RequestMapping(value="/do_login",method= RequestMethod.GET)
    public String doLogin(@RequestParam String username,@RequestParam String password, HttpServletRequest request, HttpSession session){
        User user = userService.login(username, password);
        if(user!=null){
            session.setAttribute("user", user);
            request.setAttribute("msg", "success");
            if (user.getRoleid().equals("1")){
                return "redirect:/index.jsp";
            }else if (user.getRoleid().equals("2")){
                return "redirect:/index2.jsp";
            }else if (user.getRoleid().equals("3")){
                return "redirect:/index3.jsp";
            }else {
                return "login";
            }
        }else{
            request.setAttribute("msg", "error");
            return "login";
        }
    }
    @RequestMapping(value="/adduser")
    @ResponseBody
    public void addUser(@RequestBody JSONObject obj){
        String data=obj.toJSONString();
        JSONObject json = JSON.parseObject(data);
        String account=json.getString("account");
        String username=json.getString("username");
        String password=json.getString("password");
        String phone=json.getString("phone");
        String role=json.getString("role");
        String factoryName=json.getString("factoryName");
        String factoryProfile=json.getString("factoryProfile");
        User user=new User();
        user.setAccount(account);
        user.setMobile(phone);
        user.setPassword(password);
        user.setName(username);
        if (role.equals("云工厂管理员")){
            user.setRoleid("2");
        }else if(role.equals("经销商")){
            user.setRoleid("3");
        }
//        System.out.println(user);

        if(userService.register(user,factoryName,factoryProfile)){
            System.out.println("register success");
        }else {
            System.out.println("register failure");
        }
//json array
//        JSONArray DD= JSONArray.parseArray(d);
//        for (int i=0;i<DD.size();i++){
//            int s=JSONObject.parseObject(JSONObject.toJSONString(DD.get(i))).getInteger("s");
//            System.out.println(s);
//        }
//        return "1";
    }

    @RequestMapping(value="deleteUser")
    public String deleteuser(HttpServletRequest request){
        String id= request.getParameter("id");
        if(id!=null){
            userService.deleteUser(id);
            factoryService.deleteFactory(id);
        }
        return "redirect:/index.jsp";
     }



    @RequestMapping(value="userlist")
    public void getUserList(HttpServletResponse response,HttpServletRequest request){
        String page=request.getParameter("page");
        String size=request.getParameter("size");
        String name=request.getParameter("name");
        int currentpage=1;
        int pageSize=15;
        if(page!=null){
            currentpage=Integer.parseInt(page);
        }
        if(size!=null){
            pageSize=Integer.parseInt(size);
        }
        currentpage=(currentpage-1)*pageSize;
        List<User> userList= userService.getUserList( currentpage,  pageSize);
        for(User user:userList){
            if (user.getRoleid().equals("1")){
                user.setRoleid("系统管理员");
            }
            if (user.getRoleid().equals("2")){
                user.setRoleid("云工厂管理员");
            }
            if (user.getRoleid().equals("3")){
                user.setRoleid("经销商");
            }
        }
        int count=0;
        if(name!=null){
            for(User user:userList){
                if (user.getName().contains(name)){
                    count++;
                    if (count==1){
                        userList=new ArrayList<User>();
                    }
                    userList.add(user);

                }
            }
        }
        int n=userService.getUserCount();
        Iterator<User> userIterator = userList.iterator();
        while (userIterator.hasNext()) {
            User user = userIterator .next();
            if (user.getRoleid().equals("系统管理员")){
                userIterator.remove();//这里要使用Iterator的remove方法移除当前对象，如果使用List的remove方法，则同样会出现ConcurrentModificationException
                n--;
            }
        }
        try {
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            /*
             * resultMap = ["result","sessionerror","result",error]
             * josn格式={key,value}
             */
            String listJson=JSONArray.toJSONString(userList);
            listJson="{\n" +
                    "  \"code\": 0,\n" +
                    "  \"msg\": \"\",\n" +
                    "  \"count\": "+n+",\n" +
                    "  \"data\": "+listJson+"\n}";
            writer.write(listJson);
            //writer.write(JsonArray.class.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/updateUser")
    @ResponseBody
    public void updateUser(@RequestBody JSONObject obj,HttpServletRequest request){
        String data=obj.toJSONString();
        JSONObject json = JSON.parseObject(data);
//        String id= request.getParameter("id");
        String id=json.getString("id");
        String account=json.getString("account");
        String username=json.getString("username");
        String password=json.getString("password");
        String phone=json.getString("phone");
        String role=json.getString("role");
        User user=new User();
        user.setId(id);
        user.setAccount(account);
        user.setMobile(phone);
        user.setPassword(password);
        user.setName(username);
        if (role.equals("云工厂管理员")){
            user.setRoleid("2");
        }else if(role.equals("经销商")){
            user.setRoleid("3");
        }
//        System.out.println(user);

        if(userService.updateUser(user)){
            System.out.println("updateUser success");
        }else {
            System.out.println("updateUser failure");
        }
//json array
//        JSONArray DD= JSONArray.parseArray(d);
//        for (int i=0;i<DD.size();i++){
//            int s=JSONObject.parseObject(JSONObject.toJSONString(DD.get(i))).getInteger("s");
//            System.out.println(s);
//        }
//        return "1";
    }

}
