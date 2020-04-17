package com.book.dao;

import com.alibaba.druid.util.JdbcUtils;
import com.book.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 抽象的 DaseDao
 * Created by YongXin Xue on 2020/04/11 15:04
 */
public abstract class BaseDao {
    //用于执行 SQL 语句
    QueryRunner queryRunner = new QueryRunner();

    /**
     * update() 方法用于执行 insert update delete 的SQL语句
     * @return
     */
    public int update(String sql, Object ... args){
        Connection conn = JDBCUtils.getConnection();
        try {
            //执行 insert update delete 的SQL语句
            return queryRunner.update(conn, sql, args);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.colse(conn);
        }
        return -1;  //表示连接失败
    }

    /**
     * 执行返回一个 JavaBean
     * @param type 返回的对象类型
     * @param sql  执行的sql语句
     * @param args sql对应的参数值
     * @param <T>  具体类型的泛型
     * @return  返回 null,说明查询失败,有值则成功
     */
    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.colse(conn);
        }
        return null;
    }

    /**
     * 执行返回多个JavaBean 的 SQL
     * @param type  每个JavaBean的具体类型
     * @param sql   sql语句
     * @param args  sql对应的参数值
     * @param <T>   返回值的具体类型
     * @return  返回null,则查询失败,负责查询成功
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.colse(conn);
        }
        return null;
    }

    /**
     * 用于执行查询结果是单行单列的情况
     * @param sql   执行的sql语句
     * @param args  sql对应的参数值
     * @return  返回的结果
     */
    public Object queryForSingleValue(String sql, Object ... args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }
        return null;
    }
}
