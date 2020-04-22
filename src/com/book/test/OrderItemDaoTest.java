package com.book.test;

import com.book.dao.OrderDao;
import com.book.dao.OrderItemDao;
import com.book.dao.impl.OrderDaoImpl;
import com.book.dao.impl.OrderItemDaoImpl;
import com.book.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * 测试订单项功能
 * Created by YongXin Xue on 2020/04/22 0:46
 */
public class OrderItemDaoTest {

    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null, "手撕Spring", 288, new BigDecimal(100), new BigDecimal(28800), "20204210008CC"));
        orderItemDao.saveOrderItem(new OrderItem(null, "Java高级编程", 288, new BigDecimal(200), new BigDecimal(20000), "20204210008CC"));
        orderItemDao.saveOrderItem(new OrderItem(null, "手写Tomcat", 288, new BigDecimal(100), new BigDecimal(28800), "20204210008CC"));
        orderItemDao.saveOrderItem(new OrderItem(null, "老韩手写二叉树", 288, new BigDecimal(100), new BigDecimal(28800), "20204210008CC"));
    }

    @Test
    public void queryOrderItemByOrderId() {
        orderItemDao.queryOrderItemByOrderId("158755851477313").forEach(System.out::println);
    }
}