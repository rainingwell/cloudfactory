package com.dao;

import com.dao.order.OrderMapper;
import com.dao.product.ProductMapper;
import com.dao.user.UserMapper;
import com.pojo.Order;
import com.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class OrderTest {
    @Test
    public void testlist(){
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
        OrderMapper orderMapper = (OrderMapper) act.getBean(OrderMapper.class);
        System.out.println(orderMapper.getOrderList(0,1000));
    }
}
