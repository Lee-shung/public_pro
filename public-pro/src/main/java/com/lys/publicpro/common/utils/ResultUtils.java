package com.lys.publicpro.common.utils;

import com.lys.publicpro.common.exception.ResultException;
import com.lys.publicpro.common.vo.Result;

public class ResultUtils {
    private static final int SUCCESS_CODE = 200;
    private static final int ERROR_CODE = 500;
    private static final int FAIL_CODE = 0;
    //成功返回模型
    public static Result reSuccess(Object data) {
        Result result = new Result(SUCCESS_CODE,"操作成功", data);
        return result;
    };
    //失败返回模型
    public static Result reFail(String msg) {
        return new Result(FAIL_CODE,msg);
    };
    //报错返回模型
    public static Result reError() {
        Result result = new Result(ERROR_CODE,"服务端错误");
        result.setCode(ERROR_CODE);
        return result;
    }
}
