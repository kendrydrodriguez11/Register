package com.example.demo.authUsers.auth.dao.required;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class LoginUser {
    @NotBlank
    private String name;

    @NotBlank
    private String password;
}
