package com.lys.publicpro.module.daily.bo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.lys.publicpro.common.db.entity.Income;
import com.lys.publicpro.common.db.mapper.IncomeMapper;
import com.lys.publicpro.common.dto.PageModel;
import com.lys.publicpro.common.utils.DBUtils;
import com.lys.publicpro.module.daily.model.dto.IncomeDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 李无敌
 * @date 2021年02月24日 9:57
 */
@Service
public class IncomeBo {
    @Resource
    IncomeMapper incomeMapper;
    Calendar calendar = Calendar.getInstance();

    //分页查询收入列表
    public IPage<Income> findIncomePage(PageModel pageModel, IncomeDTO incomeDTO) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LambdaQueryWrapper<Income> lambda = new QueryWrapper<Income>().lambda();
        lambda.eq(ObjectUtils.isNotEmpty(incomeDTO.getIncomeWay()),Income::getIncomeWay, incomeDTO.getIncomeWay());
        lambda.like(ObjectUtils.isNotEmpty(incomeDTO.getIncomeType()),Income::getIncomeType, incomeDTO.getIncomeType());
        lambda.ge(ObjectUtils.isNotEmpty(incomeDTO.getStartDate()),Income::getAccountDate, incomeDTO.getStartDate());
        lambda.le(ObjectUtils.isNotEmpty(incomeDTO.getEndDate()),Income::getAccountDate, incomeDTO.getEndDate());
        lambda.ge(ObjectUtils.isNotEmpty(incomeDTO.getSmallMoney()),Income::getMoney, incomeDTO.getSmallMoney());
        lambda.le(ObjectUtils.isNotEmpty(incomeDTO.getBigMoney()),Income::getMoney, incomeDTO.getBigMoney());
        //根据修改时间降序
        lambda.orderByDesc(Income::getAccountDate);
        IPage page = incomeMapper.selectPage(pageModel, lambda);
        //如果当前页数量为空 则返回上一页
        if (page.getRecords().size() <= 0) {
            long current = page.getCurrent() > 1 ? page.getCurrent() - 1 : 1;
            pageModel.setCurrent(current);
            page = incomeMapper.selectPage(pageModel, lambda);
        }
        return page;
    }
    //新增收入账单
    public boolean addIncome(Income income) throws Exception {
        income.setId(DBUtils.getUuid());
        calendar.setTime(new Date(income.getAccountDate()));
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        income.setYear(year);
        income.setMonth(month);
        int insert = incomeMapper.insert(income);
        return insert > 0 ? true : false;
    }
    //修改收入账单
    public boolean updateIncome(Income income) throws Exception {
        calendar.setTime(new Date(income.getAccountDate()));
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        income.setYear(year);
        income.setMonth(month);
        int i = incomeMapper.updateById(income);
        return i > 0 ? true : false;
    }
    //删除收入账单
    public boolean delIncome(String id) throws Exception {
        int i = incomeMapper.deleteById(id);
        return i > 0 ? true : false;
    }
}
