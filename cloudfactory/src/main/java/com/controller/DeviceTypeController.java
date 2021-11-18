package com.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pojo.DeviceType;
import com.service.deviceType.DeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/deviceType")
public class DeviceTypeController {
    @Autowired
    private DeviceTypeService deviceTypeService;


    @RequestMapping("/list")
    @ResponseBody
    public String getDeviceTypeList(HttpServletResponse response){
        List<DeviceType> deviceTypes=deviceTypeService.getDeviceTypeList(0, 1000);
        response.setContentType("application/json");
        /*
         * resultMap = ["result","sessionerror","result",error]
         * josn格式={key,value}
         */
        String listJson= JSONArray.toJSONString(deviceTypes);
        listJson="{\n" +
                "  \"code\": 0,\n" +
                "  \"msg\": \"\",\n" +
                "  \"count\": "+deviceTypes.size()+",\n" +
                "  \"data\": "+listJson+"\n}";
        return listJson;

    }

    @RequestMapping(value="tolist")
    public String ToDeviceTypeList(){
        return "deviceTypeList";
    }

    @RequestMapping(value="typelist")
    public void getDeviceTypeList(HttpServletResponse response, HttpServletRequest request){
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
        List<DeviceType> deviceTypes=deviceTypeService.getDeviceTypeList(currentpage,pageSize);
        //模糊查询
        int count=0;
        if(name!=null){
            for(DeviceType deviceType:deviceTypes){
                if (deviceType.getTypename().contains(name)){
                    count++;
                    if (count==1){
                        deviceTypes=new ArrayList<DeviceType>();
                    }
                    deviceTypes.add(deviceType);

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
            String listJson= JSONArray.toJSONString(deviceTypes);
            listJson="{\n" +
                    "  \"code\": 0,\n" +
                    "  \"msg\": \"\",\n" +
                    "  \"count\": "+deviceTypeService.getDeviceTypeList(0,1000).size()+",\n" +
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

    @RequestMapping(value="/addDeviceType")
    @ResponseBody
    public void addDeviceType(@RequestBody JSONObject obj){
        String data=obj.toJSONString();
        JSONObject json = JSON.parseObject(data);
        String name=json.getString("name");
        String id = UUID.randomUUID().toString();
        DeviceType deviceType=new DeviceType();
        deviceType.setTypename(name);
        deviceType.setId(id);
        System.out.println(deviceType);
        if(deviceTypeService.addDeviceType(deviceType)){
            System.out.println("设备类别添加成功");
        }else {
            System.out.println("设备类别添加失败");
        }

    }

    @RequestMapping(value="deleteDeviceType")
    @ResponseBody
    public void deleteDeviceType(HttpServletRequest request){
        String id= request.getParameter("id");
        if(id!=null){
            boolean flag=deviceTypeService.deleteDeviceType(id);
            if(flag){
                System.out.println("设备类别删除成功");
            }
        }
    }

    @RequestMapping(value="/updateDeviceType")
    @ResponseBody
    public void updateDeviceType(@RequestBody JSONObject obj){
        String data=obj.toJSONString();
        JSONObject json = JSON.parseObject(data);
        String id=json.getString("id");
        String typename=json.getString("name");
        DeviceType deviceType=new DeviceType();
        deviceType.setId(id);
        deviceType.setTypename(typename);
        System.out.println(deviceType);
        if (id!=null&&typename!=null) {
            if(deviceTypeService.updateDeviceType(deviceType)){
                System.out.println("设备类别更新成功");
            }else {
                System.out.println("设备类型更新失败");
            }
        }
    }

}