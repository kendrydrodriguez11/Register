package com.example.demo.authUsers.auth.dao.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseLogin<T> {

    private String message;

    private T data;

    private int statusCode;


}
