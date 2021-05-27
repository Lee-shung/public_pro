package com.lys.publicpro.module.accountInfo.bo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.lys.publicpro.common.db.entity.AccountInfo;
import com.lys.publicpro.common.db.entity.TbUser;
import com.lys.publicpro.common.db.mapper.AccountInfoMapper;
import com.lys.publicpro.common.db.mapper.Tb_UserMapper;
import com.lys.publicpro.common.utils.JWTUtils;
import com.lys.publicpro.common.utils.ResultUtils;
import com.lys.publicpro.common.vo.Result;
import com.lys.publicpro.module.accountInfo.model.vo.AccountInfoVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author 李无敌
 * @date 2021年02月06日 16:55
 */
@Service
public class AccountInfoBo {
    @Resource
    AccountInfoMapper accountInfoMapper;
    @Resource
    Tb_UserMapper tb_userMapper;
    //根据id获取账户信息
    public AccountInfo getAccountById(Integer id) {
        AccountInfo accountInfo = accountInfoMapper.selectById(id);
        return accountInfo;
    }
    //登录
    public Result login(String accountName, String accountPwd) {
//        accountInfo.setAccountName(accountName);
//        accountInfo.setAccountPassword(accountPwd);

        LambdaQueryWrapper<AccountInfo> lambda = new QueryWrapper<AccountInfo>().lambda();
        lambda.eq(AccountInfo::getAccountName, accountName);
        lambda.eq(AccountInfo::getAccountPassword, accountPwd);
        AccountInfo accountInfo = accountInfoMapper.selectOne(lambda);
        if (accountInfo == null) {
            return ResultUtils.reFail("账号或密码错误");
        }
        //登录成功
        TbUser user = tb_userMapper.selectById(accountInfo.getUserId());
        AccountInfoVO accountInfoVO = new AccountInfoVO(accountInfo.getAccountName(), accountInfo.getRoleList(), user.getName(), user.getGender(), user.getBirthday(), user.getAddress(), user.getPhone(), user.getIdcard());
        //生成token
        String token = JWTUtils.getToken(accountInfo);
        HashMap<String, Object> map = new HashMap();
        map.put("data", accountInfoVO);
        map.put("token", token);
        return ResultUtils.reSuccess(map);
    }
    //根据token
}
