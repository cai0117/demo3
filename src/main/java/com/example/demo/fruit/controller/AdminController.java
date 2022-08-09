package com.example.demo.fruit.controller;


import com.example.demo.fruit.dto.AdminInfoDTO;
import com.example.demo.fruit.service.AdminService;
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
 * @since 2022-08-06
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;


    @PostMapping("/login")
    public CommonResult login(@RequestBody AdminInfoDTO adminInfoDTO){
        return CommonResult.success(adminService.login(adminInfoDTO));
    }
    @PostMapping("/register")
    public CommonResult register(@RequestBody AdminInfoDTO adminInfoDTO){
        return CommonResult.success(adminService.register(adminInfoDTO));
    }

}

