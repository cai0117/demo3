package com.example.demo.util;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
/**
 * 通用分页
 */
@SuppressWarnings("all")
public class CommonPage<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Long total;
    private Integer totalPage;

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    private List<T> list;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    //将mybatis分页结果转化为通用
    public static <T> CommonPage<T> restPage(IPage<T> pageResult){
        CommonPage<T> result = new CommonPage<>();

        //获取当前页
        result.setPageNum(Convert.toInt(pageResult.getCurrent()));

        //每页的数据数
        result.setPageSize(Convert.toInt(pageResult.getSize()));

        //总数据量
        result.setTotal(pageResult.getTotal());

        //总页数
        result.setTotalPage(Convert.toInt(pageResult.getTotal()/pageResult.getSize()+1));

        //当前页的数据
        result.setList(pageResult.getRecords());

        return result;
    }
}
