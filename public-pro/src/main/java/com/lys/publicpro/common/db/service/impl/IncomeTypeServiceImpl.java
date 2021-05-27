package com.lys.publicpro.common.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lys.publicpro.common.db.entity.IncomeType;
import com.lys.publicpro.common.db.mapper.IncomeTypeMapper;
import com.lys.publicpro.common.db.service.IncomeTypeService;
import org.springframework.stereotype.Service;

/**
 * @author 李无敌
 * @date 2021年02月23日 14:45
 */
@Service
public class IncomeTypeServiceImpl extends ServiceImpl<IncomeTypeMapper, IncomeType> implements IncomeTypeService {
}
