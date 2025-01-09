package com.example.demo.authUsers.service;

import com.example.demo.authUsers.model.EntityUser;
import com.example.demo.authUsers.repository.detailsUser.RepositoryDetailsUser;
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
