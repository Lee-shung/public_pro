package com.lys.publicpro.module.daily.bo;

import com.lys.publicpro.common.db.mapper.ExpenseMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 李无敌
 * @date 2021年03月19日 14:51
 */
@Service
public class ExpenseBo {
    @Resource
    ExpenseMapper expenseMapper;


}
