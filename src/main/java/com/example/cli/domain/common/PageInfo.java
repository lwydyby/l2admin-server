package com.example.cli.domain.common;

import lombok.Data;

import java.util.List;

@Data
public class PageInfo<T> {

    private int pageNo;

    private long totalCount;

    private List<T> data;

    private List<Column> columns;
}
