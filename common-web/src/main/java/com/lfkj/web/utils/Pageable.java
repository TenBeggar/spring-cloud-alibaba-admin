package com.lfkj.web.utils;

import lombok.Data;

@Data
public class Pageable {

    private int current = 1;
    private int size = 20;
}
