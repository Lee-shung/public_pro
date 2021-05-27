<template>
    <div @click="$emit('on-change')" class="keep-menu-item"  :class="isActive ? 'active-item' : ''">
        <div v-if="menuItem.key !== 'control'">
            <span  class="name">{{ menuItem.name }}</span>
            <CloseCircleFilled @click.stop="$emit('on-close')" class="icon" />
        </div>
        <HomeOutlined v-else />
    </div>
</template>

<script>
import { CloseCircleFilled, HomeOutlined } from '@ant-design/icons-vue';
import { defineComponent, ref, getCurrentInstance } from 'vue';
export default defineComponent({
    name: "KeepMenuItem",
    components: {
        CloseCircleFilled, HomeOutlined
    },
    props: {
        menuItem: {
            type: Object,
            default: function () {
                return {}
            }
        },
        isActive: {
            type: Boolean,
            default: false
        }
    },
    setup() {
        const app = getCurrentInstance();

        return {
        }
    }

})
</script>

<style scoped lang="less">

.keep-menu-item {
    border-left: 1px solid #eeeeee;
    height: 100%;
    padding: 0 14px;
    cursor: pointer;
    position: relative;
    &:after {
        content: '';
        width: 0;
        height: 2px;
        background: rebeccapurple;
        display: block;
        position: absolute;
        bottom: 0;
        left: 0;
        transition: width .2s;

    }
    &:hover {
        background: #eaeaea;
        &:after {
            width: 100%;
            transition: width .2s;
        }
    }
    .name {
        display: inline-block;
        height: 100%;
    }
    .icon {
        margin-left: 10px;
        &:hover {
            color: #fe2020;
        }
    }

}
.active-item {
    background: #eaeaea;
    &:after {
        content: '';
        width: 100%;
        height: 2px;
        background: rebeccapurple;
        display: block;
        position: absolute;
        bottom: 0;
        left: 0;
        transition: width .2s;
    }
}
</style>