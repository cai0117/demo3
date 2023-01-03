package com.example.demo.movie.controller;


import com.example.demo.movie.model.MovieHotRelase;
import com.example.demo.movie.service.MovieHotRelaseService;
import com.example.demo.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 热映 前端控制器
 * </p>
 *
 * @author CSW
 * @since 2022-12-30
 */
@RestController
@RequestMapping("/movie/movieHotRelase")
public class MovieHotRelaseController {

    @Autowired
    private MovieHotRelaseService movieHotRelaseService;


    @GetMapping("/list")
    private CommonResult<List<MovieHotRelase>> list(){

        List<MovieHotRelase> result = movieHotRelaseService.getList();

        return CommonResult.success(result);
    }

    @GetMapping("/{id}")
    private CommonResult<MovieHotRelase> getHotMovie(@PathVariable("id") Integer id){
        MovieHotRelase result = movieHotRelaseService.getById(id);
        return CommonResult.success(result);
    }

}

