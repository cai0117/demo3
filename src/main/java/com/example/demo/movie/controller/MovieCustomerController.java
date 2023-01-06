package com.example.demo.movie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.movie.dto.*;
import com.example.demo.movie.model.MovieCustomer;
import com.example.demo.movie.model.MovieInfo;
import com.example.demo.movie.service.MovieCustomerService;
import com.example.demo.util.CommonResult;
import com.example.demo.util.QueryPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/movie/movieCustomer")
public class MovieCustomerController {



    @Autowired
    private MovieCustomerService movieCustomerService;


    @PostMapping("/login")
    private CommonResult<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse result = movieCustomerService.login(request);
        return CommonResult.success(result);
    }


    @PostMapping("/query")
    private CommonResult<PageOutDto<MovieCustomer>> page(@RequestBody PageInputDto<QueryCustomerDto> input){
        LambdaQueryWrapper<MovieCustomer> wrapper = Wrappers.lambdaQuery();
        //根据条件查询
        wrapper.like(input.getInput().getKeyword() != null, MovieCustomer::getTel, input.getInput().getKeyword())
                .eq(input.getInput().getOrigin() != null, MovieCustomer::getOrigin, input.getInput().getOrigin())
                .or().like(input.getInput().getKeyword() != null,MovieCustomer::getName,input.getInput().getKeyword());
        //分页
        Page<MovieCustomer> page = QueryPageUtil.toQueryPage(input);
        //分页查询
        IPage<MovieCustomer> result = movieCustomerService.page(page,wrapper);
        PageOutDto<MovieCustomer> out;
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

