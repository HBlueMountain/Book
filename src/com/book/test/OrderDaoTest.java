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
        orderDao.saveOrder(new Order("343462344234HDF", new Date(), new BigDecimal(1111), 0, 1));
    }

    @Test
    public void queryAllOrders() {
    }

    @Test
    public void queryOrderByUserId() {
    }

    @Test
    public void updateOrderByStatus() {
    }
}