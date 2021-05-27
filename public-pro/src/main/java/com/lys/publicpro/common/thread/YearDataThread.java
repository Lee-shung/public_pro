package com.lys.publicpro.common.thread;

import com.lys.publicpro.module.statistics.bo.StatisticsBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * @author 李无敌
 * @date 2021年05月27日 11:07
 */
public class YearDataThread implements Callable<Object> {

    private Integer month;
    private Integer year;
    public YearDataThread(Integer month, Integer year) {
        this.month = month;
        this.year = year;
    }

    @Override
    public Object call() throws Exception {
        //获取当月收支
        return ServerHandler.getMonthDataByMonth(month, year);
    }

}
