package com.lys.publicpro.common;

import com.lys.publicpro.common.exception.ResultException;
import com.lys.publicpro.common.utils.ResultUtils;
import com.lys.publicpro.common.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author 李无敌
 * @date 2021年01月20日 9:00
 */
public class BaseController implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(BaseController.class);
    public static Result<Object> onError(Exception e) {
        return ResultUtils.reError();
    };
    public static Result<Object> onSuccess(Object obj) {
        return ResultUtils.reSuccess(obj);
    };
}
