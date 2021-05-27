package com.lys.publicpro.module.userManage.bo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lys.publicpro.common.bo.BaseBo;
import com.lys.publicpro.common.db.entity.TbUser;
import com.lys.publicpro.common.db.mapper.Tb_UserMapper;
import com.lys.publicpro.common.dto.PageModel;
import com.lys.publicpro.common.utils.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class Tb_UserBo extends BaseBo {
    @Resource
    Tb_UserMapper tb_userMapper;
    private static Logger logger = LoggerFactory.getLogger(Tb_UserBo.class);
    //添加用户
    public boolean addUser(String name, String gender, String birthday, String address, String phone, String idcard) throws Exception {
        boolean result = true;
        TbUser user = new TbUser();
        user.setId(DBUtils.getUuid());
        user.setAddress(address);
        user.setBirthday(birthday);
        user.setGender(gender);
        user.setName(name);
        user.setPhone(phone);
        user.setIdcard(idcard);
        int count = tb_userMapper.insert(user);
        result = count > 0 ? true : false;
        return result;
    };
    //修改用户
    public boolean updateUserById(TbUser user) throws Exception {
        boolean result = true;
        int count = tb_userMapper.updateById(user);
        result = count > 0 ? true : false;
        return result;
    };
    //删除用户
    public boolean deleteUserById(String id) throws Exception {
        int i = tb_userMapper.deleteById(id);
        return i > 0 ? true : false;
    };
    //分页查询用户
    public IPage findAllUser(PageModel pageModel, String searchValue) throws Exception {
        LambdaQueryWrapper<TbUser> lambda = new QueryWrapper<TbUser>().lambda();
//        System.out.println(searchValue);
        if (searchValue == null) {
            searchValue = "";
        };
        lambda.like(TbUser::getName, searchValue);
        IPage page = tb_userMapper.selectPage(pageModel, lambda);
        return page;
    }
    //根据用户id查询用户
    public TbUser getUserById(String userId) {
        return tb_userMapper.selectById(userId);
    }
}
