package com.book.pojo;

import java.util.List;

/**
 * Page是分页的模型对象
 * @page 分页模型对象
 * Created by YongXin Xue on 2020/04/17 22:06
 */
public class Page<T> {

    public static final Integer PAGE_SIZE = 2;

    private Integer pageNo;     //当前页码
    private Integer pageToTal;  //总页码
    private Integer pageSize = PAGE_SIZE;   //当前页显示数量
    private Integer getPageToTalCount;      //记录总数
    private List<T> items;   //当前页数据
    private String url;      //分页条中的请求地址

    public Page() {
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPageNo(Integer pageNo) {
        /**
         * [页码]数据有效边界检查
         * 如果当前页码小于 1 ,则显示第一页
         * 如果当前页码大于总页码,先死最后一页
         */
        if (pageNo < 1){
            pageNo = 1;
        }
        if (pageNo > pageToTal){
            pageNo = pageToTal;
        }
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

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageToTal=" + pageToTal +
                ", pageSize=" + pageSize +
                ", getPageToTalCount=" + getPageToTalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}


