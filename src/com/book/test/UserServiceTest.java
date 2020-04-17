package com.book.test;

import com.book.pojo.User;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 测试用户业务功能
 * Created by YongXin Xue on 2020/04/12 20:13
 */
public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void login() {
        User loginUser = userService.login(new User(null, "lance", "lance", null));
        if (loginUser != null){
            System.out.println("恭喜你,登录成功!!");
        }else {
            System.out.println("很遗憾,用户名或者密码错误,登录失败!!");
        }

    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("lance")){
            System.out.println("很遗憾,用户名不可用或已被注册!!".trim());
        }else{
            System.out.println("恭喜你,用户名可用!");
        }
    }

    @Test
    public void registerUser() {
        userService.registerUser(new User(null, "Arison", "qwerhjkl","arison@163.com"));
    }
}