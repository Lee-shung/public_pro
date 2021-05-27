<template>
    <div class="today-dream">
        <div style="text-align: right; margin-bottom: 10px" >
            <a-button @click="getMyDream">追梦</a-button>
        </div>
        <div class="line" v-for="(dreams,index) in myDream" :key="index">
            <div
                class="number-box"
                v-for="(item,i) in dreams.dream"
                :key="i"
                :style="{ border: '1px dashed ' + (i === 6 ? '#2c90f4' : '#f42c2c'), color: i === 6 ? '#2c90f4' : '#f42c2c' }"
            >
                {{ item }}
            </div>
        </div>
    </div>
</template>

<script>
import {defineComponent, reactive, ref, getCurrentInstance} from 'vue';

export default defineComponent({
    name: "TodayDream",
    setup() {
        const app = getCurrentInstance();
        const myDream = reactive([
            {
                dream: new Array(7)
            },
            {
                dream: new Array(7)
            }
        ])
        //生成双色球
        const getMyDream = () => {
            myDream.forEach(item => {
                item.dream = [];
                //红球池
                const redNumber = [];
                for (let i = 1; i <= 33; i++) {
                    redNumber.push(app.appContext.config.globalProperties.$commonUtils.dbValue(i));
                }
                //篮球池
                const blueNumber = [];
                for (let i = 1; i <= 16; i++) {
                    blueNumber.push(app.appContext.config.globalProperties.$commonUtils.dbValue(i));
                }
                for (let i = 0; i <= 5; i++) {
                    const index = Math.floor(Math.random()*redNumber.length);
                    item.dream[i] = redNumber[index];
                    if (item.dream[i]) redNumber.splice(index, 1);
                }
                //升序
                item.dream.sort((a,b) => {
                    return a - b;
                });
                item.dream[6] = blueNumber[Math.floor(Math.random()*16)];
            });
        }
        getMyDream();

        return {
            getMyDream,
            myDream
        }
    }
})
</script>

<style scoped lang="less">
.today-dream {
    .line {
        display: flex;
        justify-content: space-between;
        margin-top: 10px;
        .number-box {
            width: 50px;
            height: 50px;
            line-height: 50px;
            text-align: center;
            font-size: 18px;
            font-weight: bold;
        }
    }
}
</style>
