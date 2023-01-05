package com.example.demo.movie.service;

import com.example.demo.movie.dto.LoginRequest;
import com.example.demo.movie.dto.LoginResponse;
import com.example.demo.movie.model.MovieCustomer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CSW
 * @since 2022-12-29
 */
public interface MovieCustomerService extends IService<MovieCustomer> {

    LoginResponse login(LoginRequest request);
}
