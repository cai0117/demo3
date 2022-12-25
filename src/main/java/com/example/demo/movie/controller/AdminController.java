package com.example.demo.movie.controller;


import com.example.demo.movie.dto.AdminInfoDTO;
import com.example.demo.movie.service.AdminService;
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
 * @since 2022-08-06
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @PostMapping("/login")
    public CommonResult login(@Validated @RequestBody AdminInfoDTO adminInfoDTO){
        return CommonResult.success(adminService.login(adminInfoDTO));
    }
    @PostMapping("/register")
    public CommonResult register(@RequestBody AdminInfoDTO adminInfoDTO){
        return CommonResult.success(adminService.register(adminInfoDTO));
    }

}

