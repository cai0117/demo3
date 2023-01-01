package com.example.demo.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.movie.dto.PageInputDto;
import com.example.demo.movie.dto.PageOutDto;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

public class QueryPageUtil {


    public static <T, X> Page<T> toQueryPage(PageInputDto<X> pageInput) {
        if (pageInput == null) return null;
        Page<T> page = Page.of(pageInput.getCurrent(), pageInput.getSize());
        return page;
    }

    public static <T> PageOutDto<T> toPageOut(IPage page, List<T> records) {
        PageOutDto<T> out = new PageOutDto<>();
        out.setCurrent(((Long)page.getCurrent()).intValue());
        out.setSize(((Long)page.getSize()).intValue());
        out.setTotal(((Long)page.getTotal()).intValue());
        out.setRecords(records);
        return out;
    }
}
