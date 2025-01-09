package com.example.demo.authUsers.controllers;
import com.example.demo.authUsers.controllers.required.CreateUser;
import com.example.demo.authUsers.controllers.required.LoginUser;
import com.example.demo.authUsers.model.EntityRole;
import com.example.demo.authUsers.model.EntityUser;
import com.example.demo.authUsers.model.RoleU;
import com.example.demo.authUsers.service.ServiceUser;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("api/users")
public class ControllerUser {
    private final ServiceUser serviceUser;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public EntityUser RegisterUser(@Valid @RequestBody CreateUser request){
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

        return serviceUser.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginUser request ){
        return ResponseEntity.ok(serviceUser.loginUser(request));
    }
}
