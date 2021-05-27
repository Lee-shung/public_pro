package com.lys.publicpro.module.commonSys.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lys.publicpro.common.db.entity.DealWay;
import com.lys.publicpro.common.db.mapper.DealWayMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 李无敌
 * @date 2021年03月01日 16:43
 */
@Service
public class CommonSysBo {
    @Resource
    DealWayMapper dealWayMapper;

    //获取交易方式
    public List<DealWay> findAllDealWay() throws Exception {
        QueryWrapper<DealWay> wrapper = new QueryWrapper<>();
        List<DealWay> list = dealWayMapper.selectList(wrapper);
        return list;
    }
}
