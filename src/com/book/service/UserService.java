package com.book.service;

import com.book.pojo.User;

/**
 * 用户业务接口
 * Created by YongXin Xue on 2020/04/11 15:48
 */
public interface UserService {

    /**
     * 登录
     * @param user
     */
    public User login(User user);

    /**
     * 判断用户名是否存在
     * @param username 查询用户名
     * @return  false表示用户名不存在 可用
     *          true表示用户名存在 不可用
     */
    public boolean existsUsername(String username);

    /**
     * 注册用户
     * @param user
     */
    public void registerUser(User user);
}
