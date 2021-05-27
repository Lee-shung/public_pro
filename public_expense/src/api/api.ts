/*
 * @Author: 李无敌
 * @Date: 2021-02-04 15:39:59
 * @LastEditors: 李无敌
 * @LastEditTime: 2021-02-24 15:11:35
 * @FilePath: \myworkpace\public_expense\src\api\api.ts
 */
import { get, put, post, del } from './request';

/**
 * 公共模块
 * @param params
 */
//获取交易方式
export const apiFindAllDealWay = (params: any) => get('/common/findAllDealWay', params);



export const apiFindAllUser = (params: any) => get('/user/findAllUser', params);
/**
 * 日常管理模块
 * @param params
 */
//获取收入类型
export const apiGetAllIncomeType = (params: any) => get('/daily/findIncomeType', params);
//获取收入列表
export const apiFindIncomePage = (params: any) => get('/daily/findIncomePage', params);
//新增收入账单
export const apiAddIncome = (params: any) => put('/daily/addIncome', params);
//修改收入账单
export const apiUpdateIncome = (params: any) => post('/daily/updateIncome', params);
//删除收入账单
export const apiDelIncome = (params: any) => del('/daily/delIncome', params);

//查询所有消费类别
export const apiFindAllExpenseType = (params: any) => get('/expense/findAllExpenseType', params);
//删除消费类型
export const apiDeleteExpenseType = (params: any) => del('/expense/deleteExpenseType', params);
/**
 * 用户模块
 * @param params
 */
//登录
export const apiLogin = (params: any) => post('/sysUser/login', params);


/*
 * 数据统计
 */
//获取月度总结
export const apiGetMonthData = (params: any) => get('/statistics/getMonthData', params);
//获取年度数据
export const apiGetYearData = (params: any) => get('/statistics/getYearData', params);