package com.lfkj.whale;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProduceUpdateAddDTO extends ProduceAddDTO {

    @NotNull(message = "id不能为空")
    private Long id;
}
