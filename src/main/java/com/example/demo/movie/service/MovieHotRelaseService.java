package com.example.demo.movie.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.movie.model.MovieHotRelase;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 热映 服务类
 * </p>
 *
 * @author CSW
 * @since 2022-12-30
 */
public interface MovieHotRelaseService extends IService<MovieHotRelase> {


    List<MovieHotRelase> getList();
}
