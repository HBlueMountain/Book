package com.book.web;

import com.book.pojo.Book;
import com.book.pojo.Cart;
import com.book.pojo.CartItems;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YongXin Xue on 2020/04/21 21:32
 */
@WebServlet(name = "CartServlet", urlPatterns = "/cart")
public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 添加商品到购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
   /* protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("添加商品到购物车!");
        // 1.获取商品编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        // 2.通过BookService.queryById(id) : Book 获取图书信息
        Book book = bookService.queryBookId(id);
        // 3.把 Book 转换为 CartItem
        CartItems cartItems = new CartItems(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        // 4.获取Cart 购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
           request.getSession().setAttribute("cart", cart);
        }
        // 5.调用 cart.addItem(CartItem) 添加商品项
        cart.addItems(cartItems);
        System.out.println(cart);

        // 保存到 Session 中, 最后一个添加的商品 [购物车添加首页商品的回显]
        request.getSession().setAttribute("last_name", cartItems.getName());

        // 6.跳转到添加商品的页面 注意:[根据请求头跳回添加商品的界面] [重要]
        response.sendRedirect(request.getHeader("referer"));
    }*/

    /**
     * 使用Ajax请求将图书添加到购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("添加商品到购物车!");
        // 1.获取商品编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        // 2.通过BookService.queryById(id) : Book 获取图书信息
        Book book = bookService.queryBookId(id);
        // 3.把 Book 转换为 CartItem
        CartItems cartItems = new CartItems(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        // 4.获取Cart 购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
           request.getSession().setAttribute("cart", cart);
        }
        // 5.调用 cart.addItem(CartItem) 添加商品项
        cart.addItems(cartItems);
        System.out.println(cart);

        // 保存到 Session 中, 最后一个添加的商品 [购物车添加首页商品的回显]
        request.getSession().setAttribute("last_name", cartItems.getName());

        Map<String, Object> map = new HashMap<>();
        //购物车中商品数量
        map.put("totalCount", cart.getTotalCount());
        //最后一个添加的商品
        map.put("last_name", cartItems.getName());
        Gson gson = new Gson();
        String json = gson.toJson(map);
        response.getWriter().write(json);

    }

    /**
     * 清空购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        // 2.调用 cat.clear() 清空购物车
        if (cart != null){
            // 清空
            cart.clear();
        }
        // 3.重定向到购物车页面
        response.sendRedirect(request.getHeader("referer"));
    }

    /**
     * 删除购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取请求的参数,图书编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        // 2.获取 Cart 购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        // 3.调用 cart.deleteItem(); 删除商品项
        if (cart != null){
            cart.deleteItems(id);
            // 4.跳回原来的购物车页面
            response.sendRedirect(request.getHeader("referer"));
        }
    }

    /**
     *  修改购物车商品数量
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取请求参数,商品编号和新数量
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        int count = WebUtils.parseInt(request.getParameter("count"), 1);
        // 2.获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null){
            // 3.修改商品数量
            cart.updateCount(id, count);
            // 4.重定向到购物车页面
            response.sendRedirect(request.getHeader("referer"));
        }
    }
}
