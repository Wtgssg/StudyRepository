package com.success.oracle.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class CustomException extends Exception{

    private String outCode;
    private String outMsg;

    public CustomException(String outMsg) {
        this.outMsg = outMsg;
    }

    public CustomException(String outCode, String outMsg) {
        this.outCode = outCode;
        this.outMsg = outMsg;
    }

}
