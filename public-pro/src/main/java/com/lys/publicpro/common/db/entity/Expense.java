package com.lys.publicpro.common.db.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author liwudi
 * @since 2021-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_expense")
@ApiModel(value="Expense对象", description="")
public class Expense implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "金额")
    private Double money;

    @ApiModelProperty(value = "消费类型")
    @TableField(value = "expense_type")
    private String expenseType;

    @ApiModelProperty(value = "消费方式")
    @TableField(value = "expense_way")
    private String expenseWay;

    @ApiParam(hidden = true)
    private Integer year;
    @ApiParam(hidden = true)
    private Integer month;

    @ApiModelProperty(value = "消费时间")
    @TableField(value = "expense_date")
    private Long expenseDate;

    @ApiModelProperty(value = "备注")
    private String remark;

//    @ApiModelProperty(value = "创建时间")
//    private LocalDateTime createTime;
//
//    @ApiModelProperty(value = "更新时间")
//    private LocalDateTime updateTime;


}
