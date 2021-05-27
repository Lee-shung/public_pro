package com.lys.publicpro.common.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lys.publicpro.common.db.entity.AccountInfo;
import com.lys.publicpro.common.db.mapper.AccountInfoMapper;
import com.lys.publicpro.common.db.service.AccountInfoService;
import org.springframework.stereotype.Service;

/**
 * @author 李无敌
 * @date 2021年02月06日 16:49
 */
@Service
public class AccountInfoServiceImpl extends ServiceImpl<AccountInfoMapper, AccountInfo> implements AccountInfoService {
}
