package com.book.test;

import com.book.pojo.Book;
import com.book.pojo.Page;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * 测试图书业务功能
 * Created by YongXin Xue on 2020/04/16 17:44
 */
public class BookServiceTest {

    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "易经", "蓝山", new BigDecimal(10000000), 1000, 500, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(23);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(23, "易经", "蓝山", new BigDecimal(10000), 1000, 500, null));
    }

    @Test
    public void queryBookId() {
        System.out.println(bookService.queryBookId(23));
    }

    @Test
    public void queryBooks() {
        bookService.queryBooks().forEach(System.out::println);
    }

    @Test
    public void page(){
        Page<Book> page = bookService.page(1, Page.PAGE_SIZE);
        System.out.println(page);
    }
    @Test
    public void pageByPrice(){
        Page<Book> page = bookService.pageByPrice(1, Page.PAGE_SIZE, 10, 50);
        System.out.println(page);
    }
}