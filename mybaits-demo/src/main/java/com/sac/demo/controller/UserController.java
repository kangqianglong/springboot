package com.sac.demo.controller;

import com.sac.demo.entity.User;
import com.sac.demo.entity.*;
import com.sac.demo.service.UserService;
//import com.mysql.cj.jdbc.Driver;
//import com.mysql.jdbc.Driver;

import javax.annotation.Resource;

import org.apache.coyote.http11.filters.VoidInputFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
//@Controller("UserController")
//@RequestMapping("studentCtr")
public class UserController {
  @Autowired
  //@Resource(name = "UserService")
  private UserService userService;

  @PostMapping("user/insert")
  public Response insertUser(@RequestBody User user) {
    try {
      int result = userService.insertUser(user);
      System.out.println("当前的日期是------>收到了1---来自后台的请求处理");
      return Response.success(result);
    } catch(Exception e) {
      return Response.failure(500, "服务器异常");
    }
  }

  @PostMapping("user/getByUserNameAndPassword")
  public Response getByUserNameAndPassword(@RequestBody User user) {
    try {
      User result = userService.getByUserNameAndPassword(user);
      return Response.success(result);
    } catch(Exception e) {
      return Response.failure(500, "服务器异常");
    }
  }
  
  
  //获得数据库中Student表的所有数据
  @RequestMapping("/studentAll")
  public List studentAll() throws Exception {
      return userService.getStudent();
  }
  
  @GetMapping(value="/test21")
  public String axiostest() {
 	 //使用Date创建日期对象
 	  Date date=new Date();
 	 System.out.println("当前的日期是------>"+date+"收到了1---来自后台的请求处理");
 	 return "4455nihaoa23232!!@22";
  }
}