package com.lys.publicpro.common.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户对象")
@TableName(value = "tb_user")
public class TbUser {
    @ApiModelProperty(value = "用户id", name = "id", required = true)
    private String id;
    @ApiModelProperty(value = "姓名", name = "name")
    private String name;
    @ApiModelProperty(value = "性别", name = "gender")
    private String gender;
    @ApiModelProperty(value = "生日", name = "birthday")
    private String birthday;
    @ApiModelProperty(value = "住址", name = "address")
    private String address;
    @ApiModelProperty(value = "联系电话", name = "phone")
    private String phone;
    @ApiModelProperty(value = "身份证", name = "idcard")
    private String idcard;
}
