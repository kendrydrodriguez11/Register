package com.example.demo.authUsers.service;

import com.example.demo.authUsers.model.EntityUser;

public interface ServiceDetailsUser {
    EntityUser findByName (String userName);
}
