package com.example.demo.util;

/**
 * 通用返回对象
 * @param <T>
 */
@SuppressWarnings("all")
public class CommonResult<T> {
    private long code;
    private String message;
    private T data;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    protected CommonResult(){}

    protected CommonResult(long code,String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
    /**
     * 成功返回结果
     */
    public static <T> CommonResult<T> success(T data){
        return new CommonResult<>(ResultCode.SUCCESS.getCode(),
                ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> CommonResult<T> success(T data,String message){
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), message,data);
    }
    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode){
        return new CommonResult<>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    public static <T> CommonResult<T> failed(IErrorCode errorCode,String message){
        return new CommonResult<>(errorCode.getCode(), message,null);
    }

    public static <T> CommonResult<T> failed(String message){
        return new CommonResult<>(ResultCode.FAILED.getCode(),message,null);
    }

    public static <T> CommonResult<T> failed(){
        return failed(ResultCode.FAILED);
    }
    /**
     * 参数验证失败返回结果
     */
    public static <T> CommonResult<T> validateFailed(){
        return failed(ResultCode.VALIDATE_FAILED);
    }

    public static <T> CommonResult<T> validateFailed(String message){
        return new CommonResult<>(ResultCode.VALIDATE_FAILED.getCode(), message,null);
    }
    /**
     * 未登录返回结果
     */
    public static <T> CommonResult<T> unauthorized(T data){
        return new CommonResult<>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }
    /**
     * 未授权返回结果
     */
    public static <T> CommonResult<T> forbidden(T data){
        return new CommonResult<>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(),data);
    }
}
