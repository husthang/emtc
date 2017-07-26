package com.liuhang.domain;

import org.springframework.stereotype.Component;

/**
 * Created by liuhang on 2017/7/26.
 */
@Component
public class HostHolder {
    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public User getUser() {
        return userThreadLocal.get();
    }


    public void setUse(User user) {
        userThreadLocal.set(user);
    }

    public void clear() {
        userThreadLocal.remove();
    }
}
