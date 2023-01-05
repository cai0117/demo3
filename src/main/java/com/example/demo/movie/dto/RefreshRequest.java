package com.example.demo.movie.dto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RefreshRequest {

    @Parameter(description = "用户id")
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    @Parameter(description = "刷新token")
    @NotNull(message = "refreshToken不能为空")
    private String refreshToken;

}
