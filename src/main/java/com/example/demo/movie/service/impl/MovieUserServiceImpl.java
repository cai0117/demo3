package com.example.demo.movie.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.exception.ServiceException;
import com.example.demo.movie.dto.LoginResponse;
import com.example.demo.movie.dto.UserDto;
import com.example.demo.movie.model.MovieCustomer;
import com.example.demo.movie.model.MovieUser;
import com.example.demo.movie.mapper.MovieUserMapper;
import com.example.demo.movie.service.MovieUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.util.javaToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CSW
 * @since 2022-12-29
 */
@Service
public class MovieUserServiceImpl extends ServiceImpl<MovieUserMapper, MovieUser> implements MovieUserService {


    @Autowired
    private MovieUserMapper movieUserMapper;

    @Override
    public LoginResponse<MovieUser> login(UserDto request) {
        String tel = request.getTel();
        String password = request.getPassword();
        LoginResponse<MovieUser> loginResponse = new LoginResponse<>();
        if(StrUtil.isBlank(tel) || StrUtil.isBlank(password)){
            throw new ServiceException("登录信息为空");
        }
        LambdaQueryWrapper<MovieUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(request.getTel() != null,MovieUser::getTel,request.getTel())
                          .eq(request.getPassword() != null, MovieUser::getPassword,request.getPassword());
        MovieUser userOne = movieUserMapper.selectOne(lambdaQueryWrapper);
        if(userOne != null){
            userOne = getOne(lambdaQueryWrapper);
            loginResponse.setUserInfo(userOne);
            String token = javaToken.generateToken(userOne.getUserId().toString(), userOne.getPassword());//获取token
            loginResponse.setToken(token);//将token放到请求数据中
            return loginResponse;
        }else {
            MovieUser user = new MovieUser();
            BeanUtil.copyProperties(request,user);
            movieUserMapper.insert(user);
            try{
                QueryWrapper<MovieUser> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("tel",tel).eq("password",password);
                loginResponse.setUserInfo(getOne(queryWrapper));
                String token = javaToken.generateToken(getOne(queryWrapper).getUserId().toString(), getOne(queryWrapper).getPassword());//获取token
                loginResponse.setToken(token);//将token放到请求数据中
                return loginResponse;
            }catch(Exception e){
                throw new ServiceException("用户信息不唯一");
            }
        }
    }
}
