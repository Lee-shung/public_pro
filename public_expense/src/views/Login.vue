<!--
 * @Author: 李无敌
 * @Date: 2021-02-22 11:11:43
 * @LastEditors: 李无敌
 * @LastEditTime: 2021-02-22 11:12:02
 * @FilePath: \myworkpace\public_expense\src\views\Login.vue
-->
<template>
    <div class="login-wrap">
        <canvas class="cavs" width="100%" height="100%"></canvas>
        <div class="login-form">
            <div class="form">
                <div class="title">登录</div>
                <a-form
                    ref="formRef"
                    :model="dataForm"
                    :rules="rules"
                    :wrapper-col="{ style: 'width: 100%' }"
                >
                    <a-form-item name="accountName">
                        <a-input @keyup.enter="onLogin" placeholder="账号" v-model:value="dataForm.accountName" >
                            <template v-slot:prefix>
                                <user-outlined type="user" />
                            </template>
                        </a-input>
                    </a-form-item>
                    <a-form-item name="accountPwd">
                        <a-input-password @keyup.enter="onLogin" v-model:value="dataForm.accountPwd" placeholder="密码">
                            <template #prefix>
                                <lock-outlined />
                            </template>
                        </a-input-password>
                    </a-form-item>
                </a-form>
                <a-button type="primary" @click="onLogin" style="width: 100%">登录</a-button>
            </div>
        </div>
    </div>
</template>

<script>
import { canvas } from './../assets/js/canvas.js';
import { defineComponent, ref, reactive, getCurrentInstance, readonly } from 'vue';
import { UserOutlined, LockOutlined } from '@ant-design/icons-vue';
import { apiLogin } from './../api/api.ts';
import { setStore } from './../assets/ts/storage.ts';
export default defineComponent({
    components: {
        UserOutlined,
        LockOutlined
    },
    setup() {
        canvas();
        const app = getCurrentInstance();
        const global = app.appContext.config.globalProperties;
        const dataForm = reactive({
            accountName: '',
            accountPwd: ''
        });
        const rules = readonly({
            accountName: [ { required: true, message: '请输入账号', trigger: 'blur' } ],
            accountPwd: [ { required: true, message: '请输入密码', trigger: 'blur' } ]
        });
        const formRef = ref();
        const onLogin = () => {
            formRef.value.validate().then(() => {
                apiLogin({ accountName: dataForm.accountName, accountPwd: dataForm.accountPwd }).then(res => {
                    if (res) {
                        global.$msgTip.success("登录成功");
                        setStore('accessToken', res.token);
                        setStore('accountInfo', JSON.stringify(res.data));
                        app.proxy.$router.go(-1)
                    }
                });
            }).catch(err => {
                console.log(err);
            });

        }
        return {
            dataForm,
            onLogin,
            rules,
            formRef
        }
    }
})
</script>

<style scoped lang="less">
.login-wrap {
    width: 100%;
    height: 100%;
    position: relative;
    background: url("../assets/img/bg1.jpg") no-repeat;
    background-size: 100% 100%;


}
.cavs{
    position: fixed;
    width:95%;
    margin-left: 20px;
    margin-right: 20px;
}
.login-form {
    width: 500px;
    margin: 0 auto;
    padding-top: 200px;

    .form {
        padding: 80px;
        background: rgba(0,0,0,.3);

        .title {
            color: #ffffff;
            font-size: 24px;
            margin-bottom: 30px;
            text-align: center;

        }
    }

}
</style>