package com.lfkj.user.dto;

import com.lfkj.web.enums.SexEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserUpdateDTO {

    @NotNull(message = "id不能为空")
    private Long id;
    @NotBlank(message = "昵称不能为空")
    private String nickname;
    private SexEnum sex;
}
