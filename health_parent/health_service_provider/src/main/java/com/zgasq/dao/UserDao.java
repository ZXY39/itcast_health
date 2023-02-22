package com.zgasq.dao;

import com.zgasq.pojo.User;

public interface UserDao {
    public User findByUsername(String username);
}
