package com.book.service.impl;

import com.book.dao.BookDao;
import com.book.dao.OrderDao;
import com.book.dao.OrderItemDao;
import com.book.dao.impl.BookDaoImpl;
import com.book.dao.impl.OrderDaoImpl;
import com.book.dao.impl.OrderItemDaoImpl;
import com.book.pojo.*;
import com.book.service.OrderService;

import java.util.Date;
import java.util.List;

/**
 * 实现订单功能
 * Created by YongXin Xue on 2020/04/22 10:26
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao  bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        // 1.订单号要确保唯一,简单的使用 订单号:[时间 + 用户编号]
        String orderId = System.currentTimeMillis() + "" + userId;
        // 2.创建订单对象
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        // 3.保存订单
        orderDao.saveOrder(order);

        // 4.保存订单项
        // 遍历购物车中的商品项,生成订单项,并保存到数据库中
        for (CartItems cartItems : cart.getItems().values()) {
            // 5.把购物车的商品项转换为订单项
            OrderItem orderItem = new OrderItem(null, cartItems.getName(), cartItems.getCount(), cartItems.getPrice(), cartItems.getTotalPrice(), orderId);
            // 6.保存订单项
            orderItemDao.saveOrderItem(orderItem);
            // 7.修改图书的销量和库存
            // 得到原来的商品信息
            Book book = bookDao.queryBookById(cartItems.getId());
            // 修改库存
            book.setStock(book.getStock() - cartItems.getCount());
            // 修改销量
            book.setSales(book.getSales() - cartItems.getCount());
            // 8.保存商品信息
            bookDao.updateBook(book);
        }
        // 9.清空购物车
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> queryAllOrders() {
        return orderDao.queryAllOrders();
    }

    @Override
    public List<Order> queryMyOrders(Integer userId) {
        return orderDao.queryOrderByUserId(userId);
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.updateOrderByStatus(orderId,1);
    }

    @Override
    public void reveiverOrder(String orderId) {
        orderDao.updateOrderByStatus(orderId, 2);
    }

    @Override
    public List<OrderItem> queryOrderDetails(String orderId) {
        return orderItemDao.queryOrderItemByOrderId(orderId);
    }
}
