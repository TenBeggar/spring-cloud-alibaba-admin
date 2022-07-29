package com.lfkj.whale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName
public class Produce {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private LocalDateTime recordTime;//时间
    private String department;//生产单位
    private String area;//工作面
    private String unit;//单位
    private Integer planCoal;//计划产量
    private Integer realCoal;//实际产量
}
