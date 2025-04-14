package com.example.demo.authUsers.user.service;

import com.example.demo.authUsers.user.dao.required.CreateUser;
import com.example.demo.authUsers.user.dao.required.LoginUser;
import com.example.demo.authUsers.user.model.EntityUser;

public interface ServiceUser {
    void registerUser(CreateUser request);

    String loginUser(LoginUser login);
}
