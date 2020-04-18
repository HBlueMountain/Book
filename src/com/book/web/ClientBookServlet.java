package com.book.web;
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
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }

}