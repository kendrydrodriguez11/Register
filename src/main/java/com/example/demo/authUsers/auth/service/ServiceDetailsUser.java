package com.example.demo.authUsers.auth.service;

import com.example.demo.authUsers.auth.model.EntityUser;

public interface ServiceDetailsUser {
    EntityUser findByName (String userName);
}
