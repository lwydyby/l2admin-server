package com.example.cli.domain.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActionEntrySet {

    private Integer id;

    private String action;

    private String describe;

    private Boolean defaultCheck;

}
