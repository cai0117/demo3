package com.example.demo.movie.controller;


import com.example.demo.movie.mapper.MovieSysDictMapper;
import com.example.demo.movie.model.MovieSysDict;
import com.example.demo.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 字典 前端控制器
 * </p>
 *
 * @author CSW
 * @since 2022-12-30
 */
@RestController
@RequestMapping("/movie/movieSysDict")
public class MovieSysDictController {

    @Autowired
    MovieSysDictMapper movieSysDictMapper;

    @GetMapping("/dict")
    private CommonResult<List<MovieSysDict>> getDictList(){
        List<MovieSysDict> res =  movieSysDictMapper.selectList(null);

        return CommonResult.success(res);
    }

}

