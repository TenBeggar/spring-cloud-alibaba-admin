package com.lfkj.uaa.domain;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lfkj.uaa.enums.SexEnum;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName
public class User implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String username;
    private String password;
    private String nickname;
    @EnumValue
    private SexEnum sex;
    private Integer age;
}
