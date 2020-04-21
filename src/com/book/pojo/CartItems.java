package com.book.pojo;

import java.math.BigDecimal;

/**
 * 购物车商品项
 * Created by YongXin Xue on 2020/04/21 10:21
 */
public class CartItems {
    private Integer id;     //商品编号
    private String name;    //商品名称
    private Integer count;  //商品数量
    private BigDecimal price;       //商品单价
    private BigDecimal totalPrice;  //商品总价

    public CartItems(Integer id, String name, Integer count, BigDecimal price, BigDecimal totalPrice) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public CartItems() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
