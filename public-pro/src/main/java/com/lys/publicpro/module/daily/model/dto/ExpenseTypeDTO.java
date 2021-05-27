package com.lys.publicpro.module.daily.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 李无敌
 * @date 2021年03月22日 9:18
 */
@ApiModel
@Data
public class ExpenseTypeDTO {
    @ApiModelProperty(value = "id", name = "id")
    private Integer id;
    @ApiModelProperty(value = "消费类型", name = "expenseType", required = true)
    private String expenseType;
    @ApiModelProperty(value = "备注", name = "remark")
    private String remark;
}
