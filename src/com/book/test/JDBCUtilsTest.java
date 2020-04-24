package com.book.test;

import com.alibaba.druid.util.JdbcUtils;
import com.book.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * 测试数据库连接是否正常
 * Created by YongXin Xue on 2020/04/11 14:45
 */
public class JDBCUtilsTest {
    @Test
    public void test(){
        for (int i = 0; i < 10; i++){
            Connection connection = JDBCUtils.getConnection();
            System.out.println(connection);
//            JdbcUtils.close(connection);
        }

    }
}
