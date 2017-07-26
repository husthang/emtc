package com.liuhang.service;

import com.liuhang.domain.User;
import com.liuhang.mapper.UserMapper;
import com.liuhang.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by liuhang on 2017/7/19.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    public void login(String userName, String password) {
        User user = userMapper.selectUserByName(userName);

    }

    public Map<String, Object> register(String userName, String password) {
        Map<String, Object> map = new HashMap<>();

        User user = userMapper.selectUserByName(userName);
        if (user == null) {
            map.put("msg", "用户名已注册");
        }

        user = new User();
        user.setName(userName);
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));
        user.setPassword(MD5Util.MD5(password + user.getSalt()));

        userMapper.addUser(user);

        return map;

    }

}
