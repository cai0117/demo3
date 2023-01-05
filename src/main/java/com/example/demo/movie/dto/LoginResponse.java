package com.example.demo.movie.dto;


import com.example.demo.movie.model.MovieCustomer;
import lombok.Data;

@Data
public class LoginResponse {

    private String token;
//    private String refreshToken;
    private MovieCustomer userInfo;
}
