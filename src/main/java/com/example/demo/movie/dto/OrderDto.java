package com.example.demo.movie.dto;


import lombok.Data;

@Data
public class OrderDto {

    private Integer orderId;

    private Integer payStatus;

    private Integer customerId;

    private String title;
}
