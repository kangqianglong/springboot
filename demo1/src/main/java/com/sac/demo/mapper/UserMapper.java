package com.sac.demo.mapper;

import com.sac.demo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
   int insertUser(User user);
   
   User getByUserNameAndPassword(User user);
}
