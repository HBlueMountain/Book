package com.book.filter;

import com.book.utils.JDBCUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * TransactionFilter 过滤器统一添加事务管理
 * Created by YongXin Xue on 2020/04/24 23:01
 * value = "/*" 表示拦截http://ip:port/工程路径/所有请求
 */
@WebFilter(value = "/*")
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            // 间接给所有 XxxService.xxx()方法都加上try..catch
            chain.doFilter(request, response);
            JDBCUtils.commitAndClose();
        } catch (IOException e) {
            e.printStackTrace();
            JDBCUtils.rollbackAndClose();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
