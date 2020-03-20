package com.example.cli.utils;

import com.example.cli.domain.common.PageInfo;
import org.springframework.data.domain.Page;

public class PageUtils {

    public static <T> PageInfo<T> getPageInfo(Page<T> page,Class<T> tClass){
        PageInfo<T> pageInfo=new PageInfo<>();
        pageInfo.setPageNo(page.getNumberOfElements());
        pageInfo.setTotalCount(page.getTotalElements());
        pageInfo.setData(page.getContent());
        pageInfo.setColumns(ColumnUtils.getColumns(tClass));
        return pageInfo;
    }


}
