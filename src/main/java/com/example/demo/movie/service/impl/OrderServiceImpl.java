package com.example.demo.movie.service.impl;

import com.example.demo.movie.model.Order;
import com.example.demo.movie.mapper.OrderMapper;
import com.example.demo.movie.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author CSW
 * @since 2023-03-04
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
