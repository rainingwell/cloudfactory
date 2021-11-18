package com.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pojo.Factory;
import com.pojo.Product;
import com.pojo.ProductType;
import com.pojo.User;
import com.service.factory.FactoryService;
import com.service.product.ProductService;
import com.service.productType.ProductTypeService;
import com.service.user.UserService;
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
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;
    @RequestMapping(value="list")
    public String ToProductList(){
        return "productlist";
    }

    @RequestMapping(value="getlist")
    public void getProductList(HttpServletResponse response, HttpServletRequest request){
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
        List<Product> products=productService.getProductList(currentpage,pageSize);
        //模糊查询
        int count=0;
        if(name!=null){
            for(Product product:products){
                if (product.getProductname().contains(name)){
                    count++;
                    if (count==1){
                        products=new ArrayList<Product>();
                    }
                    products.add(product);

                }
            }
        }
        List<ProductType> productTypes=productTypeService.getProductTypeList(0,1000);
        for(Product product:products){
            for (ProductType productType:productTypes){
                if (product.getTypeid().equals(productType.getId())){
                    product.setTypeid(productType.getTypename());
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
            String listJson= JSONArray.toJSONString(products);
            listJson="{\n" +
                    "  \"code\": 0,\n" +
                    "  \"msg\": \"\",\n" +
                    "  \"count\": "+productService.getProductCount()+",\n" +
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

    @RequestMapping(value="getlist2")
    public void getProductList2(HttpServletResponse response, HttpServletRequest request){

        List<Product> products=productService.getProductList(0,1000);
        try {
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            /*
             * resultMap = ["result","sessionerror","result",error]
             * josn格式={key,value}
             */
            String listJson= JSONArray.toJSONString(products);
            listJson="{\n" +
                    "  \"code\": 0,\n" +
                    "  \"msg\": \"\",\n" +
                    "  \"count\": "+productService.getProductCount()+",\n" +
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

    @RequestMapping(value="/addproduct")
    @ResponseBody
    public void addProduct(@RequestBody JSONObject obj){
        String data=obj.toJSONString();
        JSONObject json = JSON.parseObject(data);
        String typeid=json.getString("Type");
        String name=json.getString("name");
        String describe=json.getString("describe");
        String norms=json.getString("norms");
        int i=(int)((Math.random()*9+1)*100000);
        String productno="PNO15567"+i;
        String id = UUID.randomUUID().toString();
        Product product=new Product();
        product.setTypeid(typeid);
        product.setId(id);
        product.setProductno(productno);
        product.setDescribe(describe);
        product.setNorms(norms);
        product.setProductname(name);
        System.out.println(product);
        if(productService.addProduct(product)){
            System.out.println("产品添加成功");
        }else {
            System.out.println("产品添加失败");
        }

//json array
//        JSONArray DD= JSONArray.parseArray(d);
//        for (int i=0;i<DD.size();i++){
//            int s=JSONObject.parseObject(JSONObject.toJSONString(DD.get(i))).getInteger("s");
//            System.out.println(s);
//        }
//        return "1";
    }

    @RequestMapping(value="deleteProduct")
    @ResponseBody
    public void deleteProduct(HttpServletRequest request){
        String id= request.getParameter("id");
        if(id!=null){
            productService.deletaProduct(id);
        }
    }

    @RequestMapping(value="/updateProduct")
    @ResponseBody
    public void updateProduct(@RequestBody JSONObject obj){
        String data=obj.toJSONString();
        JSONObject json = JSON.parseObject(data);
        String id=json.getString("id");
        String productno=json.getString("productno");
        String typeid=json.getString("Type");
        String name=json.getString("name");
        String describe=json.getString("describe");
        String norms=json.getString("norms");
        Product product=new Product();
        product.setTypeid(typeid);
        product.setId(id);
        product.setProductno(productno);
        product.setDescribe(describe);
        product.setNorms(norms);
        product.setProductname(name);
        System.out.println(product);
        if (id != null && typeid != null && name != null && describe != null && norms != null){
            if(productService.updateProduct(product)){
                System.out.println("产品更新成功");
            }else {
                System.out.println("产品更新失败");
            }
        }
    }



}
