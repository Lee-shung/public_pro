<template>
    <div class="flex-between">
        <div class="expense-item">
            <div class="content">
                <div style="margin-bottom: 20px">
                    <a-statistic title="本月收入" :value-style="{ color: '#3f8600' }" :precision="2"  :value="data.income.monthTotal" style="margin-right: 50px" />
                </div>
                <a-statistic
                    title="主要来源"
                    :value="41.28"
                    :precision="2"
                    suffix="%"

                    style="margin-right: 50px"
                >
                </a-statistic>
            </div>
        </div>
        <div class="expense-item">
            <div class="content">
                <div style="margin-bottom: 20px">
                    <a-statistic title="本月支出" :value-style="{ color: '#cf1322' }" :value="data.expense.monthTotal" style="margin-right: 50px" />
                </div>
                <a-statistic
                    title="主要用途"
                    :value="51.28"
                    :precision="2"
                    suffix="%"
                    style="margin-right: 50px;"
                >
                </a-statistic>
            </div>
        </div>
    </div>
</template>

<script>
import {defineComponent, reactive, ref, getCurrentInstance} from 'vue';
import { apiGetMonthData } from '@/api/api.ts';

export default defineComponent({
    name: "MonthData",
    setup() {
        const app = getCurrentInstance();
        const data = reactive({
            income: {
                monthTotal: 0
            },
            expense: {
                monthTotal: 0
            }
        })
        apiGetMonthData({ month: new Date().getMonth() + 1, year: new Date().getFullYear() }).then(res => {
            data.income = res.income;
            data.expense = res.expense;
        });
        return {
            data
        }
    }
})
</script>

<style scoped lang="less">
.expense-item {
    width: 50%;
    padding: 5px;
    display: flex;
    justify-content: space-between;
    .content {
        background: #f5f5f5;
        width: 100%;
        padding: 5px;
    }
}
</style>
