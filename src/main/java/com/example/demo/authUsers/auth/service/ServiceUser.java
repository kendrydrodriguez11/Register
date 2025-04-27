package com.example.demo.authUsers.auth.service;

import com.example.demo.authUsers.auth.dao.required.CreateUser;
import com.example.demo.authUsers.auth.dao.required.LoginUser;

public interface ServiceUser {
    void registerUser(CreateUser request);

    String loginUser(LoginUser login);
}
