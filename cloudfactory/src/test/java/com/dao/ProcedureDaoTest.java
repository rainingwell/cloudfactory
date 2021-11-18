package com.dao;

import com.dao.procedure.ProcedureMapper;
import com.dao.user.UserMapper;
import com.pojo.Order;
import com.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ProcedureDaoTest {

    @Test
    public void testPurchase(){
        //获取sqlsession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        //方式一getMapper
        ProcedureMapper procedureDao=sqlSession.getMapper(ProcedureMapper.class);
        System.out.println(procedureDao.purchase("ONO123123","116b785057ee40f88255305690253aa9",101,"2020-01-01","2019-12-20","291d5530e2154ea5af25bc68504d41fc","东北大学","15252030061"));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testbid(){
        //获取sqlsession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        //方式一getMapper
        ProcedureMapper procedureDao=sqlSession.getMapper(ProcedureMapper.class);
        System.out.println(procedureDao.bid("4cd69a79dd4541bbbe6b38ee731e62f0"));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testrent(){
        //获取sqlsession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //方式一getMapper
        ProcedureMapper procedureDao=sqlSession.getMapper(ProcedureMapper.class);
        System.out.println(procedureDao.rent("56c6b74784504f4290ff6bddab49cff3","2019-04-29","2","d2749388131d4ba68fffbff226cc5ec0"));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testcapacity(){
        //获取sqlsession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //方式一getMapper
        ProcedureMapper procedureDao=sqlSession.getMapper(ProcedureMapper.class);
        System.out.println(procedureDao.capacity("a1c92633659b4219bfc80c57463d5964","ddd6f77e9bc6470db07b96efd6e930e5","23"));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testschedule(){
        //获取sqlsession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //方式一getMapper
        ProcedureMapper procedureDao=sqlSession.getMapper(ProcedureMapper.class);
        System.out.println(procedureDao.schedule("02ffc8f9fc4a409a97b966d26a16deab","56c6b74784504f4290ff6bddab49cff3","094601611766410982f0e839dc00b1d0","2021-08-10","2021-08-11"));
        sqlSession.commit();
        sqlSession.close();
    }

}
