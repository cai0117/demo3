package com.example.demo.movie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.movie.dto.OrderDto;
import com.example.demo.movie.dto.PageInputDto;
import com.example.demo.movie.dto.PageOutDto;
import com.example.demo.movie.model.MovieOrder;
import com.example.demo.movie.service.MovieOrderService;
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
 * 订单 前端控制器
 * </p>
 *
 * @author CSW
 * @since 2023-03-04
 */
@RestController
@RequestMapping("/movie/movieOrder")
public class MovieOrderController {

    @Autowired
    MovieOrderService movieOrderService;

    @PostMapping("/query")
    private CommonResult<PageOutDto<MovieOrder>> page(@RequestBody PageInputDto<OrderDto> input){
        LambdaQueryWrapper<MovieOrder> wrapper = Wrappers.lambdaQuery();
        //根据条件查询
        wrapper.like(input.getInput().getOrderId() != null, MovieOrder::getOrderId, input.getInput().getOrderId())
                .like(input.getInput().getCustomerId() != null, MovieOrder::getCustomerId, input.getInput().getCustomerId())
                .eq(input.getInput().getPayStatus() != null, MovieOrder::getPayStatus,input.getInput().getPayStatus())
                .like(input.getInput().getTitle() != null, MovieOrder::getMovie, input.getInput().getTitle());
        //分页
        Page<MovieOrder> page = QueryPageUtil.toQueryPage(input);
        //分页查询
        IPage<MovieOrder> result = movieOrderService.page(page,wrapper);
        PageOutDto<MovieOrder> out;
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

