package com.lfkj.web.utils;

import com.lfkj.web.enums.HttpStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestVO<T> implements Serializable {

    private Integer code;
    private T data;
    private String msg;

    public static RestVO<Void> OK() {
        return OK(null);
    }

    public static <T> RestVO<T> OK(T data) {
        return new RestVO<>(HttpStatusEnum.OK.getCode(), data, HttpStatusEnum.OK.getMsg());
    }

    public static RestVO<Void> ERROR(Integer code, String msg) {
        return new RestVO<>(code, null, msg);
    }

    public static RestVO<Void> ERROR(HttpStatusEnum httpStatusEnum, String msg) {
        return ERROR(httpStatusEnum.getCode(), msg);
    }

    public static RestVO<Void> ERROR(HttpStatusEnum httpStatusEnum) {
        return ERROR(httpStatusEnum, httpStatusEnum.getMsg());
    }
}
