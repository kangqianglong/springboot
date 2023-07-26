package com.chris;

import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/home")
public class axiostest {
	@CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping("")
    String get() {
        //mapped to hostname:port/home/
    	 Date date=new Date();
    	 System.out.println("当前的日期是------>"+date+"收到了3---来自后台的请求处理");
        return "Hello from get2334";
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping("/index1")
  public  String[] index1() {
        //mapped to hostname:port/home/index/
    	 Date date=new Date();
    	 System.out.println("当前的日期是------>"+date+"收到了2111---来自后台的请求处理");
         String[] arrayname =new String [] {"李小龙","bulsus","康强龙","arymy","彼得大帝"};
         return arrayname;      
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping("/index")
  String index2() {
        //mapped to hostname:port/home/index/
    	 Date date=new Date();
    	 System.out.println("当前的日期是------>"+date+"收到了121---来自后台的请求处理");
    	 return "Hello121 from index 我是后台数据";
        
    }
}