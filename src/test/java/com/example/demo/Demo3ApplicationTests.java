package com.example.demo;

import com.example.demo.movie.mapper.MovieHotRelaseMapper;
import com.example.demo.movie.model.MovieHotRelase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Demo3ApplicationTests {

    @Autowired
    MovieHotRelaseMapper movieHotRelaseMapper;
    @Test
    public void contextLoads() {
        System.out.println(movieHotRelaseMapper.selectList(null));

    }

}
