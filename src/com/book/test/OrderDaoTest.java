package com.book.test;

import com.book.dao.OrderDao;
import com.book.dao.impl.OrderDaoImpl;
import com.book.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * 测试订单功能
 * Created by YongXin Xue on 2020/04/22 0:32
 */
public class OrderDaoTest {

    private OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("20204210006CC", new Date(), new BigDecimal(1234), 0, 2));
        orderDao.saveOrder(new Order("20204210004CC", new Date(), new BigDecimal(4000), 0, 1));
        orderDao.saveOrder(new Order("20204210007CC", new Date(), new BigDecimal(4775), 0, 1));
        orderDao.saveOrder(new Order("20204210008CC", new Date(), new BigDecimal(3455), 0, 2));
    }

    @Test
    public void queryAllOrders() {
        orderDao.queryAllOrders().forEach(System.out::println);
    }

    @Test
    public void queryOrderByUserId() {
        orderDao.queryOrderByUserId(2).forEach(System.out::println);
    }

    @Test
    public void updateOrderByStatus() {
        orderDao.updateOrderByStatus("20204210006CC", 1);
    }
}