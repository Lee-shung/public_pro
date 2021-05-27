package com.lys.publicpro.common.db.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lys.publicpro.common.utils.DateFormatUtils;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import net.bytebuddy.implementation.bytecode.Throw;

import java.text.ParseException;
import java.util.Date;

/**
 * @author 李无敌
 * @date 2021年02月24日 10:19
 */
@Data
@TableName(value = "tb_income")
@ApiModel
public class Income {
    @ApiModelProperty(value = "收入账单id", name = "id")
    private String id;
    @ApiModelProperty(value = "金额", name = "money")
    private Double money;
    @TableField(value = "income_type")
    @ApiModelProperty(value = "收入类型", name = "incomeType")
    private String incomeType;
    @ApiModelProperty(value = "收款方式", name = "incomeWay")
    @TableField(value = "income_way")
    private String incomeWay;
    @ApiModelProperty(value = "入账时间", name = "accountDate")
    @TableField(value = "account_date")
    private Long accountDate;
    @ApiParam(hidden = true)
    private Integer year;
    @ApiParam(hidden = true)
    private Integer month;
    @ApiModelProperty(value = "备注", name = "remark")
    private String remark;

//    public Date getAccountDate() throws ParseException {
//        Date date = new Date();
//        try {
//            date = DateFormatUtils.formatDate(accountDate);
//        } catch (ParseException e) {
//
//        }
//        return date;
//    }
}
