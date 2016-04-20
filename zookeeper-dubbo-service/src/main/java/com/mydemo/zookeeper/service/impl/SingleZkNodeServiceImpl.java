package com.mydemo.zookeeper.service.impl;

import org.springframework.stereotype.Service;

import com.mydemo.zookeeper.service.SingleZkNodeService;

@Service("singleZkNodeService")
public class SingleZkNodeServiceImpl implements SingleZkNodeService {

	@Override
	public String print() {
		System.out.println("hello ZooKeeper Service !!!");
		return null;
	}

}
