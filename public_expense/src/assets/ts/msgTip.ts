/**
 * create by 李无敌
 * date: 2021年03月10日 16:07
 */
import { message } from 'ant-design-vue'
const duration = 1;
const msgTip = {
    success: function (msg: string) {
        message.success(msg, duration);
    },
    warning: function (msg: string) {
        message.warning(msg, duration);
    },
    info: function (msg: string) {
        message.info(msg, duration);
    },
    error: function (msg: string) {
        message.error(msg, duration);
    }
}
export default msgTip;