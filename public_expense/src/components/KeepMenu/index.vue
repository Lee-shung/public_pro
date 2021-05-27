<template>
    <div class="keep-menu">
        <keep-menu-item
            @on-change="onGoUrl(item,i)"
            :is-active="isActive === i"
            v-for="(item,i) in keepMenuList"
            :key="i"
            :menu-item="item"
            @on-close="onClose(i)"
        ></keep-menu-item>
    </div>
</template>

<script>
import { defineComponent, ref, reavtive, getCurrentInstance, onUpdated, onMounted } from 'vue';
import KeepMenuItem from './KeepMenuItem/index.vue';
import { mapState } from 'vuex';
export default defineComponent({
    name: "KeepMenu",
    components: {
        KeepMenuItem
    },
    computed: {
        ...mapState(['keepMenuList', 'isActive'])
    },
    setup() {
        const app = getCurrentInstance();
        // onUpdated(() => {
        //     const count = app.ctx.keepMenuList && app.ctx.keepMenuList.length - 1;
        //     isActive.value = count;
        // });

        const onGoUrl = (item,i) => {
            app.proxy.$store.dispatch('setIsActive', i);
            app.proxy.$store.dispatch('setOpenKeys', [item.path.split('/')[1]]);
            app.proxy.$store.dispatch('setSelectKey', [item.key]);
            app.proxy.$router.push(item.path);
        }
        //初始化历史菜单
        const keepMenu = {
            name: '控制台',
            key: 'control',
            path: '/home/control'
        }
        const onClose = (i) => {
            const isActive = app.ctx.isActive;
            if (i === isActive) onGoUrl(app.proxy.keepMenuList[i-1], i-1);
            if (i < isActive) app.proxy.$store.dispatch('setIsActive', isActive-1);
            app.proxy.$store.commit('DEL_KEEP_MENU', i);
        }
        onMounted(() => {
            const isExist = app.proxy.keepMenuList && app.proxy.keepMenuList.some(item => item.key === keepMenu.key);
            if (!isExist) {
                app.proxy.$store.dispatch('setKeepMenuList', keepMenu);
            }
            const len = app.proxy.keepMenuList.length;
            onGoUrl(app.proxy.keepMenuList[len - 1], len - 1);
        })
        return {
            // keepMenuList,
            onGoUrl,
            onClose
        }
    }

})
</script>

<style scoped lang="less">
.keep-menu {
    height: 40px;
    padding: 0 16px;
    display: flex;
    align-items: center;
    line-height: 40px;
    border-top: 1px solid #eeeeee;
    border-bottom: 1px solid #eeeeee;

}
</style>