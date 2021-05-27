<template>
    <div class="pe-header flex-center">
        <div class="left-icon">
            <menu-unfold-outlined class="trigger margin-right-36"/>
            <ReloadOutlined class="trigger"/>
        </div>
        <div class="right-icon">
            <my-icon type="icon-weiduxiaoxi" class="trigger margin-right-36"/>

            <div class="note">
                <my-icon type="icon-biaoqian" class="trigger margin-right-36" @click="isShowNote = true"/>
                <transition
                    name="custom-classes-transition"
                    enter-active-class="animate__animated animate__fadeIn"
                    leave-active-class="animate__animated animate__bounceOut"
                >
                    <div class="note-box" v-if="isShowNote">
                        <div class="title flex-center">
                            <span>便签</span>
                            <CloseOutlined style="cursor: pointer" @click="isShowNote = false"/>
                        </div>
                        <a-textarea placeholder="记录你的点滴。" @change="changeValue" v-model:value="noteValue"
                                    :aotuSize="true"/>
                    </div>
                </transition>

            </div>
            <div class="account-info">
                <a-dropdown v-if="accountInfo" placement="bottomRight">
                    <a class="a-link" @click="e => e.preventDefault()"> {{ accountInfo.name }}
                        <CaretDownFilled/>
                    </a>
                    <template #overlay>
                        <a-menu>
                            <a-menu-item>
                                <a href="javascript:;">基本资料</a>
                            </a-menu-item>
                            <a-menu-item>
                                <a href="javascript:;">安全设置</a>
                            </a-menu-item>
                            <a-menu-item>
                                <a href="javascript:;" @click="onLoginOut">退出</a>
                            </a-menu-item>
                        </a-menu>
                    </template>
                </a-dropdown>
                <a v-else @click="$router.push('/login')">登录</a>
            </div>
        </div>
    </div>
</template>

<script>

import { ref, reactive, watch, getCurrentInstance } from 'vue';
import {ReloadOutlined, MenuUnfoldOutlined, CaretDownFilled, CloseOutlined} from '@ant-design/icons-vue';
import {createFromIconfontCN} from '@ant-design/icons-vue';
import {getStore, setStore, clearStore} from '@/assets/ts/storage';

//自定义icon
const MyIcon = createFromIconfontCN({
    scriptUrl: '//at.alicdn.com/t/font_2229807_r7ue280u5m.js', // 在 iconfont.cn 上生成
});
export default {
    name: "Head",
    components: {ReloadOutlined, MenuUnfoldOutlined, MyIcon, CaretDownFilled, CloseOutlined},
    setup() {
        const app = getCurrentInstance();
        const noteValue = ref('');
        const isShowNote = ref(false);
        const accountInfo = reactive(JSON.parse(getStore('accountInfo')));
        watch(isShowNote, (newVal, oldVal) => {
            if (newVal) {
                noteValue.value = getStore('myNote') || '';
            }
        });
        //退出登录
        const onLoginOut = () => {
            clearStore(['accessToken', 'accountInfo']);
            app.proxy.$router.push('/login');
        }
        return {
            noteValue,
            isShowNote,
            accountInfo,
            onLoginOut,
        }
    },
    methods: {
        changeValue(e) {
            setStore('myNote', e.target.value);
        }
    },
}
</script>

<style scoped lang="less">
.pe-header {
    font-size: 18px;
    line-height: 60px;
    padding: 0 24px;

    .margin-right-36 {
        margin-right: 36px;
    }

    .trigger {
        cursor: pointer;
        transition: all 0.3s;

        &:hover {
            transform: scale(1.2);
        }
    }

    .right-icon {
        display: flex;
        align-items: center;

        .note {
            position: relative;

            .note-box {
                line-height: 1;

                position: absolute;
                top: 50px;
                right: 0;
                z-index: 999;
                box-shadow: 1px 1px 50px rgba(0, 0, 0, .3);

                .title {
                    background: #344058;
                    color: #ffffff;
                    height: 48px;
                    padding: 0 16px;
                    font-size: 14px;
                }

                textarea {
                    padding: 10px 20px;
                    font-size: 14px;
                    line-height: 1.4;
                    min-width: 300px;
                    min-height: 132px;
                    border: none;
                    outline: none;

                    &:focus {
                        outline: none;
                        border: none;
                        box-shadow: none;
                    }
                }
            }
        }
    }
}

.a-link {
    color: #333;
    font-size: 14px;
}
</style>