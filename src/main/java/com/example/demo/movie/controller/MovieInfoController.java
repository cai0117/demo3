package com.example.demo.movie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.movie.dto.PageInputDto;
import com.example.demo.movie.dto.PageOutDto;
import com.example.demo.movie.dto.QueryMovieInfoDto;
import com.example.demo.movie.mapper.MovieInfoMapper;
import com.example.demo.movie.model.MovieInfo;
import com.example.demo.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CSW
 * @since 2022-12-29
 */
@RestController
@RequestMapping("/movie/movieInfo")
public class MovieInfoController {

    @Autowired
    MovieInfoMapper movieInfoMapper;

    @PostMapping("/query")
    private CommonResult<PageOutDto<MovieInfo>> getMovieInfo(@RequestBody PageInputDto<QueryMovieInfoDto> input){
        LambdaQueryWrapper<MovieInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(input.getInput().getTitle() != null, MovieInfo::getTitle, input.getInput().getTitle())
                .eq(input.getInput().getRate() != null, MovieInfo::getRate, input.getInput().getRate());
        Page<MovieInfo> page = Page.of(input.getCurrent(), input.getSize());
        IPage<MovieInfo> result;
        if (input == null) {
            result = movieInfoMapper.selectPage(null,wrapper);
        }else{
            result = movieInfoMapper.selectPage(page,wrapper);
        }
        PageOutDto<MovieInfo> out = new PageOutDto<>();
//        List<MovieInfo> collect = result.getRecords().stream().map(c -> getDtoById(c.getMovieId())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(result.getRecords())) {
            out.setCurrent(((Long)result.getCurrent()).intValue());
            out.setSize(((Long)result.getSize()).intValue());
            out.setTotal(((Long)result.getTotal()).intValue());
            out.setRecords(Collections.emptyList());
            return CommonResult.success(out) ;
        }
        out.setCurrent(((Long)result.getCurrent()).intValue());
        out.setSize(((Long)result.getSize()).intValue());
        out.setTotal(((Long)result.getTotal()).intValue());
        out.setRecords(result.getRecords());
        return CommonResult.success(out);
    }
//    public MovieInfo getDtoById(Integer id) {
//        MovieInfo entity = movieInfoMapper.selectById(id);
//        if (entity == null) return null;
//        MovieInfo dto = entity;
//        return dto;
//    }
}

