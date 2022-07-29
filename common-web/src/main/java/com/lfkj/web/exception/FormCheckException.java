package com.lfkj.web.exception;

import com.lfkj.web.enums.HttpStatusEnum;

public class FormCheckException extends RuntimeException {

    public FormCheckException(String msg) {
        super(msg);
    }

    public FormCheckException(HttpStatusEnum httpStatusEnum) {
        super(httpStatusEnum.getMsg());
    }
}
