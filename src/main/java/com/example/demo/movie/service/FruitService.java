package com.example.demo.movie.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.movie.model.Fruit;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CSW
 * @since 2022-08-05
 */
public interface FruitService extends IService<Fruit> {

    IPage getList(String fname, String remark, Integer pageNum, Integer pageSize);
}