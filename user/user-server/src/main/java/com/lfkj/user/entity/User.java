package com.lfkj.user.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lfkj.web.enums.SexEnum;
import com.lfkj.web.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName
public class User extends BaseEntity {

    private String username;
    private String password;
    private String nickname;
    @EnumValue
    private SexEnum sex;
    private Integer age;
}
