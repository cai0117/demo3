package com.example.demo.movie.controller;


import com.example.demo.movie.dto.LoginResponse;
import com.example.demo.movie.dto.UserDto;
import com.example.demo.movie.model.MovieUser;
import com.example.demo.movie.service.MovieUserService;
import com.example.demo.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

}

