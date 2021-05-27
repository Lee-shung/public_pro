package com.lys.publicpro.module.accountInfo.controller;

import com.lys.publicpro.common.annotation.PassToken;
import com.lys.publicpro.common.vo.Result;
import com.lys.publicpro.module.accountInfo.bo.AccountInfoBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李无敌
 * @date 2021年02月06日 16:55
 */
@RestController
@RequestMapping("/sysUser")
@Api(tags = "系统管理")
public class AccountInfoController {
    @Autowired
    AccountInfoBo accountInfoBo;
    //登录
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountName", value = "账号", required = true),
            @ApiImplicitParam(name = "accountPwd", value = "密码", required = true),
    })
    @PassToken
    @ApiOperation(value = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public Result login(String accountName, String accountPwd) {
        return accountInfoBo.login(accountName, accountPwd);
    }
}
