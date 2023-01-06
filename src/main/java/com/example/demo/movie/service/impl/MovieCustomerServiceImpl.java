package com.example.demo.movie.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.exception.ServiceException;
import com.example.demo.movie.dto.LoginRequest;
import com.example.demo.movie.dto.LoginResponse;
import com.example.demo.movie.mapper.MovieCustomerMapper;
import com.example.demo.movie.model.MovieCustomer;
import com.example.demo.movie.service.MovieCustomerService;
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
public class MovieCustomerServiceImpl extends ServiceImpl<MovieCustomerMapper, MovieCustomer> implements MovieCustomerService {

    @Autowired
    private MovieCustomerMapper movieCustomerMapper;

    @Override
    public LoginResponse<MovieCustomer> login(LoginRequest movieCustomer) {
        String Tel = movieCustomer.getTel();
        LoginResponse<MovieCustomer> loginResponse = new LoginResponse<>();
        if(StrUtil.isBlank(Tel)){
            throw new ServiceException("手机号为空");
        }
        LambdaQueryWrapper<MovieCustomer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(movieCustomer.getTel() != null,MovieCustomer::getTel,movieCustomer.getTel());
        MovieCustomer customerOne = movieCustomerMapper.selectOne(lambdaQueryWrapper);
        if(customerOne != null){
            customerOne = getOne(lambdaQueryWrapper);
            loginResponse.setUserInfo(customerOne);
            String token = javaToken.generateToken(customerOne.getCustomerId().toString(), customerOne.getTel());//获取token
            loginResponse.setToken(token);//将token放到请求数据中
            return loginResponse;
        }else {
            MovieCustomer customer = new MovieCustomer();
            BeanUtil.copyProperties(movieCustomer,customer);
            movieCustomerMapper.insert(customer);
            try{
                QueryWrapper<MovieCustomer> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("tel",Tel);
                loginResponse.setUserInfo(getOne(queryWrapper));
                String token = javaToken.generateToken(getOne(queryWrapper).getCustomerId().toString(), getOne(queryWrapper).getTel());//获取token
                loginResponse.setToken(token);//将token放到请求数据中
                return loginResponse;
            }catch(Exception e){
                throw new ServiceException("用户信息不唯一");
            }
        }
    }

//    @Override
//    public Admin register(AdminInfoDTO adminInfoDTO) {
//        String account = adminInfoDTO.getAccount();
//        String password = adminInfoDTO.getPassword();
//        if(StrUtil.isBlank(account) || StrUtil.isBlank(password)){
//            throw new ServiceException("用户名或密码为空");
//        }
//        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("account",account);
//        queryWrapper.eq("password",password);
//        Admin one = null;
//        try{
//            one = getOne(queryWrapper);
//        }catch(Exception e){
//            LOG.error(e);
//        }
//        if (one == null){
//            one = new Admin();
//            BeanUtil.copyProperties(adminInfoDTO,one,true);//把前端穿过来的注册信息复制到Admin
//            save(one);//插入数据到数据库
//        }else{
//            throw new ServiceException("用户信息已存在");
//        }
//        return one;
//    }

}
