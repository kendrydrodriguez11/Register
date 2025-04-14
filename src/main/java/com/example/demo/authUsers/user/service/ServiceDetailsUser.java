package com.example.demo.authUsers.user.service;

import com.example.demo.authUsers.user.model.EntityUser;

public interface ServiceDetailsUser {
    EntityUser findByName (String userName);
}
