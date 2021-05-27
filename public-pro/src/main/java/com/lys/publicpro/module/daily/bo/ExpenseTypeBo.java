package com.lys.publicpro.module.daily.bo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.lys.publicpro.common.db.entity.ExpenseType;
import com.lys.publicpro.common.db.mapper.ExpenseTypeMapper;
import com.lys.publicpro.module.daily.model.dto.ExpenseTypeDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 李无敌
 * @date 2021年03月19日 17:23
 */
@Service
public class ExpenseTypeBo {
    @Resource
    ExpenseTypeMapper expenseTypeMapper;
    //获取消费类型
    public List<ExpenseType> findAllExpenseType(String expenseType) throws Exception {
        LambdaQueryWrapper<ExpenseType> lambda = new QueryWrapper<ExpenseType>().lambda();
        lambda.like(ObjectUtils.isNotEmpty(expenseType), ExpenseType::getExpenseType, expenseType);
        return expenseTypeMapper.selectList(lambda);
    }
    //新增消费类型
    public boolean addExpenseType(ExpenseTypeDTO expenseTypeDTO) throws Exception {
        ExpenseType type = new ExpenseType();
        type.setExpenseType(expenseTypeDTO.getExpenseType());
        type.setRemark(expenseTypeDTO.getRemark());
        int insert = expenseTypeMapper.insert(type);
        return insert > 0 ? true : false;
    }
    //修改消费类型
    public boolean updateExpenseType(ExpenseTypeDTO expenseTypeDTO) throws Exception {
        ExpenseType expenseType = new ExpenseType();
        if (ObjectUtils.isEmpty(expenseTypeDTO.getId())) {
            return false;
        }
        expenseType.setId(expenseTypeDTO.getId());
        expenseType.setRemark(expenseTypeDTO.getRemark());
        expenseType.setExpenseType(expenseTypeDTO.getExpenseType());
        int i = expenseTypeMapper.updateById(expenseType);
        return i > 0 ? true : false;
    }
    //删除消费类型
    public boolean deleteExpenseType(Integer id) throws Exception {
         return expenseTypeMapper.deleteById(id) > 0 ? true : false;
    }
}
