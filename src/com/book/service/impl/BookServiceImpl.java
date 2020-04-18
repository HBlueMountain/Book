package com.book.service.impl;

import com.book.dao.BaseDao;
import com.book.dao.BookDao;
import com.book.dao.impl.BookDaoImpl;
import com.book.pojo.Book;
import com.book.pojo.Page;
import com.book.service.BookService;

import java.util.List;

/**
 * Created by YongXin Xue on 2020/04/16 17:38
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookId(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();

        // 2.设置每页显示的数量
        page.setPageSize(pageSize);

        // 3.求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        // 4.设置总记录数
        page.setGetPageToTalCount(pageTotalCount);

        // 5.求总页码
        Integer pageToTal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize > 0){
            pageToTal++;
        }
        // 6.设置总页码
        page.setPageToTal(pageToTal);

        // 1.设置当前页码
        page.setPageNo(pageNo);

        // 7.求当前页数据的开始索引 ==> [当前页码减一乘以每页数量]
        int begin = (page.getPageNo() - 1) * pageSize;
        // 7.求当前页数据
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        // 8.设置当前页数据
        page.setItems(items);
        return page;
    }
}
