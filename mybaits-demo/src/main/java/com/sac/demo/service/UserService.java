package com.sac.demo.service;

import org.springframework.stereotype.Service;
import com.sac.demo.mapper.UserMapper;
import com.sac.demo.entity.User;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
//@Service("UserService")
public class UserService {
  @Autowired
 // @Resource(name = "studentMapper")
  UserMapper userMapper;

  public int insertUser(User user) {
    return userMapper.insertUser(user);
  }

  public User getByUserNameAndPassword(User user) {
    return userMapper.getByUserNameAndPassword(user);
  }
  
//获得数据库中Student表的所有数据
  public List getStudent() throws Exception {
      return userMapper.selectStudentAll();
  }
}