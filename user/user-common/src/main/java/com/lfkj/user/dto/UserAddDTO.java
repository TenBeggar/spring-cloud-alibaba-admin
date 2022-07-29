package com.lfkj.user.dto;

import com.lfkj.web.enums.SexEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class UserAddDTO implements Serializable {

    @NotBlank(message = "账号不能为空")
//    @Pattern(regexp = "^1(([3,8][0-9])|(4[1,4-9])|(5[0-3,5-9])|(6[5,6])|(7[0-8])|(9[1,8,9]))\\d{8}$", message = "手机号格式不正确")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 12, message = "密码为6-12长度的字符")
    private String password;
    @NotBlank(message = "昵称不能为空")
    private String nickname;
    private SexEnum sex;
    private Integer age;
}
