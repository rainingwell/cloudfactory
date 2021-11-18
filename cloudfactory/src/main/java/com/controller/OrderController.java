package com.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pojo.*;
import com.service.factory.FactoryService;
import com.service.order.OrderService;
import com.service.product.ProductService;
import com.service.user.UserService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private FactoryService factoryService;

    @RequestMapping("/tolist")
    public String toOrderList(){
        return "orderlist";
    }

    @RequestMapping("/tolist2")
    public String toOrderList2(){
        return "orderlist2";
    }

    @RequestMapping("/tolist3")
    public String toOrderList3(){
        return "orderlist3";
    }

    @RequestMapping("/tolist4")
    public String toOrderList4(){
        return "orderlist4";
    }

    @RequestMapping("/list")
    @ResponseBody
    public void getOrderList(HttpServletResponse response, HttpServletRequest request){
        String page=request.getParameter("page");
        String size=request.getParameter("size");
        String orderno=request.getParameter("name");
        System.out.println(orderno);
        int currentpage=1;
        int pageSize=15;
        if(page!=null){
            currentpage=Integer.parseInt(page);
        }
        if(size!=null){
            pageSize=Integer.parseInt(size);
        }
        currentpage=(currentpage-1)*pageSize;
        List<Order> orders=orderService.getOrderList(currentpage,pageSize);
        //模糊查询
        int count=0;
        if(orderno!=null){
            for(Order order:orders){
                if (order.getOrderno().contains(orderno)){
                    count++;
                    if (count==1){
                        orders=new ArrayList<Order>();
                    }
                    orders.add(order);

                }
            }
        }
        List<User> userList= userService.getUserList( 0,  1000);
        List<Product> products= productService.getProductList( 0,  1000);
        for(Order order:orders){
            for (User user: userList){
                if(user.getId().equals(order.getUserid())){
                    order.setUserid(user.getName());
                }
            }
            for(Product product:products){
                if (product.getId().equals(order.getProductid())){
                    order.setProductid(product.getProductname());
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
            String listJson= JSONArray.toJSONString(orders);
            listJson="{\n" +
                    "  \"code\": 0,\n" +
                    "  \"msg\": \"\",\n" +
                    "  \"count\": "+orderService.getOrderList(0,1000).size()+",\n" +
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

    @RequestMapping("/list2")
    @ResponseBody
    public void getOrderList2(HttpServletResponse response, HttpServletRequest request){
        String page=request.getParameter("page");
        String size=request.getParameter("size");
        String orderno=request.getParameter("name");
        System.out.println(orderno);
        int currentpage=1;
        int pageSize=15;
        if(page!=null){
            currentpage=Integer.parseInt(page);
        }
        if(size!=null){
            pageSize=Integer.parseInt(size);
        }
        currentpage=(currentpage-1)*pageSize;
        List<Order> orders=orderService.getOrderList(currentpage,pageSize);
        //模糊查询
        int count=0;
        if(orderno!=null){
            for(Order order:orders){
                if (order.getOrderno().contains(orderno)){
                    count++;
                    if (count==1){
                        orders=new ArrayList<Order>();
                    }
                    orders.add(order);

                }
            }
        }
        List<User> userList= userService.getUserList( 0,  1000);
        List<Product> products= productService.getProductList( 0,  1000);
        User user = (User) request.getSession().getAttribute("user");
        int n=orderService.getOrderList(0,1000).size();
        if (user!=null){
            Iterator<Order> orderIterator = orders.iterator();
            while (orderIterator.hasNext()) {
                Order order = orderIterator .next();
                if (!order.getUserid().equals(user.getId())){
                    orderIterator.remove();//这里要使用Iterator的remove方法移除当前对象，如果使用List的remove方法，则同样会出现ConcurrentModificationException
                    n--;
                }
            }
            for(Order order:orders){
                for(Product product:products){
                    if (product.getId().equals(order.getProductid())){
                        order.setProductid(product.getProductname());
                    }
                }
                order.setUsername(user.getName());
                if(order.getOrderstatus().equals("1")){
                    order.setOrderstatus("已保存");
                }else if (order.getOrderstatus().equals("2")){
                    order.setOrderstatus("已发布");
                }else if (order.getOrderstatus().equals("3")){
                    order.setOrderstatus("投标结束");
                }else if (order.getOrderstatus().equals("4")){
                    order.setOrderstatus("已发货");
                }else if (order.getOrderstatus().equals("5")){
                    order.setOrderstatus("订单完成");
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
            String listJson= JSONArray.toJSONString(orders);
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


    @RequestMapping("/list3")
    @ResponseBody
    public void getOrderList3(HttpServletResponse response, HttpServletRequest request){
        String page=request.getParameter("page");
        String size=request.getParameter("size");
        String orderno=request.getParameter("name");
        System.out.println(orderno);
        int currentpage=1;
        int pageSize=15;
        if(page!=null){
            currentpage=Integer.parseInt(page);
        }
        if(size!=null){
            pageSize=Integer.parseInt(size);
        }
        currentpage=(currentpage-1)*pageSize;
        List<Order> orders=orderService.getOrderList(currentpage,pageSize);
        //模糊查询
        int count=0;
        if(orderno!=null){
            for(Order order:orders){
                if (order.getOrderno().contains(orderno)){
                    count++;
                    if (count==1){
                        orders=new ArrayList<Order>();
                    }
                    orders.add(order);

                }
            }
        }
        List<User> userList= userService.getUserList( 0,  1000);
        List<Product> products= productService.getProductList( 0,  1000);
        User user = (User) request.getSession().getAttribute("user");
        List<Bid> bids=orderService.getAllBidList();
        String factoryid=null;
        List<Factory> factories=factoryService.getFactoryList(0,1000);
        int n=orderService.getOrderList(0,1000).size();
        Iterator<Order> orderIterator = orders.iterator();
        if (user!=null){
            for(Factory factory:factories){
                if (factory.getUserid().equals(user.getId())){
                    factoryid=factory.getId();
                }
            }
//            for(Bid bid:bids) {
//                if (bid.getFactoryid().equals(factoryid)){
                    while (orderIterator.hasNext()) {
                        Order order = orderIterator.next();
                        if (!order.getOrderstatus().equals("2")) {
                            orderIterator.remove();//这里要使用Iterator的remove方法移除当前对象，如果使用List的remove方法，则同样会出现ConcurrentModificationException
                            n--;
                        }
                    }
//                }
//            }
            for(Order order:orders){
                for(Product product:products){
                    if (product.getId().equals(order.getProductid())){
                        order.setProductid(product.getProductname());
                    }

                }
                order.setUsername(user.getName());
                if(order.getOrderstatus().equals("1")){
                    order.setOrderstatus("已保存");
                }else if (order.getOrderstatus().equals("2")){
                    order.setOrderstatus("已发布");
                }else if (order.getOrderstatus().equals("3")){
                    order.setOrderstatus("投标结束");
                }else if (order.getOrderstatus().equals("4")){
                    order.setOrderstatus("已发货");
                }else if (order.getOrderstatus().equals("5")){
                    order.setOrderstatus("订单完成");
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
            String listJson= JSONArray.toJSONString(orders);
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

    @RequestMapping("/list4")
    @ResponseBody
    public void getOrderList4(HttpServletResponse response, HttpServletRequest request){
        String page=request.getParameter("page");
        String size=request.getParameter("size");
        String orderno=request.getParameter("name");
        System.out.println(orderno);
        int currentpage=1;
        int pageSize=15;
        if(page!=null){
            currentpage=Integer.parseInt(page);
        }
        if(size!=null){
            pageSize=Integer.parseInt(size);
        }
        currentpage=(currentpage-1)*pageSize;
        List<Order> orders=orderService.getOrderList(currentpage,pageSize);
        //模糊查询
        int count=0;
        if(orderno!=null){
            for(Order order:orders){
                if (order.getOrderno().contains(orderno)){
                    count++;
                    if (count==1){
                        orders=new ArrayList<Order>();
                    }
                    orders.add(order);

                }
            }
        }
        List<User> userList= userService.getUserList( 0,  1000);
        List<Product> products= productService.getProductList( 0,  1000);
        User user = (User) request.getSession().getAttribute("user");
        List<Bid> bids=orderService.getAllBidList();
        String factoryid=null;
        List<Factory> factories=factoryService.getFactoryList(0,1000);
        int n=orderService.getOrderList(0,1000).size();
        Iterator<Order> orderIterator = orders.iterator();
        List<String> orderids=new ArrayList<String>();
        List<Order> orders1=new ArrayList<Order>();
        if (user!=null){
            for(Factory factory:factories){
                if (factory.getUserid().equals(user.getId())){
                    factoryid=factory.getId();
                }
            }

            for (Bid bid:bids){
                if (bid.getFactoryid().equals(factoryid)&&bid.getStatus()!=null&&bid.getStatus().equals("2")){
                    orderids.add(bid.getOrderid());
                }
            }

            for(Order order:orders){
                for(Product product:products){
                    if (product.getId().equals(order.getProductid())){
                        order.setProductid(product.getProductname());
                    }

                }
                order.setUsername(user.getName());
                if(order.getOrderstatus().equals("1")){
                    order.setOrderstatus("已保存");
                }else if (order.getOrderstatus().equals("2")){
                    order.setOrderstatus("已发布");
                }else if (order.getOrderstatus().equals("3")){
                    order.setOrderstatus("投标结束");
                }else if (order.getOrderstatus().equals("4")){
                    order.setOrderstatus("已发货");
                }else if (order.getOrderstatus().equals("5")){
                    order.setOrderstatus("订单完成");
                }
            }
            for (String orderid:orderids){
                for(Order order:orders){
                    if (order.getId().equals(orderid)){
                        orders1.add(order);
                    }
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
            String listJson= JSONArray.toJSONString(orders1);
            listJson="{\n" +
                    "  \"code\": 0,\n" +
                    "  \"msg\": \"\",\n" +
                    "  \"count\": "+orders1.size()+",\n" +
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




    @RequestMapping(value="/addorder")
    @ResponseBody
    public void addOrder(@RequestBody JSONObject obj,HttpSession session){
        String data=obj.toJSONString();
        JSONObject json = JSON.parseObject(data);
        String deliverdate=  json.getString("deliverdate");
        String deaddate=  json.getString("deaddate");
        java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
        Date date1 = null;
        Date date2 = null;
        try {
             date1=formatter.parse(deliverdate);
             date2=formatter.parse(deaddate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String product=json.getString("product");
        String number=json.getString("number");
        String receipt=json.getString("receipt");
        String contact=json.getString("contact");
        String address=json.getString("address");
        int i=(int)((Math.random()*9+1)*100000);
        String orderno="DNO15567"+i;
        String id = UUID.randomUUID().toString();
        User user = (User) session.getAttribute("user");
//        System.out.print("adad"+id+user);
        if (user!=null&&user.getRoleid().equals("3")){
            Order order=new Order();
            order.setUserid(user.getId());
            order.setOrderno(orderno);
            order.setProductid(product);
            order.setAddress(address);
            order.setContact(contact);
            order.setDeaddate(date2);
            order.setDeliverDate(date1);
            order.setOrdernum(number);
            order.setId(id);
            order.setReceipt(receipt);
            order.setOrderstatus("1");
            System.out.println(order);
            if (orderService.addOrder(order)){
                System.out.println("订单添加成功");
            }
        }

    }

    @RequestMapping(value="deleteorder")
    @ResponseBody
    public void deleteorder(HttpServletRequest request){
        String id= request.getParameter("id");
        if(id!=null){
            boolean flag=orderService.deleteOrder(id);
            if(flag){
                System.out.println("订单删除成功");
            }
        }
    }

    @RequestMapping(value="/updateorder")
    @ResponseBody
    public void updateorder(@RequestBody JSONObject obj){
        String data=obj.toJSONString();
        JSONObject json = JSON.parseObject(data);
        String deliverdate=  json.getString("deliverdate");
        String deaddate=  json.getString("deaddate");
        String id=  json.getString("id");
        java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
        Date date1 = null;
        Date date2 = null;
        try {
            date1=formatter.parse(deliverdate);
            date2=formatter.parse(deaddate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String product=json.getString("product");
        String number=json.getString("number");
        String receipt=json.getString("receipt");
        String contact=json.getString("contact");
        String address=json.getString("address");
        Order order=new Order();
        order.setProductid(product);
        order.setAddress(address);
        order.setContact(contact);
        order.setDeaddate(date2);
        order.setDeliverDate(date1);
        order.setOrdernum(number);
        order.setId(id);
        order.setReceipt(receipt);
        if (orderService.updateOrder(order)){
            System.out.println("订单修改成功");
        }
    }

    @RequestMapping(value="updatestatus")
    @ResponseBody
    public void updatestatus(HttpServletRequest request){
        String id= request.getParameter("id");
        String option= request.getParameter("option");
        if(id!=null&&option!=null){
            boolean flag=orderService.updatestatus(id,option);
            if(flag){
                System.out.println("订单状态修改");
            }
        }
    }

    @RequestMapping("/bidlist")
    @ResponseBody
    public void BidList(HttpServletResponse response, HttpServletRequest request){
        String orderid=request.getParameter("orderid");
        List<Bid> bids=orderService.getBidList(orderid);
        List<Factory> factories=factoryService.getFactoryList(0,1000);
        for(Factory factory:factories){
            for (Bid bid:bids){
                if (bid.getFactoryid().equals(factory.getId())){
                    bid.setFactoryname(factory.getFactoryname());
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
            String listJson= JSONArray.toJSONString(bids);
            listJson="{\n" +
                    "  \"code\": 0,\n" +
                    "  \"msg\": \"\",\n" +
                    "  \"count\": "+bids.size()+",\n" +
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

    @RequestMapping(value="bid")
    @ResponseBody
    public void bid(HttpServletRequest request){
        String id= request.getParameter("id");
        String orderid= request.getParameter("orderid");
        if(orderid!=null&&id!=null){
            orderService.updatebidstatus(id,"2");
            orderService.updatestatus(orderid,"3");
        }
    }

    @RequestMapping(value="bid2")
    @ResponseBody
    public void bid2(@RequestBody JSONObject obj,HttpServletRequest request){
        String data=obj.toJSONString();
        JSONObject json = JSON.parseObject(data);
        String orderid=  json.getString("id");
        String price=  json.getString("price");
        String id = UUID.randomUUID().toString();
        String factoryid=null;
        List<Factory> factories=factoryService.getFactoryList(0,1000);
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null) {
            for (Factory factory : factories) {
                if (factory.getUserid().equals(user.getId())) {
                    factoryid = factory.getId();
                }
            }
            Bid bid=new Bid();
            bid.setFactoryid(factoryid);
            bid.setBidprice(price);
            bid.setOrderid(orderid);
            bid.setId(id);
            bid.setStatus("1");
           if (orderService.addbid(bid)){
               System.out.println("投标成功");
           }
       }

    }

}
