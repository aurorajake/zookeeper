package com.mydemo.zookeeper.dao;

import com.mydemo.zookeeper.model.user.User;

public interface UserDAO {

	/**
     * 添加新用户
     * @param user
     * @return
     */
    public int insertUser(User user);
}
