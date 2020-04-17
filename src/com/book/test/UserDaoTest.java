package com.book.test;

import com.book.dao.UserDao;
import com.book.dao.impl.UserDaoImpl;
import com.book.pojo.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 测试 UserDao
 * Created by YongXin Xue on 2020/04/11 15:29
 */
public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();

    /**
     * 保存用户信息
     */
    @Test
    public void saveUser() {
        userDao.saveUser(new User(null, "lance", "lance", "lance@163.com"));
    }

    /**
     * 登录操作
     */
    @Test
    public void queryUserByUsernameAndPassword() {
        User user = userDao.queryUserByUsernameAndPassword("lance", "lance");
        if (user != null){
            System.out.println("恭喜你,登录成功!!");
        }else {
            System.out.println("很遗憾,用户名或密码错误,登录失败!!");
        }
    }

    /**
     * 查询用户名是否存在
     */
    @Test
    public void queryUsername() {
        //判断用户名是否可用
        User username = userDao.queryUsernameByUser("lance");
        if (username != null){
            System.out.println("您输入的用户名不可用,可能已经被注册!!");
        }else {
            System.out.println("恭喜你用户名可用!");
        }

    }
}