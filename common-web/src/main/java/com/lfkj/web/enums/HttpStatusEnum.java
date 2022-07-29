package com.lfkj.web.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpStatusEnum {

    OK(200, "成功"),

    BAD_REQUEST(400, "请求数据格式不正确"),
    UNAUTHORIZED(401, "登录凭证过期"),
    FORBIDDEN(403, "访问权限不足"),

    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    ;

    private final Integer code;
    private final String msg;
}
