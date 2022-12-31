package com.example.demo.movie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.movie.model.MovieHotRelase;
import com.example.demo.movie.mapper.MovieHotRelaseMapper;
import com.example.demo.movie.service.MovieHotRelaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 热映 服务实现类
 * </p>
 *
 * @author CSW
 * @since 2022-12-30
 */
@Service
public class MovieHotRelaseServiceImpl extends ServiceImpl<MovieHotRelaseMapper, MovieHotRelase> implements MovieHotRelaseService {

    @Autowired
    MovieHotRelaseMapper movieHotRelaseMapper;
    @Override
    public List<MovieHotRelase> getList() {
        List<MovieHotRelase> res =  movieHotRelaseMapper.selectList(null);
        return res;
    }
}
