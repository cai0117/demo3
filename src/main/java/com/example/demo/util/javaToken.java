package com.example.demo.util;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class javaToken {
    public static String generateToken(String adminId,String sign){

        return JWT.create().withAudience(adminId) // 将id保存到token里面
                .withExpiresAt(DateUtil.offsetHour(new Date(),2)) //2小时后token过期
                .sign(Algorithm.HMAC256(sign)); //用密码作为token的密钥

    }
}
