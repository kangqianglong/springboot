package com.sac.demo.service;

import org.springframework.stereotype.Service;
import com.sac.demo.mapper.UserMapper;
import com.sac.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {
  @Autowired
  UserMapper userMapper;

  public int insertUser(User user) {
    return userMapper.insertUser(user);
  }

  public User getByUserNameAndPassword(User user) {
    return userMapper.getByUserNameAndPassword(user);
  }
}