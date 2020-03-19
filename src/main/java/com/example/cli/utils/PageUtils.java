package com.example.cli.utils;

import com.example.cli.domain.common.PageInfo;
import org.springframework.data.domain.Page;

public class PageUtils {

    public static <T> PageInfo<T> getPageInfo(Page<T> page){
        PageInfo<T> pageInfo=new PageInfo<>();
        pageInfo.setPageNo(page.getNumberOfElements());
        pageInfo.setTotalCount(page.getTotalElements());
        pageInfo.setData(page.getContent());
        return pageInfo;
    }
}
