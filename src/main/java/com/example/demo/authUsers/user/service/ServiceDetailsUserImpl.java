package com.example.demo.authUsers.user.service;

import com.example.demo.authUsers.user.model.EntityUser;
import com.example.demo.authUsers.user.repository.detailsUser.RepositoryDetailsUser;
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
