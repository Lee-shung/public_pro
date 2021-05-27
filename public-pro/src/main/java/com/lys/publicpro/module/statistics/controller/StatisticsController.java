package com.lys.publicpro.module.statistics.controller;

import com.lys.publicpro.common.utils.ResultUtils;
import com.lys.publicpro.common.vo.Result;
import com.lys.publicpro.module.statistics.bo.StatisticsBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李无敌
 * @date 2021年05月21日 8:46
 */
@RestController
@RequestMapping("/statistics")
@Api(tags = {"数据统计"})
public class StatisticsController {
    @Autowired
    StatisticsBo statisticsBo;

    @ApiOperation(value = "获取月度总结", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "月", name = "month"),
            @ApiImplicitParam(value = "年", name = "year")
    })
    @GetMapping("/getMonthData")
    public Result getMonthData(Integer month, Integer year) throws Exception {
        Object obj = statisticsBo.getMonthDataByMonth(month, year);
        if (obj == null) {
            return ResultUtils.reError();
        }
        return ResultUtils.reSuccess(obj);
    };

    @ApiOperation(value = "获取年度总结", httpMethod = "GET")
    @ApiImplicitParam(value = "年", name = "year")
    @GetMapping("/getYearData")
    public Result getYearData(Integer year) throws Exception {
        return statisticsBo.getYearData(year);
    }
}
