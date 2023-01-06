package com.example.demo.movie.service;

import com.example.demo.movie.dto.LoginResponse;
import com.example.demo.movie.dto.UserDto;
import com.example.demo.movie.model.MovieUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CSW
 * @since 2022-12-29
 */
public interface MovieUserService extends IService<MovieUser> {

    LoginResponse<MovieUser> login(UserDto request);
}
