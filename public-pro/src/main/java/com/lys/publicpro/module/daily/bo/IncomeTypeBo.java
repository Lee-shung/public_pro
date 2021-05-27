package com.lys.publicpro.module.daily.bo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lys.publicpro.common.db.entity.IncomeType;
import com.lys.publicpro.common.db.mapper.IncomeTypeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 李无敌
 * @date 2021年02月23日 14:53
 */
@Service
public class IncomeTypeBo {
    @Resource
    IncomeTypeMapper incomeTypeMapper;

    //查询所有收入类型
    public List<IncomeType> findIncomeType(String incomeType) throws Exception {
        LambdaQueryWrapper<IncomeType> lambda = new QueryWrapper<IncomeType>().lambda();
        incomeType = incomeType == null ? "" : incomeType;
        lambda.like(IncomeType::getIncomeType, incomeType);
        List list = incomeTypeMapper.selectList(lambda);
        return list;
    }
    //添加收入类型
}
