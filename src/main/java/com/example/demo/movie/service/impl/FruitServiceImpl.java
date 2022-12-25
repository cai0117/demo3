package com.example.demo.movie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.movie.model.Fruit;
import com.example.demo.movie.mapper.FruitMapper;
import com.example.demo.movie.service.FruitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CSW
 * @since 2022-08-05
 */
@Service
public class FruitServiceImpl extends ServiceImpl<FruitMapper, Fruit> implements FruitService {

    @Override
    public IPage<Fruit> getList(String fname, String remark, Integer pageNum, Integer pageSize) {
        QueryWrapper<Fruit> qw = new QueryWrapper<>();
        if(!"".equals(fname)) qw.like("fname",fname);
        if(!"".equals(remark)) qw.like("remark",remark);
        IPage<Fruit> page = new Page<>(pageNum,pageSize);
        return this.page(page,qw);
    }
}
