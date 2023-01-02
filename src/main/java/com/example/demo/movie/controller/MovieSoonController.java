package com.example.demo.movie.controller;


import com.example.demo.movie.mapper.MovieSoonMapper;
import com.example.demo.movie.model.MovieSoon;
import com.example.demo.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 即将上映电影 前端控制器
 * </p>
 *
 * @author CSW
 * @since 2022-12-30
 */
@RestController
@RequestMapping("/movie/movieSoon")
public class MovieSoonController {

    @Autowired
    MovieSoonMapper movieSoonMapper;

    @GetMapping("/list")
    private CommonResult<List<MovieSoon>> getMovieSoon(){
        List<MovieSoon> res =  movieSoonMapper.selectList(null);
        return CommonResult.success(res);
    }
}


