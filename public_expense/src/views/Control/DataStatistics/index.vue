<template>
    <div>
        <div class="select-dot">
            <i :class="activeIndex === i ? 'active' : ''" v-for="(v,i) in charts" :key="i" @mousemove="changeChart(i)"></i>
        </div>
        <a-spin :spinning="loading" tip="数据加载中...">
            <div id="chart"></div>
        </a-spin>
    </div>
</template>

<script>
import {defineComponent, reactive, ref, getCurrentInstance, onMounted, onUnmounted} from 'vue';
import echarts from '@/assets/ts/echarts.ts';
import { apiGetYearData } from '@/api/api.ts';
export default defineComponent({
    name: "DataStatistics",
    setup() {
        const app = getCurrentInstance();
        const activeIndex = ref(0);
        const loading = ref(false);
        const lineOption = {
            title: {
                text: '2021年收支情况',
                subtext: '李无敌',
                left: 'center'

            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['收入', '支出'],
                left: 'left'

            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月',]
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    name: '收入',
                    type: 'line',
                    smooth: true,
                    data: []
                },
                {
                    name: '支出',
                    type: 'line',
                    smooth: true,
                    data: []
                },
            ]
        };
        const pieOption = {
            title: {
                text: '某站点用户访问来源',
                subtext: '纯属虚构',
                left: 'center'
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                left: 'left',
            },
            series: [
                {
                    name: '访问来源',
                    type: 'pie',
                    radius: '50%',
                    data: [
                        {value: 1048, name: '搜索引擎'},
                        {value: 735, name: '直接访问'},
                        {value: 580, name: '邮件营销'},
                        {value: 484, name: '联盟广告'},
                        {value: 300, name: '视频广告'}
                    ],
                    emphasis: {
                        itemStyle: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        }
        const charts = reactive([
            {
                type: 'line',
                option: lineOption
            },
            {
                type: 'pie',
                option: pieOption
            }
        ]);


        let chart = reactive();
        //初始化图表
        const initChart = ()=> {
            chart.dispose();
            chart = echarts.init(document.getElementById('chart'));
            chart.setOption(charts[activeIndex.value].option)
        }
        const changeChart = (i) => {
            if (activeIndex.value === i) return ;
            activeIndex.value = i;
            initChart();
        }
        //获取年度数据
        const getYearData = (year) => {
            loading.value = true;
            apiGetYearData({ year }).then(res => {
                res.forEach(item => {
                    lineOption.series[0].data.push(item.income.monthTotal);
                    lineOption.series[1].data.push(item.expense.monthTotal);
                    chart = echarts.init(document.getElementById('chart'));
                    loading.value = false;
                    initChart();
                });
            })
        }
        getYearData(new Date().getFullYear());
        // onMounted(() => {
        //     console.log(1)
        //
        // })
        onUnmounted(() => {
            if (chart) {
                chart.dispose();
            }
        })
        return {
            initChart,
            changeChart,
            charts,
            activeIndex,
            chart,
            getYearData,
            loading
        }
    }
})
</script>

<style scoped lang="less">
#chart {
    width: 100%;
    height: 301px;
}
.select-dot {
    text-align: right;
    i {
        display: inline-block;
        width: 12px;
        height: 12px;
        background: #ddd;
        border-radius: 50%;
        margin-right: 5px;

    }
    .active {
        background: #aaa;
    }
}
</style>
