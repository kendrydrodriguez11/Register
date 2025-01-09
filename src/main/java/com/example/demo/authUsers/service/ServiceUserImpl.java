package com.example.demo.authUsers.service;

import com.example.demo.authUsers.controllers.required.LoginUser;
import com.example.demo.authUsers.model.EntityUser;
import com.example.demo.authUsers.repository.RepositoryUser;
import com.example.demo.authUsers.service.jwt.ServiceToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ServiceUserImpl implements ServiceUser {
    private final RepositoryUser repositoryUser;
    private final AuthenticationManager authenticationManager;
    private final ServiceToken createToken;
    private final ServiceDetailsUser serviceDetailsUser;

    @Override
    public EntityUser registerUser(EntityUser user) {
        return repositoryUser.save(user);
    }

    @Override
    public String loginUser(LoginUser login) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getName(), login.getPassword()));
        EntityUser user = serviceDetailsUser.findByName(login.getName());
        return  createToken.createToken(user);
    }
}
