package com.book.web;

import com.book.pojo.Cart;
import com.book.pojo.Order;
import com.book.pojo.OrderItem;
import com.book.pojo.User;
import com.book.service.OrderService;
import com.book.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 订单功能
 * Created by YongXin Xue on 2020/04/22 19:04
 */
@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    /**
     * 生成订单 [结账]
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void cartOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("结账,生成订单!");
        // 1.获取购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        // 2.获取Session中保存的用户信息
        User user = (User) request.getSession().getAttribute("user");
        // 3.判断用户登录
        if (user != null){
            // 生成订单号
            String orderId = orderService.createOrder(cart, user.getId());
            // 4.请求转发到登录页码
            // 5.订单号保存到 session 中
            request.getSession().setAttribute("orderId", orderId);
            // 重定向到 订单号页面
            response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");
        }
        // 如果用户未登录就跳转到用户登录页面
        request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);

    }

    /**
     * 发货
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void sndOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取请求参数
        String orderId = request.getParameter("orderId");
        // 2.调用orderService.sendOrder(orderId);
        orderService.sendOrder(orderId);
        // 3.考虑页面需要数据, 保存到域对象中
        String referer = request.getHeader("referer");
        // 4.考虑页面跳转[]
        response.sendRedirect(referer);
    }

    /**
     * 收货
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void receiveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取请求的参数
        String orderId = request.getParameter("orderId");
        // 2.调用OrderService.receiverOrder( orderId );
        orderService.reveiverOrder(orderId);
        // 3.考虑到页面需要的数据 [保存到域中]
        String referer = request.getHeader("referer");
        // 4.页面页面跳转
        response.sendRedirect(referer);
    }

    /**
     * 管理员查询 全部订单列表功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void allOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 使用 orderService.queryAllOrders()查询全部订单
        List<Order> orders = orderService.queryAllOrders();
        // 保存到 request 域中
        request.setAttribute("orders", orders);
        // 请求转发到 订单管理页面
        request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
    }



    /**
     * 查询我的订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void myOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取用户编号
        User user = (User) request.getSession().getAttribute("user");
        // 2.判断用户是否登录
        if (user != null){
            Integer id = user.getId();
            // 3.调用orderService.queryMyOrders() 查询用户订单
            List<Order> orders = orderService.queryMyOrders(id);
            // 4.把数据保存到request 域中
            request.setAttribute("orders", orders);
            // 5.请求转发到我的订单页面
            request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
        }
        // 如果用户未登录就跳转到用户登录页面
        request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);

    }

    /**
     * 管理员订单明细
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void orderDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取请求的参数
        String orderId = request.getParameter("orderId");
        // 2.调用orderService.orderDetails();
        List<OrderItem> orderItems = orderService.queryOrderDetails(orderId);
        // 3.保存到 request 域中
        request.setAttribute("orderItems", orderItems);
        // 4.请求转发到 订单明细页面
        request.getRequestDispatcher("/pages/manager/order_details.jsp").forward(request, response);
    }

    /**
     * 用户订单明细
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void orderDetailsForUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取请求的参数
        String orderId = request.getParameter("orderId");
        // 2.调用orderService.orderDetails();
        List<OrderItem> orderItems = orderService.queryOrderDetails(orderId);
        // 3.保存到 request 域中
        request.setAttribute("orderItems", orderItems);
        // 4.请求转发到 订单明细页面
        request.getRequestDispatcher("/pages/order/order_user_details.jsp").forward(request, response);
    }

}
