package com.lys.publicpro.module.commonSys.controller;

import com.lys.publicpro.common.db.entity.DealWay;
import com.lys.publicpro.common.vo.Result;
import com.lys.publicpro.module.commonSys.bo.CommonSysBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.lys.publicpro.common.BaseController.onError;
import static com.lys.publicpro.common.BaseController.onSuccess;

/**
 * @author 李无敌
 * @date 2021年03月01日 16:48
 */
@RestController
@RequestMapping("/common")
@Api(tags = "公共接口模块")
public class CommonSysController {
    private final Logger logger = LoggerFactory.getLogger(CommonSysController.class);

    @Autowired
    CommonSysBo commonSysBo;
    //查询交易方式
    @ApiOperation(value = "查询交易方式", httpMethod = "GET")
    @GetMapping("/findAllDealWay")
    public Result<List<DealWay>> findAllDealWay() throws Exception {
        Result result = new Result();
        try {
            result = onSuccess(commonSysBo.findAllDealWay());
        } catch (Exception e) {
            logger.error("{出错了}"+e.getMessage());
            result = onError(e);
        }
        return result;
    }
}
