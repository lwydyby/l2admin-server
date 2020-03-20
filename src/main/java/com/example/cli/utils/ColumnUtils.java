package com.example.cli.utils;

import com.example.cli.constant.TableColumn;
import com.example.cli.domain.common.Column;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class ColumnUtils {

    public static <T> List<Column> getColumns(Class<T> column){
        List<Column> list=new ArrayList<>();
        Field[] fields=column.getDeclaredFields();
        for(Field field:fields){
            TableColumn tableColumn=field.getAnnotation(TableColumn.class);
            if(tableColumn!=null){
                Column col=Column.builder()
                        .dataIndex(field.getName()).title(tableColumn.title())
                        .sort(tableColumn.sort())
                        .build();
                if(!StringUtils.isEmpty(tableColumn.width())){
                    col.setWidth(tableColumn.width());
                }
                if(!StringUtils.isEmpty(tableColumn.scopeSlots())){
                    Map<String,String> map=new HashMap<>();
                    map.put("customRender",tableColumn.scopeSlots());
                    col.setScopedSlots(map);
                }
                list.add(col);
            }
        }
        list=list.stream().sorted(Comparator.comparing(Column::getSort)).collect(Collectors.toList());
        Map<String,String> map=new HashMap<>();
        map.put("customRender","action");
        list.add(Column.builder().dataIndex("action")
                .title("操作").scopedSlots(map).width("150px").build());
        return list;
    }



}
