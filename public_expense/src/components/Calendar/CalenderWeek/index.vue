<template>
    <div class="calendar-week">
        <div class="item" v-for="(item, i) in week" :key="i">{{ item }}</div>
    </div>
</template>

<script>
import { defineComponent, ref, getCurrentInstance } from 'vue';
export default defineComponent({
    name: "CalendarWeek",
    setup() {
        const app = getCurrentInstance();
        const week = ref(['一', '二', '三', '四', '五', '六', '日']);

        return {
            week
        }
    }
})
</script>

<style scoped lang="less">
.calendar-week {
    display: flex;
    justify-content: space-around;

    .item {
        width: 14.22%;
        border-bottom: 1px solid #dddddd;
        border-right: 1px solid #dddddd;
        //margin-right: -1px;
        height: 30px;
        text-align: center;
        line-height: 30px;
    }
}
</style>