package com.book.dao;

import com.book.pojo.User;

import java.util.List;

/**
 * Created by YongXin Xue on 2020/04/11 15:23
 */
public interface UserDao {

    /**
     * 保存用户
     * @param user
     * @return
     */
    public int saveUser(User user);

    /**
     * 感觉用户名和密码查询用户
     * @param username  用户名
     * @param password  密码
     * @return  用户信息
     */
    public User queryUserByUsernameAndPassword(String username, String password);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return  查询到的用户
     */
    public User queryUsernameByUser(String username);

    /**
     * 查询多个
     * @return
     */
    public List<User> queryUsers();
}
