package com.book.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 * Created by YongXin Xue on 2020/04/22 0:07
 */
public class Order {
    private String orderId;     //订单号
    private Date createTime;    //订单时间
    private BigDecimal totalPrice;   //订单金额
    private Integer status;     //订单物流状态 0未发货| 1已发货| 2已签收
    private Integer userId;     //用户编号

    public Order() {
    }

    public Order(String orderId, Date createTime, BigDecimal totalPrice, Integer status, Integer userId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.totalPrice = totalPrice;
        this.status = status;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
