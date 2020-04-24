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
    //获取ThreadLocal连接对象
    static ThreadLocal<Connection> conns = new ThreadLocal<>();

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
        //先获取连接池中有没有连接对象
        Connection conn = conns.get();
        //如果连接为 null 表示之前没有获取过连接
        if (conn == null){
            try {
                //从数据库连接池中获取连接
                conn = dataSource.getConnection();
                //保存到 ThreadLocal 中
                conns.set(conn);
                //同时设置手动提交事务
                conn.setAutoCommit(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务 并关闭连接
     */
    public static void commitAndClose(){
        //获取之前使用的连接
        Connection connection = conns.get();
        //如果为 null 说明之前没有使用过,不要需要事务处理
        if (connection != null){
            try {
                //提交事务
                connection.commit();
                //关闭连接,放回连接池
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //解除线程和连接的关联,否则会出错
        conns.remove();
    }
    /**
     * 回滚事务 并关闭连接
     */
    public static void rollbackAndClose(){
        //获取之前使用的连接
        Connection connection = conns.get();
        //如果为 null 说明之前没有使用过,不要需要事务处理
        if (connection != null){
            try {
                //回滚事务
                connection.rollback();
                //关闭连接,放回连接池
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //解除线程和连接的关联,否则会出错
        conns.remove();
    }

    /**
     * 关闭连接,放回数据库
     * @param conn
     */
    /*public static void colse(Connection conn){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }*/
}
