<!--
 * @Author: 李无敌
 * @Date: 2021-02-03 16:33:22
 * @LastEditors: 李无敌
 * @LastEditTime: 2021-03-17 10:56:04
 * @FilePath: \myworkpace\public_expense\src\views\daily\Income.vue
 * @desc: 收支管理
-->
<template>
    <div class="animate__animated animate__fadeIn">
        <div class="table-head">
            <a-form layout="inline">
                <a-form-item label="收入类型">
                    <a-select
                        v-model:value="searchParams.incomeType"
                        allowClear
                        placeholder="请选择收入类型"
                        style="width: 140px"
                    >
                        <a-select-option v-for="(item,index) in dictType.incomeTypeList" :key="index" :value="item.incomeTypeKey">{{ item.incomeType }}</a-select-option>
                    </a-select>
                </a-form-item>
                <a-form-item label="交易方式">
                    <a-select
                        v-model:value="searchParams.incomeWay"
                        allowClear
                        placeholder="请选择交易方式"
                        style="width: 140px"
                    >
                        <a-select-option v-for="(item,index) in dictType.dealWayList" :key="index" :value="item.paramKey">{{ item.paramValue }}</a-select-option>
                    </a-select>
                </a-form-item>
                <a-form-item label="金额区间">
                    <a-input-number
                        :min="0"
                        v-model:value="searchParams.smallMoney"
                        :formatter="value => `￥ ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')"
                        :parser="value => value.replace(/\￥\s?|(,*)/g, '')"
                        style="width: 120px"
                    ></a-input-number>
                    -
                    <a-input-number
                        :min="0"
                        :formatter="value => `￥ ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')"
                        :parser="value => value.replace(/\￥\s?|(,*)/g, '')"
                        v-model:value="searchParams.bigMoney"
                        style="width: 120px"
                    ></a-input-number>

                </a-form-item>
                <a-form-item label="到账时间">
                    <a-date-picker
                        :locale="locale"
                        placeholder="请选择开始日期"
                        :show-time="{ defaultValue: moment('00:00:00', 'HH:mm:ss') }"
                        v-model:value="startDate"
                        @change="(data) => {this.changeDate(data,'startDate')}"
                    ></a-date-picker>
                    -
                    <a-date-picker
                        :locale="locale"
                        placeholder="请选择截止日期"
                        :show-time="{ defaultValue: moment('00:00:00', 'HH:mm:ss') }"
                        v-model:value="endDate"
                        @change="(data) => {this.changeDate(data,'endDate')}"
                    ></a-date-picker>

                </a-form-item>
                <a-form-item>
                    <a-button type="default" size="large" @click="addIncome">
                        <template #icon>
                            <PlusOutlined/>
                        </template>
                        新增
                    </a-button>
                </a-form-item>
            </a-form>
        </div>
        <div class="table-body">
            <a-table
                :dataSource="state.dataSource"
                :columns="state.column"
                bordered
                :rowKey="(r, i) => i"
                :pagination="false"
                :loading="tableLoading"
                size="middle"
            >
                <template #accountDate="{ text }">
                    <span>{{ $commonUtils.dateFormat(text) }}</span>
                </template>
                <template #money="{ text }">
                    <span>{{ $commonUtils.toMoney(text) }}</span>
                </template>

                <template #action="{ record }">
                    <div style="display: flex; justify-content: center">
                        <a-button style="margin-right: 5px" @click="onEditClk(record)"
                        >编辑
                        </a-button
                        >
                        <del-button title="确定要删除该条记录吗？" @ok="onDelClk(record)"></del-button>
                    </div>
                </template>
            </a-table>
            <Page
                :searchParams="searchParams"
                :requestApi="requestApi"
                v-model:tableData="state.dataSource"
                v-model:tableLoading="tableLoading"
            ></Page>
        </div>
        <a-modal
            v-model:visible="dialog.isVisible"
            :title="dialog.title"
            @ok="handleOk"
            @cancel="onClose"
            okText="保存"
            cancelText="取消"
        >
            <a-form class="table-form" ref="dialogForm" :rules="rules" :model="dialog.data" :labelCol="{ span: 5 }">
                <a-form-item label="收入类型" name="incomeType">
                    <a-select
                        v-model:value="dialog.data.incomeType"
                        placeholder="请选择"
                        style="width: 200px"
                        allowClear
                    >
                        <a-select-option
                            :value="item.incomeTypeKey"
                            v-for="(item, index) in dictType.incomeTypeList"
                            :key="index"
                        >{{ item.incomeType }}
                        </a-select-option
                        >
                    </a-select>
                </a-form-item>
                <a-form-item label="收入方式" name="incomeWay">
                    <a-select
                        v-model:value="dialog.data.incomeWay"
                        placeholder="请选择"
                        style="width: 200px"
                        allowClear
                    >
                        <a-select-option
                            :value="item.paramKey"
                            v-for="(item, index) in dictType.dealWayList"
                            :key="index"
                        >{{ item.paramValue }}
                        </a-select-option
                        >
                    </a-select>
                </a-form-item>

                <a-form-item label="入账时间" name="accountDate">
                    <a-date-picker
                        :locale="locale"
                        format="YYYY/MM/DD hh:mm:ss"
                        v-model:value="dialog.data.accountDate"
                        :show-time="{ defaultValue: moment('00:00:00', 'HH:mm:ss') }"
                        style="width: 200px;"
                        placeholder="到账时间" />
                </a-form-item>
                <a-form-item label="金额" name="money">
                    <a-input-number
                        :min="0"
                        v-model:value="dialog.data.money"
                        :formatter="value => `￥ ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')"
                        :parser="value => value.replace(/\￥\s?|(,*)/g, '')"
                        style="width: 120px;"
                    />
                </a-form-item>
                <a-form-item label="备注">
                    <a-textarea auto-size style="width: 300px;" allowClear v-model:value="dialog.data.remark"/>
                </a-form-item>
            </a-form>
        </a-modal>
    </div>
</template>

<script>
import {reactive, getCurrentInstance, ref, toRaw, onMounted} from "vue";
import {
    apiFindIncomePage,
    apiAddIncome,
    apiFindAllDealWay,
    apiGetAllIncomeType,
    apiUpdateIncome,
    apiDelIncome
} from "@/api/api";
import {PlusOutlined} from "@ant-design/icons-vue";
import moment from 'moment';
import 'moment/dist/locale/zh-cn';
// import { message } from 'ant-design-vue'
import locale from 'ant-design-vue/es/date-picker/locale/zh_CN';
export default {
    components: {
        PlusOutlined,
    },
    setup() {
        const app = getCurrentInstance();
        const global = app.appContext.config.globalProperties;
        const dialogForm = ref();
        const searchParams = reactive({
            searchClk: 0,
            incomeType: undefined,
            incomeWay: undefined,
            smallMoney: 0,
            bigMoney: null,
            endDate: null,
            startDate: null
        });
        const startDate = ref(null);
        const endDate = ref(null);
        const tableLoading = ref(false);
        const requestApi = ref(apiFindIncomePage);
        const dictType = reactive({
            dealWayList: [],
            dealWayObj: {},
            incomeTypeList: [],
            incomeTypeObj: [],
        });
        //查询交易方式
        apiFindAllDealWay().then((res) => {

            dictType.dealWayList = res || [];
            dictType.dealWayObj = global.$dictUtils.arrToObj(res);
        });
        //查询收入类型
        apiGetAllIncomeType().then((res) => {
            dictType.incomeTypeList = res || [];
            const incomeTypeObj = {};
            res.forEach(item => {
                incomeTypeObj[item.incomeTypeKey] = item.incomeType;
            });
            dictType.incomeTypeObj = incomeTypeObj;
        });
        const state = reactive({
            column: [
                {
                    title: "#",
                    key: "index",
                    dataIndex: "index",
                    width: 60,
                    customRender: ({index}) => {
                        return {
                            children: index + 1,
                        };
                    },
                    align: "center",
                },

                {
                    title: "收入类型",
                    key: "incomeType",
                    dataIndex: "incomeType",
                    align: "center",
                    width: 180,
                    customRender: ({text}) => {
                        return {
                            children: dictType.incomeTypeObj[text],
                        };
                    },
                },
                {
                    title: "金额",
                    key: "money",
                    dataIndex: "money",
                    width: 160,
                    align: "right",
                    slots: {customRender: "money"},
                },
                {
                    title: "入款方式",
                    key: "incomeWay",
                    dataIndex: "incomeWay",
                    align: "center",
                    width: 200,
                    customRender: ({text}) => {
                        return {
                            children: dictType.dealWayObj[text],
                        };
                    },
                },
                {
                    title: "到账时间",
                    key: "accountDate",
                    dataIndex: "accountDate",
                    align: "center",
                    width: 220,
                    slots: {customRender: "accountDate"},
                },
                {
                    title: "备注",
                    key: "remark",
                    dataIndex: "remark",
                    align: "left",
                },
                {
                    title: "操作",
                    key: "action",
                    dataIndex: "action",
                    slots: {customRender: "action"},
                    width: 180,
                    align: "center",
                },
            ],
            dataSource: [],
        });
        const rules = reactive({
            money: [
                { required: true, type: 'number', message: '请输入金额', trigger: 'change' }
            ],
            accountDate: [
                { required: true, type: 'date', message: '请选择到账时间', trigger: 'change' }
            ],
            incomeType: [
                { required: true, message: '请选择收入类型', trigger: 'change' }
            ],
            incomeWay: [
                { required: true, message: '请选择交易方式', trigger: 'change' }
            ],

        })
        const initDialogData = () => {
            return {
                money: 0,
                accountDate: null,
                incomeType: null,
                incomeWay: null,
                remark: "",
            };
        };
        const dialog = reactive({
            isVisible: false,
            title: "新增账单",
            data: initDialogData(),
        });
        //分页查询收入列表
        const onQueryData = (isCurrent) => {
            isCurrent ? searchParams.searchClk -= 1 : searchParams.searchClk += 1;
        };
        const changeDate = (date, accountDate) => {
            searchParams[accountDate] = date ? date.valueOf() : null;
            onQueryData();
        }
        //新增账单
        const addIncome = () => {
            dialog.title = "新增账单";
            //初始化数据
            dialog.data = initDialogData();
            dialog.isVisible = true;
        };
        //修改账单
        const onEditClk = (record) => {
            dialog.title = "新增账单";
            dialog.data = global.$commonUtils.deepCopy(record);
            dialog.data.accountDate = dialog.data.accountDate && moment(new Date(dialog.data.accountDate), 'YYYY/MM/DD hh:mm:ss');
            dialog.isVisible = true;
        };
        //保存账单
        const handleOk = () => {
            dialogForm.value.validate().then(valid => {
                if (valid) {
                    const data = global.$commonUtils.deepCopy({...dialog.data,accountDate:dialog.data.accountDate.valueOf()});
                    if (data.id) {
                        //修改账单
                        apiUpdateIncome(data).then(res => {
                            if (res) {
                                // message.success('新增成功')
                                global.$msgTip.success("修改成功");
                                onQueryData(true);
                                //清除校验
                                dialogForm.value.resetFields();
                                dialog.isVisible = false;

                            }
                        })
                    } else {
                        apiAddIncome(data).then((res) => {
                            if (res) {
                                // message.success('新增成功')
                                global.$msgTip.success("新增成功");
                                onQueryData();
                                //清除校验
                                dialogForm.value.resetFields();
                                dialog.isVisible = false;

                            }
                        });
                    }
                }
            })

        };
        //关闭弹框
        const onClose = () => {
            //清除校验
            dialogForm.value.resetFields();
        };
        //删除账单
        const onDelClk = ({ id }) => {
            apiDelIncome({ id }).then(res => {
                if (res) {
                    global.$msgTip.success("删除成功");
                    onQueryData(true);
                }
            })
        };
        onMounted(() => {
            onQueryData();
        });
        return {
            state,
            dialog,
            addIncome,
            initDialogData,
            handleOk,
            dictType,
            dialogForm,
            onClose,
            onQueryData,
            searchParams,
            requestApi,
            tableLoading,
            onDelClk,
            onEditClk,
            rules,
            startDate,
            endDate,
            changeDate,
            moment,
            locale
        };
    },
};
</script>

<style>
</style>