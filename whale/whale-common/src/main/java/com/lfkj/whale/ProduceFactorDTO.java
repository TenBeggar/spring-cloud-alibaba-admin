package com.lfkj.whale;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lfkj.web.utils.Pageable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProduceFactorDTO extends Pageable {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recordTime;
}
