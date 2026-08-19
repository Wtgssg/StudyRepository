package com.success.oracle.util;

import lombok.Data;

@Data
public class PageParam {

    private Integer pageNum;

    private Integer pageSize;


    public PageParam() {
        this.pageNum = 0;
        this.pageSize = 5;
    }

}