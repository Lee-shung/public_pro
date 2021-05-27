<!--
 * @Author: 李无敌
 * @Date: 2021-01-05 17:12:39
 * @LastEditors: 李无敌
 * @LastEditTime: 2021-01-28 16:08:24
 * @FilePath: \myworkpace\public_expense\src\views\Control.vue
-->
<template>
    <div class="control-main">
        <div class="left-box">
            <div class="schedule-box animate__animated animate__fadeInLeft animate__faster">
                <a-card title="待办事项" hoverable style="width: 100%; height: 100%">
                    <div class="flex-box">
                        <div class="schedule-item" v-for="i in 7" :key="i">
                            <div>
                                <img alt="" style="width: 24px" src="@/assets/img/logo.png">
                                金标标
                            </div>
                        </div>
                    </div>
                </a-card>
            </div>
            <div class="expense-box animate__animated animate__fadeInRight animate__faster">
                <a-card title="本月收支" hoverable style="width: 100%; height: 100% ">
                    <month-data></month-data>
                </a-card>
            </div>
            <div class="data-box animate__animated animate__fadeInLeft animate__faster">
                <a-card title="数据统计" hoverable style="width: 100%; height: 100%">
                    <data-statistics></data-statistics>
                </a-card>
            </div>
        </div>
        <div class="right-box">
            <div class="today-dream-box  animate__animated animate__fadeInDown animate__faster">
                <a-card title="今日梦想" hoverable style="width: 100%; height: 100%">
                    <today-dream></today-dream>
                </a-card>
            </div>
            <div class="calendar-box  animate__animated animate__fadeInRight animate__faster">
                <a-card title="日历" hoverable style="width: 100%">
                    <Calendar height="40px"></Calendar>
                </a-card>
            </div>
        </div>
    </div>
</template>

<script>
import Calendar from '../../components/Calendar/index.vue'
import DataStatistics from './DataStatistics/index.vue'
import TodayDream from './TodayDream/index.vue'
import MonthData from './MonthData/index.vue'
import { defineComponent, reactive, ref } from 'vue';

export default defineComponent({
    components: { Calendar, DataStatistics, TodayDream, MonthData },
    setup() {
        // const hi = ref('hello')
        // return () => <div class="title">{ hi.value }</div>
        //请求数据后
        // const arr1 = [{}];//权限列表
        // const arr2 = [{}];//授权新人员列表
        // arr2 = arr2.map(element2 => {
        //     element2.isbit = true;
        //     arr1.forEach(element1 => {
        //         if (element2.userName === element1.userName) element2.isbit = false;
        //     });
        //     return element2;
        // })
        
    }
})

</script>

<style lang="less" scoped>
.control-main {
    display: flex;
    flex-wrap: wrap;
    width: 100%;
    justify-content: space-between;
    .left-box {
        width: 68%;
        display: flex;
        flex-wrap: wrap;
        .schedule-box {
            width: 50%;
            padding: 7.5px;
            height: 320px;
            .schedule-item {
                //background: #f5f5f5;
                //padding: 20px;
                width: 25%;
                padding: 5px;
                div {
                    width: 100%;
                    background: #f5f5f5;
                    padding: 20px;
                }
            }
        }
        .expense-box {
            width: 50%;
            padding: 7.5px;
            height: 320px;


        }
        .data-box {
            width: 100%;
            padding: 7.5px;

        }
    }
    .right-box {
        width: 32%;
        .calendar-box {
            width: 100%;
            padding: 7.5px;
        }
        .today-dream-box {
            height: 320px;
            padding: 7.5px;

        }
    }
}


.title {
    color: aquamarine;
}
</style>