package com.lys.publicpro.module.daily.model.vo;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;

/**
 * @author 李无敌
 * @date 2021年02月23日 15:39
 */

public class IncomeTypeVo {
    @Setter
    @Getter
    private Integer id;
    @Setter
    @Getter
    private String incomeType;
    @Setter
    private String updateTime;
    @Setter
    @Getter
    private String remark;

    public String getUpdateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(updateTime);
    }
}
