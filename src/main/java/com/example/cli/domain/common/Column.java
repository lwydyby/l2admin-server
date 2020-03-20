package com.example.cli.domain.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Column{

    private String title;

    private String width;

    private String dataIndex;

    private Map<String,String> scopedSlots;

    private Integer sort;

}
