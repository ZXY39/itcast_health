package com.zgasq.service;

import com.zgasq.pojo.User;

public interface UserService {
    public User findByUsername(String username);
}
