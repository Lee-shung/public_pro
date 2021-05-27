<template>
    <div style="margin-top: 10px">
        <a-pagination
            v-model:current="pageModel.current"
            v-model:pageSize="pageModel.size"
            :total="pageModel.total"
            :show-total="(total) => `共 ${total} 条`"
            :page-size-options="pageSizeOptions"
            show-size-changer
            @showSizeChange="onShowSizeChange"
            @change="onChangePage"
        >
            <template #buildOptionText="props">
                <span>{{ props.value }}条/页</span>
            </template>
        </a-pagination>
    </div>
</template>

<script>
import {reactive, ref, getCurrentInstance, watch} from 'vue';

export default {
    name: "Page",
    props: {
        searchParams: Object,
        requestApi: Function,
        tableData: {
            type: Array,
            default: () => []
        },
        tableLoading: Boolean
    },
    setup() {
        const app = getCurrentInstance();
        const global = app.appContext.config.globalProperties;
        const pageModel = reactive({
            current: 1,
            size: 10,
            total: 0
        });
        const pageSizeOptions = ref(['5', '10', '50', '100']);

        //获取列表数据
        const getData = () => {
            if (!app.proxy.requestApi) return;
            const params = global.$commonUtils.deepCopy(app.proxy.searchParams);
            delete params.searchClk;
            for (const key in params) {
                if (params[key] && (typeof params[key] == "string")) {
                    params[key] = params[key].trim();
                }
            }
            params.current = pageModel.current;
            params.size = pageModel.size;
            app.emit('update:tableLoading', true)
            app.proxy.requestApi(params).then(res => {
                if (res && res.records) {
                    pageModel.current = res.current;
                    pageModel.size = res.size;
                    pageModel.total = res.total;
                    app.emit('update:tableData', res.records)
                    app.emit('update:tableLoading', false)
                }
            })
        }
        //获取首页数据
        const getFirstData = () => {
            pageModel.current = 1;
            getData();
        }
        //监听状态值
        watch(
            () => app.proxy.searchParams,
            (val, old) => {
                if (val.searchClk > old.searchClk) {
                    getFirstData();
                } else {
                    getData();
                }
            },
            {deep: true}
        )
        //改变每页数量
        const onShowSizeChange = (current, pageSize) => {
            pageModel.size = pageSize;
            getFirstData();
            // pageSizeRef.value = pageSize;
        };
        //改变页码
        const onChangePage = (page, pageSize) => {
            pageModel.current = page;
            getData();
        }
        return {
            pageModel,
            pageSizeOptions,
            onShowSizeChange,
            getData,
            onChangePage,
            getFirstData
        }
    }
}
</script>

<style scoped lang="less">

</style>