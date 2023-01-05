package com.example.demo.movie.controller;


import com.example.demo.movie.dto.LoginRequest;
import com.example.demo.movie.dto.LoginResponse;
import com.example.demo.movie.model.MovieCustomer;
import com.example.demo.movie.service.MovieCustomerService;
import com.example.demo.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/movie/movieCustomer")
public class MovieCustomerController {



    @Autowired
    private MovieCustomerService movieCustomerService;


    @PostMapping("/login")
    private CommonResult<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse result = movieCustomerService.login(request);
        return CommonResult.success(result);
    }


}

