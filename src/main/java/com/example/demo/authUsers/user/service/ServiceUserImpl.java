package com.example.demo.authUsers.user.service;

import com.example.demo.authUsers.user.dao.required.CreateUser;
import com.example.demo.authUsers.user.dao.required.LoginUser;
import com.example.demo.authUsers.user.model.EntityRole;
import com.example.demo.authUsers.user.model.EntityUser;
import com.example.demo.authUsers.user.model.RoleU;
import com.example.demo.authUsers.user.repository.RepositoryUser;
import com.example.demo.authUsers.user.service.jwt.ServiceToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ServiceUserImpl implements ServiceUser {
    private final RepositoryUser repositoryUser;
    private final AuthenticationManager authenticationManager;
    private final ServiceToken createToken;
    private final ServiceDetailsUser serviceDetailsUser;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(CreateUser request) {
        Set<EntityRole> roles = request.getRole().stream()
                .map(Userrole -> EntityRole.builder()
                        .nameRole(RoleU.valueOf(Userrole))
                        .build())
                .collect(Collectors.toSet());

        EntityUser user = EntityUser.builder()
                .nameUser(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountCreating(LocalDate.now())
                .roleUser(roles)
                .build();

        repositoryUser.save(user);

    }

    @Override
    public String loginUser(LoginUser login) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getName(), login.getPassword()));
        EntityUser user = serviceDetailsUser.findByName(login.getName());
        return  createToken.createToken(user);
    }
}
