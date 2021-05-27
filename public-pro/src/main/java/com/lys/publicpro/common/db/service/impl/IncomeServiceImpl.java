package com.lys.publicpro.common.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lys.publicpro.common.db.entity.Income;
import com.lys.publicpro.common.db.mapper.IncomeMapper;
import com.lys.publicpro.common.db.service.IncomeService;
import org.springframework.stereotype.Service;

/**
 * @author 李无敌
 * @date 2021年02月24日 10:30
 */
@Service
public class IncomeServiceImpl extends ServiceImpl<IncomeMapper, Income> implements IncomeService {
}
