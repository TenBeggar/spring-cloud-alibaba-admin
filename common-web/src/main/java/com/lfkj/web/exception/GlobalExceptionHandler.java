package com.lfkj.web.exception;

import com.lfkj.web.enums.HttpStatusEnum;
import com.lfkj.web.utils.RestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public RestVO<Void> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("【统一异常处理】MethodArgumentNotValidException:", e);
        BindingResult bindingResult = e.getBindingResult();
        String defaultMessage = bindingResult.getFieldError().getDefaultMessage();
        return RestVO.ERROR(HttpStatusEnum.BAD_REQUEST, defaultMessage);
    }

    @ResponseBody
    @ExceptionHandler(value = FormCheckException.class)
    public RestVO<Void> formCheckException(FormCheckException e) {
        log.error("【统一异常处理】FormCheckException:", e);
        return RestVO.ERROR(HttpStatusEnum.BAD_REQUEST, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = IllegalArgumentException.class)
    public RestVO<Void> illegalArgumentException(IllegalArgumentException e) {
        log.error("【统一异常处理】IllegalArgumentException:", e);
        return RestVO.ERROR(HttpStatusEnum.BAD_REQUEST, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public RestVO<Void> runtimeException(RuntimeException e) {
        log.error("【统一异常处理】RuntimeException:", e);
        return RestVO.ERROR(HttpStatusEnum.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public RestVO<Void> exceptionHandler(Exception e) {
        log.error("【统一异常处理】Exception:", e);
        return RestVO.ERROR(HttpStatusEnum.INTERNAL_SERVER_ERROR);
    }
}
