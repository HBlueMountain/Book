package com.book.web;

import com.book.pojo.Book;
import com.book.pojo.Page;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.WebUtils;
import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 图书的 View 层
 * Created by YongXin Xue on 2020/04/16 17:55
 */
@WebServlet(name = "BookServlet", urlPatterns = "/manager/books")
public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 处里分页功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);  // 默认是第一页
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        // 2.调用 BookService.page(pageNo, pageSize) : Page 对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        // 3.保存 Page 对象到 request 域中
        request.setAttribute("page", page);
        // 4.请求转发到 pages/manager/book_manager.jsp 页面中
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }

    /**
     * 添加图书
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1.获取请求的参数 --> 封装成Book对象
        Book book = WebUtils.copyParamToBean(new Book(), request.getParameterMap());
        // 2.调用BookService.addBook()保存图书
        bookService.addBook(book);
        // 3.跳转到图书列表页面
//        request.getRequestDispatcher("/manager/books?action=list").forward(request, response);  //有bug会重复提交表单
        // 这里使用重定向
        response.sendRedirect(request.getContextPath() + "/manager/books?action=page&pageNo=" + Integer.MAX_VALUE);
    }

    /**
     * 删除图书
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取请求的参数id
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        // 2.调用 BookServlet.deleteBookById(id) 删除图书
        bookService.deleteBookById(id);
        // 3.重定向到图书列表页面
        response.sendRedirect(request.getContextPath() + "/manager/books?action=page&pageNo=" + request.getParameter("pageNo"));
    }

    /**
     * 更新图书
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取请求的参数 封装成 book 对象
        Book book = WebUtils.copyParamToBean(new Book(), request.getParameterMap());
        // 2.调用BookService.updateBook(book) 修改图书
        bookService.updateBook(book);
        // 3.重定向到图书列表页
        response.sendRedirect(request.getContextPath() + "/manager/books?action=page&pageNo=" + request.getParameter("pageNo"));

    }

    /**
     * 查询要修改的图书信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取请求的参数图书编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        // 2.调用 bookService.queryById 根据id查询的方法
        Book book = bookService.queryBookId(id);
        // 3.保存到图书的 request 域中
        request.setAttribute("book", book);
        // 4.请求转发到 pages/manager/book_edit.jsp 页面中
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
    }

    /**
     * 查询图书(全部图书)
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1.通过 BookService 查询全部图书信息
        List<Book> books = bookService.queryBooks();
        // 2.把全部的图书信息保存的到 request 域中
        request.setAttribute("books", books);
        // 3.请求转发到 /manager/book_manager.jsp 页面中
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }
}
