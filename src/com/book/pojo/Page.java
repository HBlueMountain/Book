package com.book.pojo;

import java.util.List;

/**
 * @page 分页模型对象
 * @param <T> 是具体的模块的 javaBean 类
 * Created by YongXin Xue on 2020/04/17 22:06
 */
public class Page<T> {

    public static final Integer PAGE_SIZE = 4;

    private Integer pageNo;     //当前页码
    private Integer pageToTal;  //总页码
    private Integer pageSize = PAGE_SIZE;   //当前页显示数量
    private Integer getPageToTalCount;      //记录总数
    private List<T> items;   //当前页数据

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageToTal=" + pageToTal +
                ", pageSize=" + pageSize +
                ", getPageToTalCount=" + getPageToTalCount +
                ", items=" + items +
                '}';
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageToTal() {
        return pageToTal;
    }

    public void setPageToTal(Integer pageToTal) {
        this.pageToTal = pageToTal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getGetPageToTalCount() {
        return getPageToTalCount;
    }

    public void setGetPageToTalCount(Integer getPageToTalCount) {
        this.getPageToTalCount = getPageToTalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
