package com.lfkj.whale;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lfkj.web.utils.LongToStringSerializer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProduceVO {

    @JsonSerialize(using = LongToStringSerializer.class)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recordTime;//时间
    private String department;//生产单位
    private String area;//工作面
    private String unit;//单位
    private Integer planCoal;//计划产量
    private Integer realCoal;//实际产量
}
