package com.lys.publicpro.common.vo;

import com.lys.publicpro.common.exception.ResultException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Getter
@Setter
public class Result<T> implements Serializable {
    private int code;
    private String msg;
    private T data;
    private ResultException resultException;
    public Result() {};
    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    };
    public Result(int code, String msg, T data, ResultException exception) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.resultException = exception;
    };
    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
