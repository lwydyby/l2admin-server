package com.example.cli.exception;


import com.example.cli.constant.CommonConstant;
import com.example.cli.domain.common.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.sasl.AuthenticationException;
import java.security.SignatureException;

/**
 * @author Exrickx
 */
@Slf4j
@RestControllerAdvice
public class RestCtrlExceptionHandler {

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseBean handleXCloudException(AuthException e) {
        if (e!=null){
            log.error(e.toString());
        }
        return new ResponseBean(e.getCode(),e.getMsg(),null);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseBean handleExcelException(BaseException e) {
        if (e!=null){
            log.error(e.toString());
        }
        return new ResponseBean(e.getCode(),e.getMsg(),null);
    }


    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseBean handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("JSON 格式错误:{}", e);
        return new ResponseBean(CommonConstant.PARAM_EXCEPTION,"格式错误");
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseBean handleValidationException(MethodArgumentNotValidException e) {
        log.error("参数异常:{}", e);
        return new ResponseBean(CommonConstant.PARAM_EXCEPTION,"参数异常");
    }

    /**
     * 401 - 验签异常
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(SignatureException.class)
    public ResponseBean handleSignatureException(SignatureException e) {
        log.error("token验签失败:{}", e);
        return new ResponseBean(CommonConstant.AUTH_EXCEPTION,"token验签失败" );
    }

    /**
     * 403 - shiro 认证异常
     * @param
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseBean handleUnauthenticatedException(UnauthenticatedException e) {
        log.error("未经过身份认证:{}", e);
        return new ResponseBean(CommonConstant.AUTH_EXCEPTION,"未经身份认证，无权访问");
    }

    /**
     * 403 - shiro 授权异常
     * @param
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseBean handleUnauthorizedException(UnauthorizedException e) {
        log.error("不在权限允许范围内:{}", e);
        return new ResponseBean(CommonConstant.AUTH_EXCEPTION,"超出权限范围，无权访问");
    }

    /**
     * 415 - Unsupported Media Type。HttpMediaTypeNotSupportedException
     * 是ServletException的子类,需要Servlet API支持
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseBean handleHttpMediaTypeNotSupportedException(Exception e) {
        log.error("不支持的媒体类型:{}", e);
        return new ResponseBean(CommonConstant.EXCEPTION,"不支持的媒体类型");
    }

    /**
     * 500 - shiro身份认证异常
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseBean handleAuthenticationException(AuthenticationException e) {
        log.error("Shiro身份认证异常:{}", e);
        return new ResponseBean(CommonConstant.AUTH_EXCEPTION,"身份认证失败");
    }

    /**
     * 500 - shiro授权处理异常
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthorizationException.class)
    public ResponseBean handleAuthorizationException(AuthorizationException e) {
        log.error("Shiro授权处理异常:{}", e);
        return new ResponseBean(CommonConstant.AUTH_EXCEPTION,"授权处理失败");
    }



}
