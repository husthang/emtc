package com.liuhang.web;

import com.liuhang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liuhang on 2017/7/19.
 */
@Controller
public class LoginController {
    @Autowired
    UserService userService;

//    @RequestMapping

}
