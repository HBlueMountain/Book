package com.book.test;

import com.book.pojo.Cart;
import com.book.pojo.CartItems;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * 测试购物车功能
 * Created by YongXin Xue on 2020/04/21 20:21
 */
public class CartTest {

    @Test
    public void addItems() {
        Cart cart = new Cart();
        cart.addItems(new CartItems(1, "Java编程之旅", 1, new BigDecimal(999), new BigDecimal(100)));
        cart.addItems(new CartItems(1, "Java编程之旅", 1, new BigDecimal(999), new BigDecimal(100)));
        cart.addItems(new CartItems(2, "梦里编程", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItems(new CartItems(3, "MacBook Pro", 1, new BigDecimal(25000), new BigDecimal(25000)));
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItems(new CartItems(1, "Java编程之旅", 1, new BigDecimal(999), new BigDecimal(100)));
        cart.addItems(new CartItems(1, "Java编程之旅", 1, new BigDecimal(999), new BigDecimal(100)));
        cart.addItems(new CartItems(2, "梦里编程", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItems(new CartItems(3, "MacBook Pro", 1, new BigDecimal(25000), new BigDecimal(25000)));
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItems(new CartItems(1, "Java编程之旅", 1, new BigDecimal(999), new BigDecimal(100)));
        cart.addItems(new CartItems(1, "Java编程之旅", 1, new BigDecimal(999), new BigDecimal(100)));
        cart.addItems(new CartItems(2, "梦里编程", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItems(new CartItems(3, "MacBook Pro", 1, new BigDecimal(25000), new BigDecimal(25000)));
        cart.updateCount(2, 10);
        System.out.println(cart);
    }

    @Test
    public void deleteItems() {
        Cart cart = new Cart();
        cart.addItems(new CartItems(1, "Java编程之旅", 1, new BigDecimal(999), new BigDecimal(100)));
        cart.addItems(new CartItems(1, "Java编程之旅", 1, new BigDecimal(999), new BigDecimal(100)));
        cart.addItems(new CartItems(2, "梦里编程", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItems(new CartItems(3, "MacBook Pro", 1, new BigDecimal(25000), new BigDecimal(25000)));
        cart.deleteItems(2);
        System.out.println(cart);
    }
}