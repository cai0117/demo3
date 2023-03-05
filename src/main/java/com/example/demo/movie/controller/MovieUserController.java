package com.example.demo.movie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.movie.dto.*;
import com.example.demo.movie.model.MovieUser;
import com.example.demo.movie.service.MovieUserService;
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
@RequestMapping("/movie/movieUser")
public class MovieUserController {


    @Autowired
    MovieUserService movieUserService;

    @PostMapping("/login")
    private CommonResult<LoginResponse<MovieUser>> login(@RequestBody UserDto request){
        LoginResponse<MovieUser> result =  movieUserService.login(request);
        return CommonResult.success(result);
    }

    @PostMapping("/query")
    private CommonResult<PageOutDto<MovieUser>> page(@RequestBody PageInputDto<Employee> input){
        LambdaQueryWrapper<MovieUser> wrapper = Wrappers.lambdaQuery();
        //根据条件查询
        wrapper.like(input.getInput().getName() != null, MovieUser::getName, input.getInput().getName())
                .like(input.getInput().getTel() != null, MovieUser::getName, input.getInput().getTel())
                .or().eq(input.getInput().getStatus() != null,MovieUser::getStatus,input.getInput().getStatus());
        //分页
        Page<MovieUser> page = QueryPageUtil.toQueryPage(input);
        //分页查询
        IPage<MovieUser> result = movieUserService.page(page,wrapper);
        PageOutDto<MovieUser> out;
//        List<MovieInfo> collect = result.getRecords().stream().map(c -> getDtoById(c.getMovieId())).collect(Collectors.toList());
        //封装返回给前端的格式
        if (CollectionUtils.isEmpty(result.getRecords())) {
            out = QueryPageUtil.toPageOut(result, Collections.emptyList());
            return CommonResult.success(out) ;
        }
        out = QueryPageUtil.toPageOut(result,result.getRecords());
        return CommonResult.success(out);
    }

}

