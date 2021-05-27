package com.lys.publicpro.common.db.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 李无敌
 * @date 2021年02月06日 16:12
 */
@Data
@TableName(value = "tb_account_info")
public class AccountInfo {
    private Integer id;
    @TableField(value = "user_id")
    private String userId;
    @TableField(value = "acount_name")
    private String accountName;
    @TableField(value = "acount_password")
    private String accountPassword;
    @TableField(value = "role_list")
    private String roleList;
}
