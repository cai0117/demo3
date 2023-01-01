package com.example.demo.movie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.movie.dto.PageInputDto;
import com.example.demo.movie.dto.PageOutDto;
import com.example.demo.movie.dto.QueryMovieHighSourceDto;
import com.example.demo.movie.dto.QueryMovieInfoDto;
import com.example.demo.movie.mapper.MovieInfoMapper;
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
    private CommonResult<PageOutDto<MovieInfo>> getMovieInfo(@RequestBody PageInputDto<QueryMovieHighSourceDto> input){
        LambdaQueryWrapper<MovieInfo> wrapper = Wrappers.lambdaQuery();
        //根据条件查询
        wrapper.like(input.getInput().getTitle() != null, MovieInfo::getTitle, input.getInput().getTitle())
                .eq(input.getInput().getRate() != null, MovieInfo::getRate, input.getInput().getRate())
                .like(input.getInput().getDirector() != null,MovieInfo::getDirector,input.getInput().getDirector())
                .like(input.getInput().getProtagonist() != null,MovieInfo::getProtagonist,input.getInput().getProtagonist())
                .like(input.getInput().getType() != null,MovieInfo::getType,input.getInput().getType());
        //分页
        Page<MovieInfo> page = QueryPageUtil.toQueryPage(input);
        //分页查询
        IPage<MovieInfo> result = movieInfoMapper.selectPage(page,wrapper);
        PageOutDto<MovieInfo> out;
//        List<MovieInfo> collect = result.getRecords().stream().map(c -> getDtoById(c.getMovieId())).collect(Collectors.toList());
        //封装返回给前端的格式
        if (CollectionUtils.isEmpty(result.getRecords())) {
            out = QueryPageUtil.toPageOut(result,Collections.emptyList());
            return CommonResult.success(out) ;
        }
        out = QueryPageUtil.toPageOut(result,result.getRecords());
        return CommonResult.success(out);
    }

    @PostMapping("/page")
    private CommonResult<PageOutDto<MovieInfo>> getMovieInfoPage(@RequestBody PageInputDto<QueryMovieHighSourceDto> input){
        LambdaQueryWrapper<MovieInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.like(input.getInput().getTitle() != null, MovieInfo::getTitle, input.getInput().getTitle())
                .eq(input.getInput().getRate() != null, MovieInfo::getRate, input.getInput().getRate())
                .like(input.getInput().getDirector() != null,MovieInfo::getDirector,input.getInput().getDirector())
                .like(input.getInput().getProtagonist() != null,MovieInfo::getProtagonist,input.getInput().getProtagonist())
                .like(input.getInput().getType() != null,MovieInfo::getType,input.getInput().getType());
        Page<MovieInfo> page = QueryPageUtil.toQueryPage(input);

        if(input.getInput().getStatus() == 1){
            wrapper.orderByDesc(MovieInfo::getEvaluateNum,MovieInfo::getRate);
        } else if (input.getInput().getStatus() == 2) {
            wrapper.orderByDesc(MovieInfo::getRate,MovieInfo::getRelease);
        }
        IPage<MovieInfo> result = movieInfoMapper.selectPage(page,wrapper);
        PageOutDto<MovieInfo> out;

        if (CollectionUtils.isEmpty(result.getRecords())) {
            out = QueryPageUtil.toPageOut(result, Collections.emptyList());
            return CommonResult.success(out) ;
        }
        out = QueryPageUtil.toPageOut(result,result.getRecords());
        return CommonResult.success(out);
    }
//    public MovieInfo getDtoById(Integer id) {
//        MovieInfo entity = movieInfoMapper.selectById(id);
//        if (entity == null) return null;
//        MovieInfo dto = entity;
//        return dto;
//    }
}

