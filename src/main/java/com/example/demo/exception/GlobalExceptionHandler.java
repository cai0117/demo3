package com.example.demo.exception;


import com.example.demo.util.CommonResult;
import com.example.demo.util.ResultCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义请求异常
     * @param serviceException
     * @return
     */

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public CommonResult handler(ServiceException serviceException){
        return CommonResult.failed(ResultCode.VALIDATE_FAILED, serviceException.getMessage());
    }
}
