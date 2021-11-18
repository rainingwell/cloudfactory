package com.util;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPUtils {

    private static DataSource dataSource;
    static {

        try {
            InputStream in = DBCPUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
            Properties props = new Properties();
            props.load(in);
            dataSource = BasicDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }
    /**
     * 获取DataSource对象
     * @return
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    //获取connection
    public  static Connection getConnection(){
        Connection connection=null;
        try {
            connection= dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//        System.out.println("dbpc连接");
        return connection;
    }

//    public static boolean closeDataSource(dataSource){
//        dataSource.
//    }
}

