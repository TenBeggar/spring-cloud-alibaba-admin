package com.lfkj.web.enable;

import com.lfkj.web.exception.GlobalExceptionHandler;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(GlobalExceptionHandler.class)
public @interface EnableGlobalException {
}
