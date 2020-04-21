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
        orderItemDao.saveOrderItem(new OrderItem(null, "MySQL高级", 288, new BigDecimal(100), new BigDecimal(28800), "343462344234HDF"));
    }

    @Test
    public void queryOrderItemByOrderId() {
    }
}