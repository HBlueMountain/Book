package com.book.test;

import com.book.pojo.Cart;
import com.book.pojo.CartItems;
import com.book.pojo.OrderItem;
import com.book.service.OrderService;
import com.book.service.impl.OrderServiceImpl;
import com.book.web.BaseServlet;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * 订单业务功能测试
 * Created by YongXin Xue on 2020/04/22 15:00
 */
public class OrderServiceTest extends BaseServlet {
    OrderService orderService = new OrderServiceImpl();
    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItems(new CartItems(1, "天才在左 疯子在右(2018全新完整版)", 1, new BigDecimal(45), new BigDecimal(45)));
        cart.addItems(new CartItems(4, "了不起的我", 1, new BigDecimal(78), new BigDecimal(78)));
        cart.addItems(new CartItems(2, "最好的告别", 1, new BigDecimal(56), new BigDecimal(56)));
        cart.addItems(new CartItems(3, "高性能MySQL（第3版）", 1, new BigDecimal(125), new BigDecimal(125)));
        orderService.createOrder(cart, 1);
    }

    @Test
    public void queryAllOrders() {
        orderService.queryAllOrders().forEach(System.out::println);
    }

    @Test
    public void queryMyOrders() {
        orderService.queryMyOrders(2).forEach(System.out::println);
    }

    @Test
    public void sendOrder() {
        orderService.sendOrder("15875526707492");
    }

    @Test
    public void reveiverOrder() {
        orderService.sendOrder("15875526707492");
    }

    @Test
    public void queryOrderDetails() {
        orderService.queryOrderDetails("15875526707492").forEach(System.out::println);
    }
}