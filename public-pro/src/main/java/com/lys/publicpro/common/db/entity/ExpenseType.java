package com.lys.publicpro.common.db.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.lys.publicpro.common.utils.DateFormatUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author liwudi
 * @since 2021-03-19
 */
@TableName("tb_expense_type")
@ApiModel(value="ExpenseType对象", description="")
@Data
public class ExpenseType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "消费类型id")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "消费类型")
    @TableField(value = "expense_type")
    private String expenseType;
    @ApiModelProperty(value = "消费类型key", name = "expenseTypeKey")
    @TableField(value = "expense_type_key")
    private String expenseTypeKey;
    @ApiModelProperty(value = "图标", name = "iconUrl")
    @TableField(value = "icon_url")
    private String iconUrl;
    @ApiModelProperty(value = "修改时间", name = "updateTime")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime = new Date();
    @ApiModelProperty(value = "备注")
    private String remark;

    public String getUpdateTime() {
        return DateFormatUtils.formatDate(updateTime);
    }
}
