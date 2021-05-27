package com.lys.publicpro.module.userManage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lys.publicpro.common.BaseController;
import com.lys.publicpro.common.db.entity.TbUser;
import com.lys.publicpro.common.dto.PageModel;
import com.lys.publicpro.common.utils.ResultUtils;
import com.lys.publicpro.common.vo.Result;
import com.lys.publicpro.module.userManage.bo.Tb_UserBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Api(tags = {"用户管理"})
@RestController
@RequestMapping("/user")
public class Tb_UserController extends BaseController {
    @Autowired
    Tb_UserBo tb_userBo;
    private static Logger logger = LoggerFactory.getLogger(Tb_UserBo.class);

    //添加用户
    @ApiOperation(value = "添加用户",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", required = true, paramType = "query"),
            @ApiImplicitParam(name = "gender", value = "性别", required = true, paramType = "query"),
            @ApiImplicitParam(name = "birthday", value = "生日", required = true, paramType = "query"),
            @ApiImplicitParam(name = "address", value = "住址", required = false, paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "联系电话", required = false, paramType = "query"),
            @ApiImplicitParam(name = "idcard", value = "身份证", required = false, paramType = "query"),
    })
    @PostMapping("/addUser")
    public Result addUser(String name, String gender, String birthday, String address, String phone, String idcard) throws Exception {
        Result result = new Result();
        try {
            result = onSuccess(tb_userBo.addUser(name,gender,birthday,address,phone,idcard));
        } catch (Exception e) {
            logger.error("{出错了：}"+e.getMessage());
            result = onError(e);
        }
        return result;
    };
    @ApiOperation(value = "修改用户",httpMethod = "PUT")
    //根据id修改用户信息
    @PutMapping("/updateUserById")
    public Result updateUserById(@RequestBody TbUser user) throws Exception {
        Result result = new Result();
        try {
            result = onSuccess(tb_userBo.updateUserById(user));
        } catch (Exception e) {
            logger.error("{出错了：}"+e.getMessage());
            result = onError(e);
        }
        return result;
    };
    //删除用户
    @ApiOperation(value = "删除用户",httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true)
    })
    @DeleteMapping("/deleteUserById")
    public Result deleteUserById(String id) throws Exception {
        Result result = new Result();
        try {
            result = onSuccess(tb_userBo.deleteUserById(id));
        } catch (Exception e) {
            logger.error("{出错了}"+e.getMessage());
            result = onError(e);
        }
        return result;
    };
    //分页查询用户
    @ApiOperation(value = "分页查询用户", httpMethod = "GET")
    @ApiImplicitParam(value = "搜索条件：姓名", name = "searchValue")
    @GetMapping("/findAllUser")
    public Result<IPage<TbUser>> findAllUser(PageModel pageModel, String searchValue) throws Exception {
        Result result = new Result();
        try {
            result = ResultUtils.reSuccess(tb_userBo.findAllUser(pageModel, searchValue));
        } catch (Exception e) {
            logger.error("{出错了}"+e.getMessage());
            result = onError(e);
        }
        return result;
    }
}
