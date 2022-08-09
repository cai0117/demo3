package com.example.demo.fruit.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.exception.GlobalExceptionHandler;
import com.example.demo.exception.ServiceException;
import com.example.demo.fruit.dto.AdminInfoDTO;
import com.example.demo.fruit.model.Admin;
import com.example.demo.fruit.mapper.AdminMapper;
import com.example.demo.fruit.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.util.CommonResult;
import com.example.demo.util.javaToken;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CSW
 * @since 2022-08-06
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    private final static Log LOG = Log.get();

    @Override
    public AdminInfoDTO login(AdminInfoDTO adminInfoDTO) {
        String username = adminInfoDTO.getName();
        String password = adminInfoDTO.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
             throw new ServiceException("用户名或密码为空");
        }
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",username);
        queryWrapper.eq("password",password);
        Admin one = null;
        try{
            one = getOne(queryWrapper);
        }catch(Exception e){
            LOG.error(e);
        }
        if(one != null){
            BeanUtil.copyProperties(one,adminInfoDTO,true);
            String token = javaToken.generateToken(one.getId().toString(), one.getPassword());//获取token
            adminInfoDTO.setToken(token);//将token放到请求数据中
            return adminInfoDTO;
        }else{
            throw new ServiceException("用户名或密码错误");

        }
    }

    @Override
    public Admin register(AdminInfoDTO adminInfoDTO) {
        String username = adminInfoDTO.getName();
        String password = adminInfoDTO.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            throw new ServiceException("用户名或密码为空");
        }
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",username);
        queryWrapper.eq("password",password);
        Admin one = null;
        try{
            one = getOne(queryWrapper);
        }catch(Exception e){
            LOG.error(e);
        }
        if (one == null){
            one = new Admin();
            BeanUtil.copyProperties(adminInfoDTO,one,true);//把前端穿过来的注册信息复制到Admin
            save(one);//插入数据到数据库
        }else{
            throw new ServiceException("用户信息已存在");
        }
        return one;
    }
}
