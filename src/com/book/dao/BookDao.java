package com.book.dao;

import com.book.pojo.Book;

import java.util.List;

/**
 * 图书接口
 * Created by YongXin Xue on 2020/04/16 16:51
 */
public interface BookDao {
    /**
     * 添加图书
     * @param book
     * @return
     */
    public int addBook(Book book);

    /**
     * 根据编号删除图书
     * @param id
     * @return
     */
    public int deleteBookById(Integer id);

    /**
     * 修改图书
     * @param book
     * @return
     */
    public int updateBook(Book book);

    /**
     * 根据图书编号查询
     * @param id
     * @return
     */
    public Book queryBookById(Integer id);

    /**
     * 批量查询
     * @return
     */
    public List<Book> queryBooks();

    /**
     * 求总记录数
     * @return
     */
    Integer queryForPageTotalCount();

    /**
     * 当前页数据
     * @param begin
     * @param pageSize
     * @return
     */
    List<Book> queryForPageItems(int begin, int pageSize);

    /**
     * 求价格区间求总记录数
     * @param min
     * @param max
     * @return
     */
    Integer queryForPageTotalCountByPrice(int min, int max);

    /**
     * 根据价格区间求当前页数据
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
