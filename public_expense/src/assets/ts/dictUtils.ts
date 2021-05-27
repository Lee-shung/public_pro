/**
 * create by 李无敌
 * date: 2021年03月09日 11:17
 */
const dictUtils = {
    arrToObj: function (arr: any) {
        const obj: any = {};
        if (arr && arr.length) {
            arr.forEach((item: any) => {
                obj[item.paramKey] = item.paramValue;
            });
        }
        return obj;
    }
}
export default dictUtils;