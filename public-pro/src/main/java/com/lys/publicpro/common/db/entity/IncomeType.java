package com.lys.publicpro.common.db.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lys.publicpro.common.utils.DateFormatUtils;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author 李无敌
 * @date 2021年02月23日 14:33
 */
@Data
@TableName(value = "tb_income_type")
public class IncomeType {
    @ApiModelProperty(value = "id", name = "id")
    private Integer id;
    @ApiModelProperty(value = "收入类型", name = "incomeType")
    @TableField(value = "income_type")
    private String incomeType;
    @ApiModelProperty(value = "收入类型key", name = "incomeTypeKey")
    @TableField(value = "income_type_key")
    private String incomeTypeKey;
    @ApiModelProperty(value = "图标", name = "iconUrl")
    @TableField(value = "icon_url")
    private String iconUrl;
    @ApiModelProperty(value = "修改时间", name = "updateTime")
    @TableField(value = "update_time")
    private Date updateTime;
    @ApiModelProperty(value = "备注", name = "remark")
    @TableField(value = "remark")
    private String remark;
    public String getUpdateTime() {
        return DateFormatUtils.formatDate(updateTime);
    }
}
