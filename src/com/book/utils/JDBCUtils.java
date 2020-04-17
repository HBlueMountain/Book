package com.book.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 工具类
 * 操作数据库连接(从数据库连接池获取)
 * Created by YongXin Xue on 2020/04/11 14:33
 */
public class JDBCUtils {

    static DruidDataSource dataSource;

    static {

        try {
            //创建属性配置文件
            Properties properties = new Properties();
            //同类加载器读取 jdbc.properties 属性文件
            InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(inputStream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

            //测试是否得到连接
            //System.out.println(dataSource.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从数据连接池中获取连接
     * @return
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            //从数据库连接池中获取连接
            conn = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭连接,放回数据库
     * @param conn
     */
    public static void colse(Connection conn){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
