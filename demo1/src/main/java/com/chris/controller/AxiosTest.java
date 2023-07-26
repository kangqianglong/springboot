package com.chris.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/system1/about1")
public class AxiosTest {
     public void axiostest() {
    	 System.out.println("我收到了---来自后台的请求处理");
    	 
     }
}
