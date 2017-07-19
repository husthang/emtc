package com.liuhang.web;

import com.liuhang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by liuhang on 2017/7/19.
 */
@Controller
public class LoginController {
    @Autowired
    UserService userService;
}
