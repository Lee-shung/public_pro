<!--
 * @Author: 李无敌
 * @Date: 2020-11-20 16:39:42
 * @LastEditors: 李无敌
 * @LastEditTime: 2021-02-07 09:13:02
 * @FilePath: \myworkpace\public_expense\src\views\Main.vue
-->
<template>
    <a-layout id="custom-trigger">
        <a-layout-sider width="240">
            <SiderBarVue></SiderBarVue>
        </a-layout-sider>
        <a-layout>
            <a-layout-header style="background: #fff; padding: 0;height: 100px;">
                <Head/>
                <KeepMenu></KeepMenu>
            </a-layout-header>
            <a-layout-content
                :style="{ padding: '16px', minHeight: '280px' }"
            >
                <router-view></router-view>
            </a-layout-content>
            <a-footer>
                <div class="footer">
                    <span>Copyright © 2021-2022</span>
                    <span style="margin: 0 20px">Liys. All Rights Reserved. 李无敌 版权所有</span>
                    <span>湘ICP备2021008523号-1</span>
                </div>
            </a-footer>

        </a-layout>
    </a-layout>
</template>

<script>

import Head from '@/components/Head';
import KeepMenu from '@/components/KeepMenu';
import SiderBarVue from '../components/SiderBar.vue';

export default {
    name: "Main",
    components: {
        SiderBarVue,
        Head,
        KeepMenu
    },
    data() {
        return {}
    }
}
</script>

<style scoped lang="less">
#custom-trigger {
    //background: url("../assets/img/bg.jpeg") no-repeat center;
    .logo {
        height: 32px;
        margin: 16px;
    }
}
.footer {
    height: 60px;
    line-height: 60px;
    color: #333333;
    text-align: center;
}
</style>