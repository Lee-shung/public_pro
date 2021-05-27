package com.lys.publicpro.common.thread;

import com.lys.publicpro.module.statistics.bo.StatisticsBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author 李无敌
 * @date 2021年05月27日 14:56
 * 在普通类中调用service方法
 */

@Component //通过@Component注解为组件加载
public class ServerHandler {
    private static ServerHandler serverHandler;
    @Autowired
    StatisticsBo statisticsBo;
    @PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
    public void init() {
        serverHandler = this;
        serverHandler.statisticsBo = this.statisticsBo;
    }
    //获取当月收支
    public static Object getMonthDataByMonth(Integer month, Integer year) throws Exception {
        return serverHandler.statisticsBo.getMonthDataByMonth(month, year);
    }
}

