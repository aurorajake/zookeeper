package com.mydemo.zookeeper.service.impl;

import org.springframework.stereotype.Service;

import com.mydemo.zookeeper.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Override
	public String sayHello(String name) {
		return "hello "+(name == null ? "":name);
	}
	
}
