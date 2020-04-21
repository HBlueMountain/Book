package com.book.dao;

import com.book.pojo.Order;

import java.util.List;

/**
 * 订单接口
 * Created by YongXin Xue on 2020/04/22 0:16
 */
public interface OrderDao {
    /**
     * 保存订单
     * @param order
     * @return
     */
    public int saveOrder(Order order);

    /**
     * 查询全部订单
     * @return
     */
    public List<Order> queryAllOrders();

    /**
     * 根据用户id查询订单
     * @param userId    用户id
     * @return
     */
    public List<Order> queryOrderByUserId(Integer userId);

    /**
     * 根据订单号修改订单状态
     * @param orderId   订单号
     * @param status    订单状态
     * @return
     */
    public int updateOrderByStatus(String orderId, Integer status);
}
