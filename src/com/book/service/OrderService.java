package com.book.service;

import com.book.pojo.Cart;
import com.book.pojo.Order;
import com.book.pojo.OrderItem;

import java.util.List;

/**
 * 订单功能
 * Created by YongXin Xue on 2020/04/22 10:17
 */
public interface OrderService {
    /**
     * 生成订单
     * @param cart  购物车
     * @param userId    用户Id
     */
    public String createOrder(Cart cart, Integer userId);

    /**
     * 管理员查询全部订单
     * @return
     */
    public List<Order> queryAllOrders();

    /**
     * 根据用户查询用户订单
     * @param userId
     * @return
     */
    public List<Order> queryMyOrders(Integer userId);

    /**
     *  管理员根据订单号发货
     * @param orderId   订单号
     */
    public void sendOrder(String orderId);

    /**
     * 用户签收
     * @param orderId
     */
    public void reveiverOrder(String orderId);

    /**
     * 根据订单号查询 订单项
     * @param orderId   订单号
     * @return
     */
    public List<OrderItem> queryOrderDetails(String orderId);

}
