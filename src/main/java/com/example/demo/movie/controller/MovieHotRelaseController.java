package com.example.demo.movie.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.movie.dto.HotReleaseDto;
import com.example.demo.movie.dto.PageInputDto;
import com.example.demo.movie.model.MovieHotRelase;
import com.example.demo.movie.service.MovieHotRelaseService;
import com.example.demo.util.CommonPage;
import com.example.demo.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}

