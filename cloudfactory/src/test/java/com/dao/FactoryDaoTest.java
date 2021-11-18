package com.dao;

import com.dao.factory.FactoryMapper;
import com.dao.product.ProductMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryDaoTest {
    @Test
    public void testlist(){
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
        FactoryMapper factoryMapper = (FactoryMapper) act.getBean(FactoryMapper.class);
        factoryMapper.getFactoryCount();
        factoryMapper.getFactoryList(1,10);
        System.out.println(factoryMapper.getFactoryCount());
    }
}
