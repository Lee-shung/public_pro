package com.lys.publicpro.module.statistics.bo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lys.publicpro.common.db.entity.Expense;
import com.lys.publicpro.common.db.entity.Income;
import com.lys.publicpro.common.db.entity.IncomeType;
import com.lys.publicpro.common.db.mapper.ExpenseMapper;
import com.lys.publicpro.common.db.mapper.IncomeMapper;
import com.lys.publicpro.common.db.mapper.IncomeTypeMapper;
import com.lys.publicpro.common.exception.ResultException;
import com.lys.publicpro.common.thread.YearDataThread;
import com.lys.publicpro.common.utils.ResultUtils;
import com.lys.publicpro.common.vo.Result;
import io.swagger.models.auth.In;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author 李无敌
 * @date 2021年05月20日 16:38
 */
@Service
public class StatisticsBo {

    @Resource
    IncomeMapper incomeMapper;

    @Resource
    ExpenseMapper expenseMapper;

    //查询某月收支数据
    public Object getMonthDataByMonth(Integer month, Integer year) throws Exception {
        Object result = new Object();
        try {
            HashMap<String, Object> map = new HashMap<>();
            //收入
            HashMap<String, Object> incomeMap = getIncomeMonthData(month, year);
            //支出
            HashMap<String, Object> expenseMap = getExpenseMonthData(month, year);
            map.put("income", incomeMap);
            map.put("expense", expenseMap);
            result =  map;
        } catch (Exception e) {
            result = null;
        }
        return result;
    }
    //查询某年收支数据
    public Result getYearData(Integer year) throws Exception {
        ArrayList<Object> list = new ArrayList<>();
        Result result = new Result();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            //启动多线程
            for (int i = 1; i <= 12; i++) {
                Future<Object> future = threadPool.submit(new YearDataThread(i, year));
                //通过Future.get方法获取数据
                list.add(future.get());
            }
            result = ResultUtils.reSuccess(list);
        } catch (Exception e) {
            result = ResultUtils.reError();
        } finally {
            threadPool.shutdown();
        }
        return result;
    };
    //获取月度收入数据
    public HashMap<String, Object> getIncomeMonthData(Integer month, Integer year) {
        //月收入
        LambdaQueryWrapper<Income> incomeLambda = new QueryWrapper<Income>().lambda();
        incomeLambda.eq(Income::getMonth, month);
        incomeLambda.eq(Income::getYear, year);
        List<Income> incomeList = incomeMapper.selectList(incomeLambda);
        double incomeMonth = 0;
        incomeMonth = incomeList.stream().mapToDouble(Income::getMoney).sum();
        //分类收入
        HashMap<String, Double> incomeTypeMap = new HashMap<>();
        incomeList.forEach(income -> {
            if (incomeTypeMap.containsKey(income.getIncomeType())) {
                incomeTypeMap.put(income.getIncomeType(), income.getMoney() + incomeTypeMap.get(income.getIncomeType()));
            } else {
                incomeTypeMap.put(income.getIncomeType(), income.getMoney());
            }
        });
        HashMap<String, Object> incomeMap = new HashMap<>();
        incomeMap.put("monthTotal", incomeMonth);
        incomeMap.put("typeData", incomeTypeMap);
        return incomeMap;
    };

    //获取月度支出数据
    public HashMap<String, Object> getExpenseMonthData(Integer month, Integer year) {
        //月收入
        LambdaQueryWrapper<Expense> expenseLambda = new QueryWrapper<Expense>().lambda();
        expenseLambda.eq(Expense::getMonth, month);
        expenseLambda.eq(Expense::getYear, year);

        List<Expense> expenseList = expenseMapper.selectList(expenseLambda);
        double expenseMonth = expenseList.stream().mapToDouble(Expense::getMoney).sum();
        //分类收入
        HashMap<String, Double> expenseTypeMap = new HashMap<>();
        expenseList.forEach(expense -> {
            if (expenseTypeMap.containsKey(expense.getExpenseType())) {
                expenseTypeMap.put(expense.getExpenseType(), expense.getMoney() + expenseTypeMap.get(expense.getExpenseType()));
            } else {
                expenseTypeMap.put(expense.getExpenseType(), expense.getMoney());
            }
        });
        HashMap<String, Object> expenseMap = new HashMap<>();
        expenseMap.put("monthTotal", expenseMonth);
        expenseMap.put("typeData", expenseTypeMap);
        return expenseMap;
    }
}
