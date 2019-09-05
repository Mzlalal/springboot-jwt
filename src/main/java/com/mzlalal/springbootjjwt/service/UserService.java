package com.mzlalal.springbootjjwt.service;

import com.mzlalal.springbootjjwt.entity.User;
import com.mzlalal.springbootjjwt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:  用户服务
 * @author:       Mzlalal
 * @date:         2019/9/5 14:47
 * @version:      1.0
 */
@Service("UserService")
public class UserService {
    @Autowired
    UserMapper userMapper;
    public User findByUsername(User user){
        return userMapper.findByUsername(user.getUsername());
    }
    public User findUserById(String userId) {
        return userMapper.findUserById(userId);
    }

}
