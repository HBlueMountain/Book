package com.book.web;
import com.book.pojo.Book;
import com.book.pojo.Page;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Created by YongXin Xue on 2020/04/18 21:06
 */
@WebServlet(name = "ClientBookServlet", urlPatterns = "/client/books")
public class ClientBookServlet extends BaseServlet {

    private BookService bookService= new BookServiceImpl();

    /**
     * 首页图书分页
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("求分页数据保存到 Request 域中,最后转发到client/index.jsp 页面中");
        // 1.获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);  // 默认是第一页
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        // 2.调用 BookService.page(pageNo, pageSize) : Page 对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        // 设置分页条的请求地址
        page.setUrl("client/books?action=page");

        // 3.保存 Page 对象到 request 域中
        request.setAttribute("page", page);
        // 4.请求转发到 /pages/client/index.jsp 页面中
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }

    /**
     * 根据价格区间显示分页数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);  // 默认是第一页
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(request.getParameter("min"), 0);
        int max = WebUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);
        // 2.调用 BookService.page(pageNo, pageSize) : Page 对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max );
        //将图书最大价格和最小价格追加
        StringBuilder sb = new StringBuilder("client/books?action=pageByPrice");
        if (request.getParameter("min") != null){
            sb.append("&min=").append(request.getParameter("min"));
        }
        if (request.getParameter("max") != null){
            sb.append("&max=").append(request.getParameter("max"));
        }
        // 设置分页条的请求地址
        page.setUrl(sb.toString());


        // 3.保存 Page 对象到 request 域中
        request.setAttribute("page", page);
        // 4.请求转发到 /pages/client/index.jsp 页面中
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }

}
