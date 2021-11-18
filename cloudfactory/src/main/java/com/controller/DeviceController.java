package com.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pojo.*;
import com.service.device.DeviceService;
import com.service.deviceType.DeviceTypeService;
import com.service.factory.FactoryService;
import com.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private FactoryService factoryService;
    @Autowired
    private DeviceTypeService deviceTypeService;
    @Autowired
    private ProductService productService;

    @RequestMapping(value="tolist")
    public String ToDeviceList(){
        return "devicelist";
    }

    @RequestMapping(value="tocloudlist")
    public String ToCloudDeviceList(){
        return "clouddevice";
    }

    @RequestMapping(value="list")
    public void getDeviceList(HttpServletResponse response, HttpServletRequest request){
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
        List<Device> devices=deviceService.getDeviceList(currentpage,pageSize);
        //模糊查询
        int count=0;
        if(name!=null){
            for(Device device:devices){
                if (device.getDevicename().contains(name)){
                    count++;
                    if (count==1){
                        devices=new ArrayList<Device>();
                    }
                    devices.add(device);

                }
            }
        }
        List<Factory> factories=factoryService.getFactoryList(0,1000);
        for(Factory factory:factories){
            for(Device device:devices){
                if (device.getFactoryid()!=null&&device.getFactoryid().equals(factory.getId())){
                    device.setFactoryid(factory.getFactoryname());
                }
            }
        }
        List<DeviceType> deviceTypes=deviceTypeService.getDeviceTypeList(0, 1000);
        for (DeviceType deviceType:deviceTypes){
            for (Device device:devices){
                if (device.getTypeid()!=null&&device.getTypeid().equals(deviceType.getId())){
                    device.setTypeid(deviceType.getTypename());
                }
            }
        }

        for(Device device:devices){
            if(device.getDevicestatus().equals("0")){
                device.setDevicestatus("关闭");
            }else if (device.getDevicestatus().equals("1")){
                device.setDevicestatus("生产中");
            }else if (device.getDevicestatus().equals("2")){
                device.setDevicestatus("闲置中");
            }else if (device.getDevicestatus().equals("3")){
                device.setDevicestatus("故障");
            }
        }
        for(Device device:devices){
            if(device.getRentstatus().equals("0")){
                device.setRentstatus("自有设备");
            }else if(device.getRentstatus().equals("1")){
                device.setRentstatus("租用");
            }else if (device.getRentstatus().equals("2")){
                device.setRentstatus("空闲");
            }
        }
        try {
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            /*
             * resultMap = ["result","sessionerror","result",error]
             * josn格式={key,value}
             */
            String listJson= JSONArray.toJSONString(devices);
            listJson="{\n" +
                    "  \"code\": 0,\n" +
                    "  \"msg\": \"\",\n" +
                    "  \"count\": "+deviceService.getDeviceList(0,1000).size()+",\n" +
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

    @RequestMapping(value="list2")
    public void getDeviceList2(HttpServletResponse response, HttpServletRequest request){
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
        List<Device> devices=deviceService.getDeviceList(currentpage,pageSize);
        //模糊查询
        int count=0;
        if(name!=null){
            for(Device device:devices){
                if (device.getDevicename().contains(name)){
                    count++;
                    if (count==1){
                        devices=new ArrayList<Device>();
                    }
                    devices.add(device);

                }
            }
        }
        List<Factory> factories=factoryService.getFactoryList(0,1000);
        User user = (User) request.getSession().getAttribute("user");
//        System.out.println(user);
        String factoryid=null;
        String factoryname=null;
        int n=deviceService.getDeviceList(0,1000).size();
        if(user!=null&&user.getRoleid().equals("2")){
            for(Factory factory:factories){
                if (factory.getUserid()!=null&&factory.getUserid().equals(user.getId())){
                    factoryid=factory.getId();
                    factoryname=factory.getFactoryname();
                }
            }

            for (Device device:devices){
                if (device.getFactoryid()!=null&&device.getFactoryid().equals(factoryid)){
                    device.setFactoryid(factoryname);
                }
            }
            Iterator<Device> deviceIterator = devices.iterator();
            while (deviceIterator.hasNext()) {
                Device device = deviceIterator .next();
                if (device.getFactoryid()!=factoryname){
                    deviceIterator.remove();//这里要使用Iterator的remove方法移除当前对象，如果使用List的remove方法，则同样会出现ConcurrentModificationException
                    n--;
                }
            }
//            System.out.println(devices);
        }

//        for(Factory factory:factories){
//        }
        List<DeviceType> deviceTypes=deviceTypeService.getDeviceTypeList(0, 1000);
        for (DeviceType deviceType:deviceTypes){
            for (Device device:devices){
                if (device.getTypeid()!=null&&device.getTypeid().equals(deviceType.getId())){
                    device.setTypeid(deviceType.getTypename());
                }
            }
        }
        for(Device device:devices){
            if(device.getDevicestatus().equals("0")){
                device.setDevicestatus("关闭");
            }else if (device.getDevicestatus().equals("1")){
                device.setDevicestatus("生产中");
            }else if (device.getDevicestatus().equals("2")){
                device.setDevicestatus("闲置中");
            }else if (device.getDevicestatus().equals("3")){
                device.setDevicestatus("故障");
            }
        }
        for(Device device:devices){
            if(device.getRentstatus().equals("0")){
                device.setRentstatus("自有设备");
            }else if(device.getRentstatus().equals("1")){
                device.setRentstatus("租用");
            }else if (device.getRentstatus().equals("2")){
                device.setRentstatus("空闲");
            }
        }
        try {
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            /*
             * resultMap = ["result","sessionerror","result",error]
             * josn格式={key,value}
             */
//            System.out.println(n);
            String listJson= JSONArray.toJSONString(devices);
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


    @RequestMapping(value="rentlist")
    public void rentlist(HttpServletResponse response, HttpServletRequest request){
        String page=request.getParameter("page");
        String size=request.getParameter("size");
        int currentpage=1;
        int pageSize=15;
        if(page!=null){
            currentpage=Integer.parseInt(page);
        }
        if(size!=null){
            pageSize=Integer.parseInt(size);
        }
        currentpage=(currentpage-1)*pageSize;
        List<Device> devices=deviceService.getDeviceList(currentpage,pageSize);

        int n=deviceService.getDeviceList(0,1000).size();

        Iterator<Device> deviceIterator = devices.iterator();
        while (deviceIterator.hasNext()) {
            Device device = deviceIterator .next();
            if (device.getFactoryid()!=null&&!device.getRentstatus().equals("2")){
                deviceIterator.remove();//这里要使用Iterator的remove方法移除当前对象，如果使用List的remove方法，则同样会出现ConcurrentModificationException
                n--;
            }
        }

        List<DeviceType> deviceTypes=deviceTypeService.getDeviceTypeList(0, 1000);
        for (DeviceType deviceType:deviceTypes){
            for (Device device:devices){
                if (device.getTypeid()!=null&&device.getTypeid().equals(deviceType.getId())){
                    device.setTypeid(deviceType.getTypename());
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
//            System.out.println(n);
            String listJson= JSONArray.toJSONString(devices);
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

    @RequestMapping("/capacity")
    @ResponseBody
    public String Capacity(HttpServletResponse response,HttpServletRequest request){
        List<Capacity> capacities=deviceService.getCapacityList();
        List<Product> products=productService.getProductList(0,1000);
        String id= request.getParameter("id");
//        System.out.println(id);
        for(Capacity capacity:capacities){
            for (Product product:products){
                if (capacity.getProductid()!=null&&capacity.getProductid().equals(product.getId())){
                    capacity.setProductname(product.getProductname());
                }
            }
        }
        Iterator<Capacity> capacityIterator = capacities.iterator();
        while (capacityIterator.hasNext()) {
            Capacity capacity = capacityIterator .next();
            if (!capacity.getDeviceid().equals(id)){
                capacityIterator.remove();//这里要使用Iterator的remove方法移除当前对象，如果使用List的remove方法，则同样会出现ConcurrentModificationException
            }
        }

        response.setContentType("application/json");
        /*
         * resultMap = ["result","sessionerror","result",error]
         * josn格式={key,value}
         */
        String listJson= JSONArray.toJSONString(capacities);
        listJson="{\n" +
                "  \"code\": 0,\n" +
                "  \"msg\": \"\",\n" +
                "  \"count\": "+capacities.size()+",\n" +
                "  \"data\": "+listJson+"\n}";
        return listJson;

    }

    @RequestMapping(value="/addDevice")
    @ResponseBody
    public void addDevice(@RequestBody JSONObject obj, HttpSession session){
        String data=obj.toJSONString();
        JSONObject json = JSON.parseObject(data);
        String typeid=json.getString("Type");
        String name=json.getString("name");
        String describe=json.getString("describe");
        String norms=json.getString("norms");
        int i=(int)((Math.random()*9+1)*100000);
        String deviceno="DNO15567"+i;
        String id = UUID.randomUUID().toString();
        String devicestatus="0";
        String rentstatus=null;
        String factoryid=null;
        User user=(User)session.getAttribute("user");
        System.out.println(user);
        if (user.getRoleid()!=null&&user.getRoleid().equals("1")){
            rentstatus="2";
        }else if (user.getRoleid()!=null&&user.getRoleid().equals("2")){
            rentstatus="0";
            List<Factory> factories=factoryService.getFactoryList(0,1000);
            for (Factory factory:factories){
                if (factory.getUserid().equals(user.getId())){
                    factoryid=factory.getId();
                }
            }
        }else {
            return;
        }
        Device device=new Device();
        device.setId(id);
        device.setDescribe(describe);
        device.setDevicename(name);
        device.setDeviceno(deviceno);
        device.setNorms(norms);
        device.setRentstatus(rentstatus);
        device.setTypeid(typeid);
        device.setDevicestatus(devicestatus);
        device.setFactoryid(factoryid);
        System.out.println(device);
        if(deviceService.addDevice(device)){
            System.out.println("设备添加成功");
        }

    }

    @RequestMapping(value="deleteDevice")
    @ResponseBody
    public void deleteDevice(HttpServletRequest request){
        String id= request.getParameter("id");
        if(id!=null){
            boolean flag=deviceService.deleteDevice(id);
            if(flag){
                System.out.println("设备删除成功");
            }
        }
    }
    @RequestMapping(value="addCapacity")
    @ResponseBody
    public void addCapacity(@RequestBody JSONObject obj){
        String data=obj.toJSONString();
        JSONObject json = JSON.parseObject(data);
        String deviceid=json.getString("deviceid");
        String productidnew=json.getString("Type");
        String capacity=json.getString("capacity");
        System.out.println(deviceid);
        if(deviceid!=null&&productidnew!=null&&capacity!=null){
            boolean flag=deviceService.addCapacity(deviceid,productidnew,capacity);
            if(flag){
                System.out.println("产能添加成功");
            }
        }
    }

    @RequestMapping(value="rent")
    @ResponseBody
    public void rent(HttpServletRequest request){
        String id= request.getParameter("id");
        String rent=request.getParameter("rent");
        List<Factory> factories=factoryService.getFactoryList(0,1000);
        User user = (User) request.getSession().getAttribute("user");
//        System.out.println(user);
        String factoryid=null;
        if(user!=null&&user.getRoleid().equals("2")){
            for(Factory factory:factories){
                if (factory.getUserid()!=null&&factory.getUserid().equals(user.getId())){
                    factoryid=factory.getId();
                }
            }
        }
        if(id!=null&&rent!=null){
            boolean flag=false;
            if (rent.equals("1")){
                 flag=deviceService.rent(id,"1",factoryid);
            }else if (rent.equals("2")){
                flag=deviceService.rent(id,"2",null);
            }
            if(flag){
                System.out.println("租用操作成功");
            }
        }
    }

    @RequestMapping(value="/updateDevice")
    @ResponseBody
    public void updateDevice(@RequestBody JSONObject obj){
        String data=obj.toJSONString();
        JSONObject json = JSON.parseObject(data);
        String id=json.getString("id");
        String deviceno=json.getString("deviceno");
        String typeid=json.getString("Type");
        String name=json.getString("name");
        String describe=json.getString("describe");
        String norms=json.getString("norms");
        Device device = new Device();
        device.setTypeid(typeid);
        device.setId(id);
        device.setDescribe(describe);
        device.setNorms(norms);
        device.setDeviceno(deviceno);
        device.setDevicename(name);
        System.out.println(device);
        if (id != null && typeid != null && name != null && describe != null && norms != null){
            if(deviceService.updateDevice(device)){
                System.out.println("设备更新成功");
            }else {
                System.out.println("设备更新失败");
            }
        }
    }

    @RequestMapping(value="/updateCapaciy")
    @ResponseBody
    public void updateCapaciy(@RequestBody JSONObject obj){
        String data=obj.toJSONString();
        JSONObject json = JSON.parseObject(data);
        String deviceid=json.getString("deviceid");
        String productidnew=json.getString("Type");
        String capacity=json.getString("capacity");
        String productidold=json.getString("productid");
//        System.out.println(deviceid+productid+capacity);
        if (deviceid!=null&&productidnew!=null&&capacity!=null&&productidold!=null){
            if(deviceService.updateCapacity(deviceid,productidnew,productidold,capacity)){
                System.out.println("产能配置成功");
            }
        }
    }

    @RequestMapping(value="deleteCapacity")
    @ResponseBody
    public void deleteCapacity(HttpServletRequest request){
        String id= request.getParameter("id");
        String productid= request.getParameter("productid");
        if(id!=null){
            boolean flag=deviceService.deleteCapacity(id,productid);
            if(flag){
                System.out.println("产能删除成功");
            }
        }
    }

    @RequestMapping(value="updateDeviceStatus")
    @ResponseBody
    public void updateDeviceStatus(HttpServletRequest request){
        String id= request.getParameter("id");
        String devicestatus=request.getParameter("devicestatus");
        if(id!=null&&devicestatus!=null){
            boolean flag=deviceService.updateDeviceStatus(id,devicestatus);
            if(flag){
                System.out.println("改变设备状态成功");
            }
        }
    }

}