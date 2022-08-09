package com.example.demo.fruit.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.fruit.model.Menu;
import com.example.demo.fruit.service.MenuService;
import com.example.demo.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CSW
 * @since 2022-08-09
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    /**
     * 联级查询
     * @return
     */
    @GetMapping("/page")
    public CommonResult findWithChildren(@RequestParam(defaultValue = "") String name){
        QueryWrapper<Menu> qy = new QueryWrapper<>();
        qy.like("name",name);
        List<Menu> list = menuService.list(qy);//查询出所有结果
        //获取父级菜单
        List<Menu> parentNode = list.stream().filter(menu -> menu.getParentId() == null).collect(Collectors.toList());
        for (Menu menu : parentNode){
            //获取子级菜单,并set子级
            menu.setChilrenList(list.stream().filter(m -> menu.getId().equals(m.getParentId())).collect(Collectors.toList()));
        }
        return CommonResult.success(parentNode);
    }

}

