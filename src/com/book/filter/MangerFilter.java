package com.book.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Filter 过滤器   value 表示需要拦截的路径
 * Created by YongXin Xue on 2020/04/24 16:25
 */
@WebFilter(value = {"/pages/manager/*", "/manager/books"})
public class MangerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //检查用户是否登录
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //获取用户登录之后的信息
        Object user = httpServletRequest.getSession().getAttribute("user");
        if (user == null){
            // 未登录就跳回登录
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }else{
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
