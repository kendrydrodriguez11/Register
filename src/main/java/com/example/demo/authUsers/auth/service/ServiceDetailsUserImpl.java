package com.example.demo.authUsers.auth.service;

import com.example.demo.authUsers.auth.model.EntityUser;
import com.example.demo.authUsers.auth.repository.detailsUser.RepositoryDetailsUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ServiceDetailsUserImpl implements  ServiceDetailsUser{
    private final  RepositoryDetailsUser repositoryDetailsUser;

    @Override
    public EntityUser findByName(String userName) {
        return repositoryDetailsUser.UserName(userName);
    }
}
