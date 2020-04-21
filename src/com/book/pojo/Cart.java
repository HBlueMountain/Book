package com.book.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车
 * Created by YongXin Xue on 2020/04/21 10:21
 */
public class Cart {
    /**
     * 购物车中的商品信息
     * Integer key : 商品编号
     * CartItems value: 商品信息
     */
    private Map<Integer, CartItems> items = new LinkedHashMap<>();

    /**
     * 添加商品项
     * @param cartItems
     */
    public void addItems(CartItems cartItems){
        // 先查看购物车中是否已经添加过此商品
        CartItems item = items.get(cartItems.getId());
        if (item == null){
            // 发现购物车中没有该商品,就添加
            items.put(cartItems.getId(), cartItems);
        }else{
            // 如果已经有该商品,就修改商品数量
            item.setCount(item.getCount() + 1 );
            // 修改商品总金额
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }

    /**
     * 修改商品数量
     * @param id    商品编号
     * @param count 商品数量
     */
    public void updateCount(Integer id, Integer count){
        // 先查看购物车中是否有此商品
        CartItems cartItems = items.get(id);
        // 如果有,修改商品数量,并更新总金额
        if (cartItems != null){
            cartItems.setCount(count);  //修改商品数量
            // 更新总金额
            cartItems.setTotalPrice(cartItems.getPrice().multiply(new BigDecimal(cartItems.getCount())));
        }
    }

    /**
     * 删除商品
     * @param id    商品编号
     */
    public void deleteItems(Integer id){
        items.remove(id);
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;     //商品总数量
        for (CartItems cartItem : items.values()) {
            totalCount += cartItem.getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);  //商品总金额
        for (CartItems cartItem : items.values()) {
            totalPrice = totalPrice.add(cartItem.getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItems> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItems> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
