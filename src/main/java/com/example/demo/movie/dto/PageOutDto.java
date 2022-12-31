package com.example.demo.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageOutDto<T> {
    private Integer total = 0;
    private Integer size = 10;
    private Integer current = 1;
    private List<T> records;
}
