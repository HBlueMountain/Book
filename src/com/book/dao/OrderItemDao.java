package com.book.dao;

import com.book.pojo.OrderItem;

import java.util.List;

/**
 * 订单项接口
 * Created by YongXin Xue on 2020/04/22 0:27
 */
public interface OrderItemDao {
    /**
     * 保存订单项
     * @param orderItem
     * @return
     */
    public int saveOrderItem(OrderItem orderItem);

    /**
     * 根据订单号,查询订单明细
     * @param orderId   订单号
     * @return
     */
    public List<OrderItem>  queryOrderItemByOrderId(String orderId);


}
