package com.mydemo.zookeeper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydemo.zookeeper.dao.UserDAO;
import com.mydemo.zookeeper.model.user.User;
import com.mydemo.zookeeper.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
    private UserDAO userDAO;
	 
	@Override
	public int insertUser(User user) {
		return userDAO.insertUser(user);
	}

}
