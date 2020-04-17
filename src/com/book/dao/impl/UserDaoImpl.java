package com.book.dao.impl;

import com.book.dao.BaseDao;
import com.book.dao.UserDao;
import com.book.pojo.User;

import java.util.List;

/**
 * 实现 UserDao 中的接口
 * Created by YongXin Xue on 2020/04/11 15:25
 */
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`email`) values(?,?,?)";
        return update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select `username`,`password`,`email`,`id` from t_user where username = ? and password = ?";
        return queryForOne(User.class, sql, username,password);
    }

    @Override
    public User queryUsernameByUser(String username) {
        String sql = "select `username`,`password`,`email`,`id` from t_user where username = ?";
        return queryForOne(User.class, sql, username);
    }

    @Override
    public List<User> queryUsers() {
        return null;
    }
}
