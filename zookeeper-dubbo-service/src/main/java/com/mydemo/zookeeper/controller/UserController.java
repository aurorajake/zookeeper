package com.mydemo.zookeeper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class UserController {

	
	@RequestMapping("index")
    public String index(){
		//	http://localhost:8080/zookeeper-dubbo-service/index.do
        return "index";
    }
}
