package com.example.demo.movie.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PageInputDto<T> {
    private Integer size = 10;
    private Integer current = 1;
    private T input;

    @JsonIgnore
    public int getEsFrom() {
        if (current == null || current == 0) return 0;
        if (current < 0) return -1;
        if (size == null) {
            size = 10;
        }
        return (current - 1) * size;
    }

    public Integer getSize() {
        if (size == null || size < 0)  return 10;
        return size;
    }
}
