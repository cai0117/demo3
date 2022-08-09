package com.example.demo.config.intercept;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.exception.ServiceException;
import com.example.demo.fruit.model.Admin;
import com.example.demo.fruit.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        String adminId;
        //如果不是方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        //认证
        if (StrUtil.isBlank(token)){
            throw new ServiceException("过期，请重新登录");
        }
        //获取token中的id
        try {
            adminId = JWT.decode(token).getAudience().get(0);
        }catch (JWTDecodeException j){
            throw new ServiceException("验证失败");
        }
        //根据id去查询数据库是否存在用户
        Admin byId = adminService.getById(adminId);
        if (byId == null){
            throw new ServiceException("用户不存在，请重新登录");
        }
        //验证token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(byId.getPassword())).build();//根据查询出来的id和密码构建token并验证
        try{
            jwtVerifier.verify(token);//验证token
        }catch (JWTVerificationException e){
            throw new ServiceException("验证失败请重新登录");
        }
        return true;
    }
}
