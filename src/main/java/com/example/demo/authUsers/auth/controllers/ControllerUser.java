package com.example.demo.authUsers.auth.controllers;
import com.example.demo.authUsers.auth.dao.required.CreateUser;
import com.example.demo.authUsers.auth.dao.required.LoginUser;
import com.example.demo.authUsers.auth.dao.response.ApiResponseCreate;
import com.example.demo.authUsers.auth.dao.response.ApiResponseLogin;
import com.example.demo.authUsers.auth.service.ServiceUser;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class ControllerUser {
    private final ServiceUser serviceUser;


    @PostMapping("/register")
    public ResponseEntity<ApiResponseCreate> RegisterUser(@Valid @RequestBody CreateUser request){
        try {
            serviceUser.registerUser(request);
            ApiResponseCreate responseCreate = new ApiResponseCreate("User registered successfully", HttpStatus.CREATED.value());
            return new ResponseEntity<>(responseCreate, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponseCreate responseCreate = new ApiResponseCreate("Error registering user: " + e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(responseCreate);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginUser request) {
        try {
            String token = serviceUser.loginUser(request);
            ApiResponseLogin<String> response = new ApiResponseLogin<>("Login successful", token, HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponseLogin<String> response = new ApiResponseLogin<>("Login failed: " + e.getMessage(), null, HttpStatus.UNAUTHORIZED.value());
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }




}
