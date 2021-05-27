package com.lys.publicpro.module.daily.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lys.publicpro.common.BaseController;
import com.lys.publicpro.common.db.entity.Income;
import com.lys.publicpro.common.dto.PageModel;
import com.lys.publicpro.common.vo.Result;
import com.lys.publicpro.module.daily.bo.IncomeBo;
import com.lys.publicpro.module.daily.bo.IncomeTypeBo;

import com.lys.publicpro.module.daily.model.dto.IncomeDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 李无敌
 * @date 2021年02月23日 15:04
 */
@RestController
@Api(tags = {"日常管理"})
@RequestMapping("/daily")
public class IncomeController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(IncomeController.class);
    @Autowired
    IncomeBo incomeBo;
    @Autowired
    IncomeTypeBo incomeTypeBo;

    @ApiOperation(value = "查询收入类型", httpMethod = "GET")
    @ApiImplicitParam(value = "收入类型", name = "incomeType")
    @GetMapping("/findIncomeType")
    public Result<Object> findIncomeType(String incomeType) throws Exception {
        return onSuccess(incomeTypeBo.findIncomeType(incomeType));
    }

    //分页查询收入列表
    @ApiOperation(value = "分页查询收入列表", httpMethod = "GET")
    @GetMapping("/findIncomePage")
    public Result<IPage<Income>> findIncomePage(PageModel pageModel, IncomeDTO incomeDTO) throws Exception {
        Result result = new Result();
        try {
            result = onSuccess(incomeBo.findIncomePage(pageModel, incomeDTO));
        } catch (Exception e) {
            logger.error("{出错了}"+e.getMessage());
            result = onError(e);
        }
        return result;
    }

    //新增收入账单
    @ApiOperation(value = "新增收入账单", httpMethod = "PUT")
    @PutMapping("/addIncome")
    public Result addIncome(@RequestBody Income income) throws Exception {
        return onSuccess(incomeBo.addIncome(income));
    }
    //修改收入账单
    @ApiOperation(value = "修改收入账单", httpMethod = "POST")
    @PostMapping("/updateIncome")
    public Result updateIncome(Income income) throws Exception {
        return onSuccess(incomeBo.updateIncome(income));
    }
    //删除收入账单
    @ApiOperation(value = "删除收入账单", httpMethod = "DELETE")
    @DeleteMapping("/delIncome")
    public Result delIncome(String id) throws Exception {
        return onSuccess(incomeBo.delIncome(id));
    }
}
