package com.book.web;

import com.book.pojo.User;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import com.book.utils.WebUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.jasper.tagplugins.jstl.core.If;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * 用户的 View 层
 * Created by YongXin Xue on 2020/04/16 10:11
 */
@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /**
     * 定义 register 方法处理注册业务
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void register(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        System.out.println("可以注册了!!!!");
        // 1.获取表单信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        //使用 BeanUtils 工具类
        User user = WebUtils.copyParamToBean(new User(), request.getParameterMap());

        //假设验证码是 : [ABCDE]
        // 2.检查验证码是否正确
        if ("ABCDE".equalsIgnoreCase(code)){

            // 3.检查用户名是否存在
            if (userService.existsUsername(username)){

                //把错误信息和回显表单信息,保存到request域中
                request.setAttribute("msg", "用户名已存在!");
                request.setAttribute("username", username);
                request.setAttribute("email", email);

                //用户名已存在,不可用 ===> 跳回注册页面 regist.jsp
                System.out.println("用户名 ["+ username +"] 已存在,不可用!!");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            }else {
                //用户名可用,保存用户名
                userService.registerUser(new User(null, username, password, email));
                //跳转到注册成功页面
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
            }
        }else {
            //把错误信息和回显表单信息,保存到request域中
            request.setAttribute("msg", "用户名已存在!");
            request.setAttribute("username", username);
            request.setAttribute("email", email);

            //验证码错误
            System.out.println("验证码 [" + code + "] 错误!");
            //跳回注册页面
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }
    }

    /**
     * 定义 login 方法处理登录的业务
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 2.调用userService.login():User检查用户登录
        User loginUser = userService.login(new User(null, username, password, null));
        // 3.根据login的返回结果判断用户是否登录成功
        if (loginUser != null){
            // 1.登录成功
            System.out.println("登录成功!!");
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
        }else {

            // 保存错误信息,和表单项信息,到 request 域中,给 jsp 页面回显使用
            request.setAttribute("msg", "用户名或密码错误!");
            request.setAttribute("username", username);

            // 2.登录失败
            System.out.println("登录失败!!");
            // 跳回登录页面
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }
    }
}
