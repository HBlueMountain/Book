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
        String sql = "INSERT INTO `t_order`( `order_id`,`create_time`, `total_price`,`status`,`user_id`) VALUES (?,?,?,?,?) ;";
        return update(sql, order.getOrderId(), order.getCreateTime(), order.getTotalPrice(), order.getStatus(), order.getUserId());
    }

    @Override
    public List<Order> queryAllOrders() {
        return null;
    }

    @Override
    public List<Order> queryOrderByUserId(Integer userId) {
        return null;
    }

    @Override
    public int updateOrderByStatus(String orderId, Integer status) {
        return 0;
    }
}
