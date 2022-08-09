package com.example.demo.fruit.dto;

import lombok.Data;

/**
 * 接收前端用户请求数据
 */
@Data
public class AdminInfoDTO {
    private String name;
    private String password;
    private String token;
}
