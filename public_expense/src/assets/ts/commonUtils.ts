/**
 * create by 李无敌
 * date: 2021年03月09日 15:18
 */
const commonUtils = {
    dateFormat: function (params: any, type: string) {
        const date = new Date(parseFloat(params));
        const year = date.getFullYear();
        const month = commonUtils.dbValue(date.getMonth() + 1);
        const day = commonUtils.dbValue(date.getDate());
        const hour = commonUtils.dbValue(date.getHours());
        const min = commonUtils.dbValue(date.getMinutes());
        const secend = commonUtils.dbValue(date.getSeconds());
        let resultDate = '';
        switch (type) {
            case 'date':
                resultDate = `${year}/${month}/${day}`
                break
            case 'time':
                resultDate = `${hour}:${min}`
                break

            default:
                resultDate = `${year}/${month}/${day} ${hour}:${min}:${secend}`
        }
        return resultDate;
    },
    dbValue: function (val: any) {
        val = val < 10 ? '0'+val : val + '';
        return val;
    },
    toMoney: function (num: number) {
        return parseFloat(num.toFixed(2)).toLocaleString('zh-Hans', { style: 'currency', currency: 'CNY' });
    },
    deepCopy: function (obj: any) {
        return JSON.parse(JSON.stringify(obj));
    }

}
export default commonUtils;