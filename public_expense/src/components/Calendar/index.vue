<template>
    <div class="calendar">
        <CalendarHead @on-change-date="getDate"></CalendarHead>
        <div v-if="refresh" class="calendar-body animate__animated animate__zoomIn animate__faster">
            <CalendarWeek></CalendarWeek>
            <CalendarDays :date-obj="dateObj" :height="height"></CalendarDays>
        </div>
    </div>
</template>

<script>
import CalendarWeek from './CalenderWeek/index.vue';
import CalendarDays from './CalendarDays/index.vue';
import CalendarHead from './CalendarHead/index.vue';
import { defineComponent, ref, getCurrentInstance, watch, reactive } from 'vue';
export default defineComponent({
    name: "Calendar",
    components: { CalendarWeek, CalendarDays, CalendarHead },
    props: {
        height: {
            type: String,
            default: '80px'
        }
    },
    setup() {
        const app = getCurrentInstance();
        const dateObj = reactive({
            year: new Date().getFullYear(),
            month: new Date().getMonth() + 1
        });
        const refresh = ref(true);
        const refreshDate = () => {
            refresh.value = false;
            let timer = setTimeout(() => {
                refresh.value = true;
                timer = null;
            },100)
        }
        const getDate = (date) => {
            refreshDate();
            dateObj.year = date.year;
            dateObj.month = date.month;
        }

        return {
            dateObj,
            getDate,
            refresh,
            refreshDate
        }
    }
})
</script>

<style scoped lang="less">
.calendar {
    .calendar-body {
        border-top: 1px solid #dddddd;
        border-left: 1px solid #dddddd;
        //margin: 0 -1px -1px 0;
    }
}
</style>