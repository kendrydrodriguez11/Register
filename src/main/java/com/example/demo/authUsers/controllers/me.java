package com.example.demo.authUsers.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prueba")
public class me {
    @GetMapping("/prueba")
    public String prueba() {
        return "Hola mundo!";
    }
}
