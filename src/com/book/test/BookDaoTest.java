package com.book.test;

import com.book.dao.BookDao;
import com.book.dao.impl.BookDaoImpl;
import com.book.pojo.Book;
import com.book.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 测试图书功能
 * Created by YongXin Xue on 2020/04/16 17:15
 */
public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "编程之美", "蓝山", new BigDecimal(99.99), 9999, 1000,null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(6, "美食之家", "东方", new BigDecimal(89.99), 9999, 1000,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
         bookDao.queryBooks().forEach(System.out::println);
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        bookDao.queryForPageItems(6, Page.PAGE_SIZE).forEach(System.out::println);
    }
    @Test
    public void queryForPageItemsByPrice(){
        bookDao.queryForPageItemsByPrice(0, Page.PAGE_SIZE, 10, 50).forEach(System.out::println);
    }

    @Test
    public void queryForPageTotalCountByPrice(){
        System.out.println(bookDao.queryForPageTotalCountByPrice(10, 50));
    }
}