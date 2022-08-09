package com.example.demo.fruit.service;

import com.example.demo.fruit.dto.AdminInfoDTO;
import com.example.demo.fruit.model.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CSW
 * @since 2022-08-06
 */
public interface AdminService extends IService<Admin> {

    AdminInfoDTO login(AdminInfoDTO adminInfoDTO);

    Admin register(AdminInfoDTO adminInfoDTO);
}
