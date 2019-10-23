package com.example.cli.exception;

import com.example.cli.constant.CommonConstant;
import lombok.Data;

/**
 * @author Exrickx
 */
@Data
public class BaseException extends RuntimeException {

    private String msg;

    private Integer code;

    public BaseException(Integer code, String msg) {
        super(msg);
        this.msg = msg;
        this.code=code;
    }

    public BaseException(String msg) {
        super(msg);
        this.code= CommonConstant.PARAM_EXCEPTION;
        this.msg = msg;
    }
}
