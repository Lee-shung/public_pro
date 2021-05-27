<template>
    <!-- 转换为中文 -->
    <a-config-provider :locale="zhCN">
        <router-view/>
    </a-config-provider>
</template>
<script>
import {defineComponent, ref} from 'vue';
import zhCN from 'ant-design-vue/es/locale/zh_CN';
import moment from 'moment';
import 'moment/dist/locale/zh-cn';
moment.locale(zhCN.locale);
export default defineComponent({
    setup() {
        const locale = ref(zhCN.locale);
        return {
            moment,
            locale,
            zhCN
        }
    }
})
</script>
<style>
#app {
    height: 100%;
}

</style>
