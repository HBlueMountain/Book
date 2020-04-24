package com.book.dao.impl;

import com.book.dao.BaseDao;
import com.book.dao.OrderDao;
import com.book.pojo.Order;
import com.sun.org.apache.xalan.internal.xsltc.cmdline.getopt.GetOpt;

import java.util.List;

/**
 * Created by YongXin Xue on 2020/04/22 0:23
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        //测试当前线程是否同步
        System.out.println("OrderDaoImpl 当前线程名是:" + Thread.currentThread().getName());
        String sql = "INSERT INTO `t_order`( `order_id`,`create_time`, `total_price`,`status`,`user_id`) VALUES (?,?,?,?,?)";
        return update(sql, order.getOrderId(), order.getCreateTime(), order.getTotalPrice(), order.getStatus(), order.getUserId());
    }

    @Override
    public List<Order> queryAllOrders() {
        String sql = "SELECT `order_id` orderId,`create_time` createTime, `total_price` totalPrice,`status`,`user_id` userId FROM `t_order`";
        return queryForList(Order.class, sql);
    }

    @Override
    public List<Order> queryOrderByUserId(Integer userId) {
        String sql = "SELECT `order_id` orderId,`create_time` createTime, `total_price` totalPrice,`status`,`user_id` userId " +
                "FROM `t_order` WHERE user_id = ?";
        return queryForList(Order.class, sql, userId);
    }

    @Override
    public int updateOrderByStatus(String orderId, Integer status) {
        String sql = "UPDATE t_order SET status = ? where order_id = ?";
        return update(sql, status, orderId);
    }
}
