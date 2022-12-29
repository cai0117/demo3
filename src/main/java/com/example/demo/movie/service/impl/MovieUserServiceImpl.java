package com.example.demo.movie.service.impl;

import com.example.demo.movie.model.MovieUser;
import com.example.demo.movie.mapper.MovieUserMapper;
import com.example.demo.movie.service.MovieUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
