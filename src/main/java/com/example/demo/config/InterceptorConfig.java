package com.example.demo.config;

import com.example.demo.config.intercept.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

//    @Override
////    public void addInterceptors(InterceptorRegistry registry) {
////        registry.addInterceptor(jwtInterceptor())
////                .addPathPatterns("/**")//拦截所有路径请求
////                .excludePathPatterns("/**/login","/**/register","/**/export","/**/import","/flieupload/**","/swagger-ui.html/**");//排除
//    }
    //将JwtInterceptor注入到spring容器中，如果用component无法动态注入spring
    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }
}
