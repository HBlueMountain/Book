package com.book.service.impl;

import com.book.dao.UserDao;
import com.book.dao.impl.UserDaoImpl;
import com.book.pojo.User;
import com.book.service.UserService;

/**
 * 实现用功能
 * Created by YongXin Xue on 2020/04/11 15:51
 */
public class UserServiceImpl implements UserService {

    //UserService 需要 UserDao ,又叫 依赖
    private UserDao userDao = new UserDaoImpl();


    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        User user = userDao.queryUsernameByUser(username);
        if (user != null){
            //用户名存在 不可用
            return true;
        }
        //用户名不存在 可用
        return false;
    }

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }
}
