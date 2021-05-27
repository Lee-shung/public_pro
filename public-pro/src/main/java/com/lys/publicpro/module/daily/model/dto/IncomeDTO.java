package com.lys.publicpro.module.daily.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 李无敌
 * @date 2021年02月24日 10:42
 */
@Data
@ApiModel
public class IncomeDTO {
    @ApiModelProperty(value = "最小金额", name = "smallMoney")
    private Double smallMoney;
    @ApiModelProperty(value = "最大金额", name = "bigMoney")
    private Double bigMoney;
    @ApiModelProperty(value = "收入类型", name = "incomeType")
    private String incomeType;
    @ApiModelProperty(value = "入款方式", name = "incomeWay")
    private String incomeWay;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "开始到账时间", name = "startDate")
    private Long startDate;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后到账时间", name = "endDate")
    private Long endDate;
}
