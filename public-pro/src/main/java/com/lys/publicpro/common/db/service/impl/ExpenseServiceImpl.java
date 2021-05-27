package com.lys.publicpro.common.db.service.impl;

import com.lys.publicpro.common.db.entity.Expense;
import com.lys.publicpro.common.db.mapper.ExpenseMapper;
import com.lys.publicpro.common.db.service.ExpenseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liwudi
 * @since 2021-03-22
 */
@Service
public class ExpenseServiceImpl extends ServiceImpl<ExpenseMapper, Expense> implements ExpenseService {

}
