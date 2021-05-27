package com.lys.publicpro.module.accountInfo.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 李无敌
 * @date 2021年04月19日 11:08
 */
@ApiModel
@Data
@NoArgsConstructor                 //无参构造
@AllArgsConstructor                //有参构造
public class AccountInfoVO {
    //账号
    private String accountName;
    //密码
//    private String accountPassword;
    //权限
    private String roleList;
    //姓名
    private String name;
    //性别
    private String gender;
    //生日
    private String birthday;
    //住址
    private String address;
    //联系方式
    private String phone;
    //身份证
    private String idcard;
}
