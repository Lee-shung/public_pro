<template>
    <a-popconfirm :title="title" @confirm="onDelClk">
        <a-button>删除</a-button>
    </a-popconfirm>
</template>

<script>
export default {
    name: "DeleteBtn",
    props: {
        title: {
            type: String,
            default: '确定要删除该数据吗？'
        }
    },
    methods: {
        onDelClk() {
            this.$emit('ok');
        }
    }
}
</script>

<style scoped lang="less">

</style>