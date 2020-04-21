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
        String sql = " INSERT INTO `t_order_item` (`name`,`price`,`total_price`,`count`,`order_id`) VALUES(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());

    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        return null;
    }
}
