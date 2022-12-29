package com.example.demo.movie.service.impl;

import com.example.demo.movie.model.MovieInfo;
import com.example.demo.movie.mapper.MovieInfoMapper;
import com.example.demo.movie.service.MovieInfoService;
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
public class MovieInfoServiceImpl extends ServiceImpl<MovieInfoMapper, MovieInfo> implements MovieInfoService {

}
