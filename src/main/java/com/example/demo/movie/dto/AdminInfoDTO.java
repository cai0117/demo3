package com.example.demo.movie.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * 接收前端用户请求数据
 */
@Data
public class AdminInfoDTO {
   @NotBlank(message = "账号不能为空")
    private String account;
    @NotBlank(message = "密码不能为空")
    private String password;
    private String token;
}
