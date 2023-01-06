package com.example.demo.movie.dto;


import com.example.demo.movie.model.MovieCustomer;
import lombok.Data;

@Data
public class LoginResponse<T> {

    private String token;
//    private String refreshToken;
    private T userInfo;
}
