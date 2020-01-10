package com.example.cli.exception;


import org.springframework.http.HttpStatus;

/**
 * 为了返回的异常和openStack一致，增加自定义异常，配合统一处理，返回正确的HttpStatus
 *
 * @author lw
 */
public class ValidException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public ValidException(HttpStatus status, String message) {
        this.status = status;
        this.message = getErrorMessage(status)+ "| errorMessage:"+message;
    }

    public ValidException(HttpStatus status) {
        this.status = status;
        this.message= getErrorMessage(status);
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    private String getErrorMessage(HttpStatus status){
        switch (status) {
            case NOT_FOUND:
                return  "The requested resource could not be found.";
            case BAD_REQUEST:
                return "Some content in the request was invalid.";
            case UNAUTHORIZED:
                return "The request you have made requires authentication.";
            case FORBIDDEN:
                return"Policy does not allow current user to do this operation.";
            default:
                return status.getReasonPhrase();
        }
    }
}
