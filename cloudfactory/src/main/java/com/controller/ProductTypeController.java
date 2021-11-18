package com.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pojo.Product;
import com.pojo.ProductType;
import com.service.productType.ProductTypeService;
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
@RequestMapping("/productType")
public class ProductTypeController {
    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping("/list")
    @ResponseBody
    public String getProductTypeList(HttpServletResponse response){
        List<ProductType> productTypes=productTypeService.getProductTypeList(0, 1000);
        response.setContentType("application/json");
        /*
         * resultMap = ["result","sessionerror","result",error]
         * josn格式={key,value}
         */
        String listJson= JSONArray.toJSONString(productTypes);
        listJson="{\n" +
                "  \"code\": 0,\n" +
                "  \"msg\": \"\",\n" +
                "  \"count\": "+productTypeService.getProductTypeCount()+",\n" +
                "  \"data\": "+listJson+"\n}";
        return listJson;

    }

    @RequestMapping(value="tolist")
    public String ToProductTypeList(){
        return "productTypeList";
    }

    @RequestMapping(value="typelist")
    public void getProductTypeList(HttpServletResponse response, HttpServletRequest request){
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
        List<ProductType> productTypes=productTypeService.getProductTypeList(currentpage,pageSize);
        //模糊查询
        int count=0;
        if(name!=null){
            for(ProductType productType:productTypes){
                if (productType.getTypename().contains(name)){
                    count++;
                    if (count==1){
                        productTypes=new ArrayList<ProductType>();
                    }
                    productTypes.add(productType);

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
            String listJson= JSONArray.toJSONString(productTypes);
            listJson="{\n" +
                    "  \"code\": 0,\n" +
                    "  \"msg\": \"\",\n" +
                    "  \"count\": "+productTypeService.getProductTypeCount()+",\n" +
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

    @RequestMapping(value="/addProductType")
    @ResponseBody
    public void addProductType(@RequestBody JSONObject obj){
        String data=obj.toJSONString();
        JSONObject json = JSON.parseObject(data);
        String name=json.getString("name");
        String id = UUID.randomUUID().toString();
        ProductType productType=new ProductType();
        productType.setTypename(name);
        productType.setId(id);
        System.out.println(productType);
        if(productTypeService.addProductType(productType)){
            System.out.println("产品类别添加成功");
        }else {
            System.out.println("产品类别添加失败");
        }

//json array
//        JSONArray DD= JSONArray.parseArray(d);
//        for (int i=0;i<DD.size();i++){
//            int s=JSONObject.parseObject(JSONObject.toJSONString(DD.get(i))).getInteger("s");
//            System.out.println(s);
//        }
//        return "1";
    }

    @RequestMapping(value="deleteProductType")
    @ResponseBody
    public void deleteProductType(HttpServletRequest request){
        String id= request.getParameter("id");
        if(id!=null){
            boolean flag=productTypeService.deleteProductType(id);
            if(flag){
                System.out.println("产品类别删除成功");
            }
        }
    }

    @RequestMapping(value="/updateProductType")
    @ResponseBody
    public void updateProductType(@RequestBody JSONObject obj){
        String data=obj.toJSONString();
        JSONObject json = JSON.parseObject(data);
        String id=json.getString("id");
        String typename=json.getString("name");
        ProductType productType=new ProductType();
        productType.setId(id);
        productType.setTypename(typename);
        System.out.println(productType);
        if (id!=null&&typename!=null) {
            if(productTypeService.updateProductType(productType)){
                System.out.println("产品类型更新成功");
            }else {
                System.out.println("产品类型更新失败");
            }
        }
    }

}
