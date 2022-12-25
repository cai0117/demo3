package com.example.demo.movie.service;

import com.example.demo.movie.dto.AdminInfoDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.movie.model.Admin;
import org.springframework.stereotype.Service;

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
