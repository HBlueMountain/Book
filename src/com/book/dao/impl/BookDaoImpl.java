package com.book.dao.impl;

import com.book.dao.BaseDao;
import com.book.dao.BookDao;
import com.book.pojo.Book;

import java.util.List;

/**
 * 实现图书接口
 * Created by YongXin Xue on 2020/04/16 16:57
 */
public class BookDaoImpl extends BaseDao implements BookDao {

    @Override
    public int addBook(Book book) {
        //测试当前线程是否同步
        System.out.println("BookDaoImpl 当前线程名是:" + Thread.currentThread().getName());
        String sql = "INSERT INTO t_book (`name`, `author`, `price`, `sales`, `stock`, `img_path` ) value (?,?,?,?,?,?)";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "DELETE FROM t_book WHERE id = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "UPDATE t_book set `name`=? , `author`=? , `price`=? , `sales`=? , `stock`=? , `img_path`=? WHERE id = ?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        //测试当前线程是否同步
        System.out.println("BookDaoImpl 当前线程名是:" + Thread.currentThread().getName());
        String sql = "SELECT `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` img_Path FROM t_book WHERE id = ?";
        return queryForOne(Book.class, sql, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "SELECT `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` img_Path FROM t_book";
        return queryForList(Book.class, sql );
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` img_Path FROM t_book limit ?,?";
        return queryForList(Book.class, sql, begin, pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number count = (Number) queryForSingleValue(sql, min, max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` img_Path, `id` " +
                "FROM t_book where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class, sql, min, max, begin, pageSize);
    }
}
