package com.book.service;

import com.book.pojo.Book;

import java.util.List;

/**
 * 图书业务层接口
 * Created by YongXin Xue on 2020/04/16 17:35
 */
public interface BookService {
    /**
     * 添加图书
     * @param book
     */
    public void addBook(Book book);

    /**
     * 删除图书
     * @param id
     */
    public void deleteBookById(Integer id);

    /**
     * 更新图书
     * @param book
     */
    public void updateBook(Book book);

    /**
     * 根据编号查询图书
     * @param id
     * @return
     */
    public Book queryBookId(Integer id);

    /**
     * 批量查询图书
     * @return
     */
    public List<Book> queryBooks();
}
