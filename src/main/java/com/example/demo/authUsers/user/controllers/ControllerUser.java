package com.example.demo.authUsers.user.controllers;
import com.example.demo.authUsers.user.dao.required.CreateUser;
import com.example.demo.authUsers.user.dao.required.LoginUser;
import com.example.demo.authUsers.user.dao.response.ResponseAuth;
import com.example.demo.authUsers.user.model.EntityRole;
import com.example.demo.authUsers.user.model.EntityUser;
import com.example.demo.authUsers.user.model.RoleU;
import com.example.demo.authUsers.user.service.ServiceUser;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
@RequestMapping("api/users")
public class ControllerUser {
    private final ServiceUser serviceUser;


    @PostMapping("/register")
    public ResponseEntity<ResponseAuth> RegisterUser(@Valid @RequestBody CreateUser request){
        try{
            serviceUser.registerUser(request);
            return new ResponseEntity<>(new ResponseAuth("User registered successfully", null), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseAuth("Error registering user", null), HttpStatus.CREATED);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginUser request, HttpServletResponse response) {
        try {
            String token = serviceUser.loginUser(request);
            return ResponseEntity.ok(new ResponseAuth("Login successful", token));

        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseAuth("Login failed: " + e.getMessage(), null), HttpStatus.UNAUTHORIZED);
        }
    }




}
