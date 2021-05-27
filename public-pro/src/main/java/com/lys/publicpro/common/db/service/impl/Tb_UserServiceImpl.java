package com.lys.publicpro.common.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lys.publicpro.common.db.mapper.Tb_UserMapper;
import com.lys.publicpro.common.db.entity.TbUser;
import com.lys.publicpro.common.db.service.Tb_UserService;
import org.springframework.stereotype.Service;

@Service
public class Tb_UserServiceImpl extends ServiceImpl<Tb_UserMapper, TbUser> implements Tb_UserService {

}
