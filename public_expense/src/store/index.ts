import {createStore} from 'vuex'
import { toRaw } from 'vue'
export default createStore({
    state: {
        keepMenuList: [],
        openKeys: [],
        selectedKeys: [],
        isActive: 0
    },
    mutations: {
        SET_DATA(state: any, { name, value }) {
            state[name] = value;
        },
        SET_KEEP_MENULIST(state, payload) {
            state.keepMenuList.push(payload);
        },
        DEL_KEEP_MENU(state, index) {
            state.keepMenuList.splice(index,1);
        }
    },
    actions: {
        //头部缓存菜单
        setKeepMenuList({ commit, state }, keepMenu) {
            commit('SET_KEEP_MENULIST', keepMenu);
        },
        //当前打开的侧边菜单
        setOpenKeys({ commit }, openKeys) {
            commit('SET_DATA', { name: 'openKeys', value: openKeys })
        },
        //当前打开的头部菜单项索引
        setIsActive({ commit }, isActive) {
            commit('SET_DATA', { name: 'isActive', value: isActive });
        },
        //当前选中的侧边栏菜单项
        setSelectKey({ commit }, selectedKeys) {
            commit('SET_DATA', { name: 'selectedKeys', value: selectedKeys });
        }
    },
    modules: {}
})
