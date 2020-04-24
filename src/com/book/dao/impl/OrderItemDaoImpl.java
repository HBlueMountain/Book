package com.book.dao.impl;

import com.book.dao.BaseDao;
import com.book.dao.OrderItemDao;
import com.book.pojo.OrderItem;

import java.util.List;

/**
 * Created by YongXin Xue on 2020/04/22 0:30
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        //测试当前线程是否同步
        System.out.println("OrderItemDaoImpl 当前线程名是:" + Thread.currentThread().getName());
        String sql = " INSERT INTO `t_order_item` (`name`,`price`,`total_price`,`count`,`order_id`) VALUES(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getCount(),orderItem.getOrderId());

    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        String sql = "SELECT `id`,`name`,`price`,`total_price` totalPrice,`count`,`order_id` orderId FROM `t_order_item` WHERE order_id = ?";
        return queryForList(OrderItem.class, sql, orderId);
    }
}
