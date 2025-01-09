package com.example.demo.authUsers.service;

import com.example.demo.authUsers.controllers.required.LoginUser;
import com.example.demo.authUsers.model.EntityUser;

public interface ServiceUser {
    EntityUser registerUser(EntityUser user);

    String loginUser(LoginUser login);
}
