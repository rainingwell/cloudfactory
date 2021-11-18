package com.controller;

import com.alibaba.fastjson.JSONArray;
import com.pojo.Device;
import com.pojo.Factory;
import com.pojo.User;
import com.service.device.DeviceService;
import com.service.factory.FactoryService;
import com.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/factory")
public class FactoryController {
    @Autowired
    private FactoryService factoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private DeviceService deviceService;

    @RequestMapping("tolist")
    private String toFactoryList(){
        return "factorylist";
    }

    @RequestMapping("list")
    private void getFactoryList(HttpServletResponse response, HttpServletRequest request){
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
        List<Factory> factories=factoryService.getFactoryList(currentpage,pageSize);
        //模糊查询
        int count=0;
        if(name!=null){
            for(Factory factory:factories){
                if (factory.getFactoryname().contains(name)){
                    count++;
                    if (count==1){
                        factories=new ArrayList<Factory>();
                    }
                    factories.add(factory);

                }
            }
        }
        for(Factory factory:factories){
            if (factory.getStatus().equals("1")){
                factory.setStatus("正常");
            }else if (factory.getStatus().equals("2")){
                factory.setStatus("关停");
            }
        }
        List<User> userList= userService.getUserList( 0,  1000);
        for (Factory factory:factories){
            for (User user:userList){
                if (factory.getUserid().equals(user.getId())){
                    factory.setUserid(user.getName());
                }
            }

        }
        try {
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            /*
             * resultMap = ["result","sessionerror","result",error]
             * josn格式={key,value}
             */
            String listJson= JSONArray.toJSONString(factories);
            listJson="{\n" +
                    "  \"code\": 0,\n" +
                    "  \"msg\": \"\",\n" +
                    "  \"count\": "+factoryService.getFactoryList(0,1000).size()+",\n" +
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

    @RequestMapping(value="changestatus")
    @ResponseBody
    public void changestatus(HttpServletRequest request){
        String id= request.getParameter("id");
        String status=request.getParameter("status");
        if(id!=null&&status!=null){
            List<Device> devices=deviceService.getDeviceList(0,1000);
            for (Device device:devices){
                if (device.getFactoryid()!=null&&device.getFactoryid().equals(id)&&status.equals("2")){
                    deviceService.updateDeviceStatus(device.getId(),"0");
                    System.out.println("强制关闭设备");
                }
            }
            boolean flag=factoryService.updateFactory(id,status);
            if(flag){
                System.out.println("改变工厂状态成功");
            }
        }
    }
}
