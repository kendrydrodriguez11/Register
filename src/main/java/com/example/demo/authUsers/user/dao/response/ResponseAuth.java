package com.example.demo.authUsers.user.dao.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseAuth {
    String message;

    Object data;
}
