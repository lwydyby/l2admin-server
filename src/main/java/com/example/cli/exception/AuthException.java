package com.example.cli.exception;

import lombok.Data;

/**
 * @author Exrickx
 */
@Data
public class AuthException extends RuntimeException {

    private String msg;

    private Integer code;

    public AuthException(Integer code,String msg) {
        super(msg);

        this.msg = msg;
        this.code=code;
    }
}
