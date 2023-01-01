package com.example.demo.movie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.movie.dto.PageInputDto;
import com.example.demo.movie.dto.PageOutDto;
import com.example.demo.movie.dto.QueryMovieHighSourceDto;
import com.example.demo.movie.mapper.MovieHighScoureMapper;
import com.example.demo.movie.model.MovieHighScoure;
import com.example.demo.movie.model.MovieInfo;
import com.example.demo.util.CommonResult;
import com.example.demo.util.QueryPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;

/**
 * <p>
 * 高分电影 前端控制器
 * </p>
 *
 * @author CSW
 * @since 2022-12-30
 */
@RestController
@RequestMapping("/movie/movieHighScoure")
public class MovieHighScoureController {


    @Autowired
    MovieHighScoureMapper movieHighScoureMapper;

    @PostMapping("/query")
    private CommonResult<PageOutDto<MovieHighScoure>> getMovieHighSource(@RequestBody PageInputDto<QueryMovieHighSourceDto> input){
        LambdaQueryWrapper<MovieHighScoure> wrapper = Wrappers.lambdaQuery();
        wrapper.like(input.getInput().getTitle() != null, MovieHighScoure::getTitle, input.getInput().getTitle())
                .eq(input.getInput().getRate() != null, MovieHighScoure::getRate, input.getInput().getRate())
                .like(input.getInput().getDirector() != null,MovieHighScoure::getDirector,input.getInput().getDirector())
                .like(input.getInput().getProtagonist() != null,MovieHighScoure::getProtagonist,input.getInput().getProtagonist())
                .like(input.getInput().getType() != null,MovieHighScoure::getType,input.getInput().getType());
        Page<MovieHighScoure> page = QueryPageUtil.toQueryPage(input);

        if(input.getInput().getStatus() == 2){
            wrapper.orderByDesc(MovieHighScoure::getRate,MovieHighScoure::getRelease);

        }
        IPage<MovieHighScoure> result = movieHighScoureMapper.selectPage(page,wrapper);
        PageOutDto<MovieHighScoure> out;

        if (CollectionUtils.isEmpty(result.getRecords())) {
            out = QueryPageUtil.toPageOut(result, Collections.emptyList());
            return CommonResult.success(out) ;
        }
        out = QueryPageUtil.toPageOut(result,result.getRecords());
        return CommonResult.success(out);
    }
}

