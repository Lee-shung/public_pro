package com.lys.publicpro.common.db.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 李无敌
 * @date 2021年03月01日 16:21
 */
@TableName(value = "tb_deal_way")
@Data
public class DealWay {
    private Integer id;
    @TableField(value = "param_key")
    private String paramKey;
    @TableField(value = "param_value")
    private String paramValue;
}
