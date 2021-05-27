<!--
 * @Author: 李无敌
 * @Date: 2021-02-22 10:46:43
 * @LastEditors: 李无敌
 * @LastEditTime: 2021-02-23 16:48:21
 * @FilePath: \myworkpace\public_expense\src\views\daily\IncomeType.vue
-->
<template>
  <div class="animate__animated animate__fadeIn">
      <div class="table-head"></div>
      <div class="table-body">
          <a-table :pagination="false" :dataSource="state.dataSource" :columns="state.column" bordered rowKey="id">
              <template #action="{ record }">
                  <div style="display: flex">
                      <a-button style="margin-right: 5px">编辑</a-button>
                      <del-button title="确定要删除该分类吗？" @ok="onDelBtn(record)"></del-button>
                  </div>
              </template>
          </a-table>
      </div>

  </div>
</template>

<script>
import { reactive } from 'vue';
import { apiGetAllIncomeType } from '@/api/api';
export default {
    setup(props) {
        console.log(props)
        const state = reactive({
            column: [
                {
                    title: '#',
                    key: 'index',
                    dataIndex: 'index',
                    width: 60,
                    customRender: ({text, record, index}) => {
                        return {
                            children: index+1
                        }
                    },
                    align: 'center'
                },
                {
                    title: '类型名称',
                    key: 'incomeType',
                    dataIndex: 'incomeType',
                    align: 'center'

                },
                {
                    title: '类型key',
                    key: 'incomeTypeKey',
                    dataIndex: 'incomeTypeKey',

                },
                {
                    title: '修改时间',
                    key: 'updateTime',
                    dataIndex: 'updateTime',
                    align: 'center',
                    width: 180
                },
                {
                    title: '备注',
                    key: 'remark',
                    dataIndex: 'remark',
                    align: 'left'
                
                },
                {
                    title: '操作',
                    key: 'action',
                    dataIndex: 'action',
                    slots: { customRender: 'action' },
                    width: 150,
                    align: 'center'

                },
            ],
            dataSource: [],

        });
        apiGetAllIncomeType().then(res => {
            state.dataSource = res || [];
        });
        const onDelBtn = ({ id }) => {
            console.log(id)
        }
        return {
            state,
            onDelBtn
        }
    }
}
</script>

<style>

</style>