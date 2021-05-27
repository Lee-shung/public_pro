package com.lys.publicpro.module.daily.controller;


import com.lys.publicpro.common.db.entity.ExpenseType;
import com.lys.publicpro.common.vo.Result;
import com.lys.publicpro.module.daily.bo.ExpenseTypeBo;
import com.lys.publicpro.module.daily.model.dto.ExpenseTypeDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.lys.publicpro.common.BaseController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liwudi
 * @since 2021-03-19
 */
@RestController
@RequestMapping("/expense")
@Api(tags = {"日常管理"})
public class ExpenseController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(ExpenseController.class);
    @Autowired
    ExpenseTypeBo expenseTypeBo;

    //查询所有消费类别
    @ApiOperation(value = "查询所有消费类别", httpMethod = "GET")
    @ApiImplicitParam(value = "消费类型", name = "expenseType")
    @GetMapping("/findAllExpenseType")
    public Result findAllExpenseType(String expenseType) throws Exception {
        return onSuccess(expenseTypeBo.findAllExpenseType(expenseType));
    }
    //新增消费类型
    @ApiOperation(value = "新增消费类别", httpMethod = "PUT")
    @PutMapping("/addExpenseType")
    public Result addExpenseType(@RequestBody ExpenseTypeDTO expenseTypeDTO) throws Exception {
        return onSuccess(expenseTypeBo.addExpenseType(expenseTypeDTO));
    }
    //修改消费类型
    @ApiOperation(value = "修改消费类别", httpMethod = "POST")
    @PostMapping("/addExpenseType")
    public Result updateExpenseType(ExpenseTypeDTO expenseTypeDTO) throws Exception {
        return onSuccess(expenseTypeBo.updateExpenseType(expenseTypeDTO));
    }
    //删除消费类型
    @ApiOperation(value = "删除消费类型", httpMethod = "DELETE")
    @DeleteMapping("/deleteExpenseType")
    public Result deleteExpenseType(Integer id) throws Exception {
        return onSuccess(expenseTypeBo.deleteExpenseType(id));
    }
}

