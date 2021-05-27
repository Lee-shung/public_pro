<template>
    <div class="sider-main">
        <div class="title"><img src="./../assets/img/logo.png">李无敌管理平台</div>
        <a-menu
            mode="inline"
            :openKeys="openKeys"
            v-model:selectedKeys="selectedKeys"
            @openChange="onOpenChange"
            @click="onClickItem"
            @select="onSelect"
            theme="dark"
        >
            <a-sub-menu v-for="item in state.menuList" :key="item.key">
                <template #title>
                    <span>{{ item.name }}</span>
                </template>
                <template v-if="item.child && item.child.length">
                    <a-menu-item :key="child.key" v-for="child in item.child">{{
                            child.name
                        }}
                    </a-menu-item>
                </template>
            </a-sub-menu>
        </a-menu>
    </div>
</template>

<script>
import {reactive, getCurrentInstance} from "vue";
import { mapState } from 'vuex';
export default {
    name: "SiderBar",
    computed: {
        ...mapState(['keepMenuList', 'openKeys', 'selectedKeys'])
    },
    setup(props) {
        const state = reactive({
            menuList: [
                {
                    name: "主页",
                    key: "home",
                    child: [
                        {
                            name: "控制台",
                            key: "control",
                        },
                    ],
                },
                {
                    name: "日常管理",
                    key: "daily",
                    child: [
                        {
                            name: "收入列表",
                            key: "income",
                        },
                        {
                            name: "支出列表",
                            key: "expense",
                        },
                        {
                            name: "消费类型",
                            key: "consumptionType",
                        },
                        {
                            name: "收入类型",
                            key: "incomeType",
                        },
                    ],
                },
            ],
        });
        const app = getCurrentInstance();

        /**
         * 菜单点击事件
         * @param openkeys 已展开的菜单
         */
        const onOpenChange = (openkeys) => {
            if (!openkeys.length) {
                app.proxy.$store.dispatch('setOpenKeys', []);
                return;
            }
            //只展开一个
            const openKeyList = openkeys.filter((item, index) => {
                if (index === openkeys.length - 1) {
                    return item;
                }
            });
            app.proxy.$store.dispatch('setOpenKeys', openKeyList);
        };

        /**
         * 菜单项点击事件跳转路由
         * @param obj
         */
        const onClickItem = ({keyPath}) => {
            //返回的菜单顺序是子菜单到父菜单，这里将数组倒序反过来
            const newPathArr = keyPath.reverse();
            let path = '';
            newPathArr.forEach(element => {
                path += '/' + element;
            });
            const index = state.menuList.findIndex(element => element.key === newPathArr[0]);
            let keepMenu;
            state.menuList[index].child.forEach(element => {
                if (element.key === newPathArr[1]) {
                        keepMenu = element;
                }
            })
            keepMenu.path = path;
            const pathIndex = app.proxy.keepMenuList.findIndex(element => element.key === keepMenu.key);
            //如果点击的页面已经存在
            if (pathIndex > -1) {
                app.proxy.$store.dispatch('setIsActive', pathIndex);
            } else {
                app.proxy.$store.dispatch('setIsActive', app.ctx.keepMenuList.length);
                app.proxy.$store.dispatch('setKeepMenuList', keepMenu);
            }
            app.proxy.$router.push(path);
        };
        const onSelect = ({selectedKeys} ) => {
            app.proxy.$store.dispatch('setSelectKey', selectedKeys)
        }
        return {
            state,
            onOpenChange,
            onClickItem,
            onSelect
        };
    },
};
</script>

<style scoped lang="less">
.title {
    line-height: 60px;
    //background: #001529;
    text-align: center;
    color: #fff;
    font-size: 16px;
    font-weight: bold;

    img {
        width: 32px;
    }
}

.sider-main {
    height: 100vh;
}
</style>