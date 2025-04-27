package com.example.demo.authUsers.auth.service;

import com.example.demo.authUsers.auth.dao.required.CreateUser;
import com.example.demo.authUsers.auth.dao.required.LoginUser;
import com.example.demo.authUsers.auth.model.EntityRole;
import com.example.demo.authUsers.auth.model.EntityUser;
import com.example.demo.authUsers.auth.model.RoleU;
import com.example.demo.authUsers.auth.repository.RepositoryUser;
import com.example.demo.authUsers.auth.service.jwt.ServiceToken;
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
        try{
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
        }catch (Exception e){
            System.out.printf(e.getMessage());
        }
    }

    @Override
    public String loginUser(LoginUser login) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getName(), login.getPassword()));
        EntityUser user = serviceDetailsUser.findByName(login.getName());
        return  createToken.createToken(user);
    }
}
