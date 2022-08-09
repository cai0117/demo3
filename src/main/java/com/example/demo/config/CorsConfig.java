package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/*
设置跨域
 */
@Configuration
public class CorsConfig {
    private static final long MAX_AGE = 24*60*60;

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");//设置访问源地址
        corsConfiguration.addAllowedHeader("*");//设置访问请求头
        corsConfiguration.addAllowedMethod("*");//设置访问请求方法
        corsConfiguration.setMaxAge(MAX_AGE);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);//对接口设置跨域配置
        return new CorsFilter(source);
    }


}
