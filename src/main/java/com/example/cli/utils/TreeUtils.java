package com.example.cli.utils;

import com.example.cli.domain.common.BaseTree;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liwei
 * @title: TreeUtils
 * @projectName cli
 * @description: TODO
 * @date 2019-10-23 08:21
 */
public class TreeUtils {

    private static <T extends BaseTree> List<T> getMenuTreeList(List<T> menuList, Integer pid) {
        // 查找所有菜单
        List<T> childrenList = new ArrayList<>();
        menuList.stream()
                .filter(d ->{
                    if(pid==null){
                        return StringUtils.isEmpty(d.getParentId());
                    }else {
                        return Objects.equals(pid, d.getParentId());
                    }
                } )
                .collect(Collectors.toList())
                .forEach(d -> {
                    d.setChildren(getMenuTreeList(menuList,d.getId()));
                    childrenList.add(d);
                });
        return childrenList;
    }

    public static <T extends BaseTree> List<T> getMenuTreeList(List<T> menuList) {
        List<T> result=getMenuTreeList(menuList,null);
        sortTreeList(result);
        return result;
    }

    /**
     * 对菜单排序
     * @param menuList
     * @param <T>
     */
    private static <T extends BaseTree> void sortTreeList(List<T> menuList) {
        menuList.sort(Comparator.comparing(T::getSort));
        for(T t:menuList){
             if(t.getChildren()!=null&&t.getChildren().size()!=0){
                 sortTreeList(t.getChildren());
             }
        }
    }


}
