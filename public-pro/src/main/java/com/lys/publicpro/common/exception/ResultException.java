package com.lys.publicpro.common.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class ResultException implements Serializable {
    private String exceptionClass;
    private String msg;
    private int code;
    public ResultException() {};
    public ResultException(String exceptionClass, String msg) {
        this.exceptionClass = exceptionClass;
        this.msg = msg;
    }
    public ResultException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
