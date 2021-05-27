<template>
    <div class="calendar-head">
        <div class="left">
            <a-month-picker @change="onChangeDate" :allowClear="false" v-model:value="date" placeholder="选择月份" format="YYYY年MM月" />
        </div>
        <div class="right"></div>
    </div>

</template>

<script>
import {defineComponent, reactive, ref, getCurrentInstance} from 'vue';
import moment from 'moment';
export default defineComponent({
    name: "CalendarHead",
    setup() {
        const app = getCurrentInstance();
        const date = ref(moment(new Date(), "YYYY年MM月"));
        const onChangeDate = (date,dateString) => {
            app.proxy.$emit('on-change-date', { year: date.year(), month: date.month() + 1 });
        }
        return {
            date,
            moment,
            onChangeDate
        }
    }
})
</script>

<style scoped lang="less">
.calendar-head {
    padding: 10px 0;
}
</style>
