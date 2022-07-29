package com.lfkj.user.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lfkj.web.enums.SexEnum;
import com.lfkj.web.utils.LongToStringSerializer;
import lombok.Data;

@Data
public class UserVO {

    @JsonSerialize(using = LongToStringSerializer.class)
    private Long id;
    private String nickname;
    private SexEnum sex;
    private Integer age;
}
