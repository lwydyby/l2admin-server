package com.example.cli.domain.common;


import lombok.Data;

@Data
public class ResponseBean {

    // http 状态码
    private int code;

    // 返回信息
    private String msg;

    // 返回的数据
    private Object result;

    public ResponseBean(int code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public ResponseBean(int code) {
        this.code = code;
    }

    public ResponseBean(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseBean(Object result) {
        this.result = result;
        this.code = 200;
    }


}
