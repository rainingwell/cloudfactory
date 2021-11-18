package com.dao;

import com.dao.ProductType.ProductTypeMapper;
import com.dao.product.ProductMapper;
import com.dao.user.UserMapper;
import com.pojo.Product;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.UUID;

public class ProductDaoTest {
    @Test
    public void testlist(){
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
        ProductMapper productMapper = (ProductMapper) act.getBean(ProductMapper.class);
        productMapper.getProductCount();
        productMapper.getProductList(1,10);
        System.out.println( productMapper.getProductCount()+"gg"+productMapper.getProductList(1,10));
    }

    @Test
    public void testTypelist(){
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
        ProductTypeMapper productTypeMapper = (ProductTypeMapper) act.getBean(ProductTypeMapper.class);
//        productTypeMapper.getProductCount();
//        productTypeMapper.getProductList(1,10);
//        System.out.println( productTypeMapper.getProductCount()+"gg"+productTypeMapper.getProductList(1,10));
    }

    @Test
    public void testAddProduct(){
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
        ProductMapper productMapper = (ProductMapper) act.getBean(ProductMapper.class);
        int i=(int)((Math.random()*9+1)*100000);
        String productno="PNO15567"+i;
        String id = UUID.randomUUID().toString();
        Product product=new Product();
        product.setTypeid("1");
        product.setId(id);
        product.setProductno(productno);
        product.setDescribe("1");
        product.setNorms("1");
        product.setProductname("1");
        System.out.println(product);
        productMapper.addProduct(product);
    }

    @Test
    public void testdeleteproduct(){
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
        ProductMapper productMapper = (ProductMapper) act.getBean(ProductMapper.class);
        productMapper.deleteProduct("636b666232114521bd454043a5ab47b7");
    }
}
