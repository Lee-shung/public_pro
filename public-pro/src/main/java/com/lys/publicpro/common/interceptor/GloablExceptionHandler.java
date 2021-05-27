package com.lys.publicpro.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.lys.publicpro.common.exception.ResultException;
import com.lys.publicpro.common.utils.ResultUtils;
import com.lys.publicpro.common.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 李无敌
 * @date 2021年04月16日 17:14
 */
@ControllerAdvice
public class GloablExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "服务器出错";
        }
        Result result = new Result();
        switch (msg) {
            case "401":
                result.setCode(401);
                result.setMsg("token过期或不存在，请重新登录！");
                break;
            default:
                result = ResultUtils.reError();
        }
        return result;
    }
}
