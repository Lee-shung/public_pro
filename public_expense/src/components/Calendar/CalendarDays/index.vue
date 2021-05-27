<template>
    <div class="calendar-days">
        <div
            class="item"
            v-for="(item, i) in showDays"
            :key="i"
            :style="{
                color: item.dayType !== 'current' ? '#bbbbbb' : '',
                height: height,
                lineHeight: height
            }"
            :class="(item.flag && new Date().getDate() == item.day) ? 'active-item' : ''"
        >
            <div
                >
                {{ item.day }}
            </div>
        </div>
    </div>

</template>

<script>
import {defineComponent, reactive, ref, getCurrentInstance, watch} from 'vue';
import commonUtils from '@/assets/ts/commonUtils.ts';
export default defineComponent({
    name: "CalendarDays",
    props: {
        height: {
            type: String,
            default: '80px'
        },
        dateObj: Object
    },
    setup() {
        const app = getCurrentInstance();
        const showDays = ref([]);
        //获取当月天数
        const getDays = (year, month) => {
            //获取需要展示的月份
            const currentDate = new Date(`${year}-${ commonUtils.dbValue(month) }`);
            //获取一号星期几
            const week = currentDate.getDay() || 7;//星期天返回0
            //获取下一个月份
            let nextDate;

            if (month < 12) {
                nextDate = new Date(`${year}-${ commonUtils.dbValue(month + 1) }`);
            } else {
                nextDate = new Date(`${year + 1}-01`);
            }
            //获取当月天数
            const days = (nextDate.getTime() - currentDate.getTime())/86400000;

            return { week, days };
        }
        //生成当月展示天数集合
        const getShowDays = (year, month) => {
            //是否当月
            let flag = false;
            const date = new Date();
            if (date.getFullYear() === year && (date.getMonth() + 1) === month) flag = true;
            const showDaysObj = getDays(year, month);
            let prevDaysObj;
            if (month > 1) {
                prevDaysObj = getDays(year, month - 1);
            } else {
                prevDaysObj = getDays(year - 1, 12);

            }
            const showDays = [];
            //当前天数
            for (let i = 1; i <= showDaysObj.days; i++) {
                showDays.push({
                    day: i,
                    dayType: 'current',
                    date: `${ year }-${ month }}`,
                    flag
                });
            }
            //上一个月
            const { days } = prevDaysObj;
            const { week } = showDaysObj;
            for (let i = days; i > days - week + 1; i--) {
                showDays.unshift({
                    day: i,
                    dayType: 'prev',
                    date: `${ year }-${ month - 1 }}`
                })
            }
            //下一个月
            const len = showDays.length;
            for (let i = 1; i <= 42 - len; i++) {
                showDays.push({
                    day: i,
                    dayType: 'next',
                    date: `${ year }-${ month + 1 }}`

                })
            }
            return showDays
        }
        //初始化函数
        const init = ({year, month}) => {
            showDays.value = getShowDays(year, month);
        }
        init(app.proxy.dateObj);
        watch(
            () => app.proxy.dateObj,
            (val) => {
                if (val) init(val);
            },
            { deep: true },

        );
        return {
            getShowDays,
            showDays,
            getDays,
        }
    }
})
</script>

<style scoped lang="less">
.calendar-days {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
    .item {
        width: 14.2%;
        //border: 1px solid #dddddd;
        //margin: -1px -1px 0 0;
        border-bottom: 1px solid #dddddd;
        border-right: 1px solid #dddddd;
        text-align: center;
        cursor: pointer;
        &:hover {
            background: #dffcfc;
        }
    }
    .active-item {
        background: #dffcfc;
        font-weight: bold;
    }
}
</style>
