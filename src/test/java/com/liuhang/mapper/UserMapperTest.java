package com.liuhang.mapper;

import com.liuhang.Application;
import com.liuhang.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuhang on 2017/7/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testAdd() throws Exception {
        userMapper.addUser(new User(1, "liu1", "123", "sal", "headurl"));
    }

}
