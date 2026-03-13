package com.lx.user_center.expection;

import com.lx.user_center.common.BaseResponse;
import com.lx.user_center.common.ErrorCode;
import com.lx.user_center.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
//    shift + F6 选中变量名批量替换
    public BaseResponse businessExceptionHandler(BusinessException e) {
        log.error("runtimeException" + e.getMessage(), e);
        return ResultUtils.error(e.getCode(), e.getMessage(), e.getDescription());

    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeException(Exception e) {
        log.error("runtimeException",e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR,"");
    }

}
