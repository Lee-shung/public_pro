/*
 * @Author: 李无敌
 * @Date: 2020-11-19 17:19:05
 * @LastEditors: 李无敌
 * @LastEditTime: 2021-02-24 16:04:38
 * @FilePath: \myworkpace\public_expense\src\main.ts
 */
import { createApp } from 'vue'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css';
import './assets/css/common.less'
import App from './App.vue'
import router from './router'
import store from './store'
import 'animate.css/animate.css'
import axios from 'axios';
import { Modal } from 'ant-design-vue';
import { getStore, setStore, clearStore } from '@/assets/ts/storage';
import dictUtils from '@/assets/ts/dictUtils';
import commonUtils from '@/assets/ts/commonUtils';
import msgTip from '@/assets/ts/msgTip';
import Page from './components/Page.vue';
import DeleteBtn from './components/DeleteBtn.vue';

if (process.env.NODE_ENV === 'development') {
    // axios.defaults.baseURL = 'http://www.liwudi.cn:8888'
    axios.defaults.baseURL = 'http://localhost:8888'
} else {
    axios.defaults.baseURL = ''

}
// 添加请求拦截器
axios.interceptors.request.use(function (config) {
    
    // 在发送请求之前做些什么
    if(getStore('accessToken')){
        /*设置统一的header*/
        config.headers.Authorization  = getStore('accessToken');
    }
    return config;
  }, function (error) {
    // 对请求错误做些什么
    Modal.error({
        title: '请求超时',
        content: '请检查网络是否错误，或者文件过大...',
      });
    return Promise.reject(error);
  });

// 添加响应拦截器
axios.interceptors.response.use(function (response) {
    const data = response.data;
    // 根据返回的code值来做不同的处理(和后端约定)
    switch (data.code) {
        case 401:
            // 未登录 清除已登录状态
            clearStore(['accessToken', 'accountInfo']);
            router.push('/login');
            break;
        case 403:
            // 没有权限
            if (data.msg !== null) {
                Modal.error({
                    title: '没有权限',
                    content: data.msg
                });
            } else {
                Modal.error({
                    title: "未知错误"
                });
            }
            break;
        case 500:
            // 错误
            if (data.msg !== null) {
                Modal.error({
                    title: '错误',
                    content: data.msg
                });
            } else {
                Modal.error({
                    title: '未知错误',
                });
            }
            break;
        default:
            return data;
    }

    return data;
}, (err) => { // 这里是返回状态码不为200时候的错误处理
    Modal.error({
        title: '错误',
        content: err.toString()
    });
    return Promise.resolve(err);
});
const app = createApp(App);
//绑定全局属性
app.config.globalProperties.$dictUtils = dictUtils;
app.config.globalProperties.$commonUtils = commonUtils;
app.config.globalProperties.$msgTip = msgTip;
app.use(store).use(router).use(Antd).component('Page', Page).component('DelButton',DeleteBtn).mount('#app')
