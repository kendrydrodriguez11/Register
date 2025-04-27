package com.example.demo.authUsers.auth.dao.required;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CreateUser {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private Set<String> role;
}
