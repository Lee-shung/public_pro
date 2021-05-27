/*
 * @Author: 李无敌
 * @Date: 2021-02-04 11:31:57
 * @LastEditors: 李无敌
 * @LastEditTime: 2021-02-04 16:16:19
 * @FilePath: \myworkpace\public_expense\src\assets\ts\storage.ts
 
 */
 

export function getStore(key: string) {
    return localStorage.getItem(key);
}
export function setStore(key: string, data: any) {
    localStorage.setItem(key, data);
}
export function clearStore(keys: Array<string>) {
    for (let i = 0, len = keys.length; i < len; i++) {
        setStore(keys[i], null);
    }
}

