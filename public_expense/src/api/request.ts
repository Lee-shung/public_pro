
import axios from 'axios';
import QS from 'qs';
const SUCCESS_CODE = 200;
const FAIL_CODE = 0;
import msgTip from './../assets/ts/msgTip';
function result(response: any, resolve: any, reject: any) {
    
    switch (response.code) {
        case SUCCESS_CODE:
            resolve(response.data)
            break;
        case FAIL_CODE:
            msgTip.error(response.msg);
            resolve(response.data)
            break;
        default:
            reject(response);
            break;
    }
}
export function get(url: string, params: any) {
    return new Promise<any>((resolve,reject) => {
        axios.get(url, { params: params}).then(res => {

            result(res, resolve, reject);
        }).catch(err => {
            reject(err);
        })
    })
}

export function post(url: string, params: any) {
    return new Promise<any>((resolve, reject) => {
        axios.post(url, QS.stringify(params), {
            headers: {
                'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8'
            }
        }).then(res => {
            result(res, resolve, reject);
        }).catch(err => {
            reject(err);
        })
    })
}

export function put(url: string, params: any) {
    return new Promise<any>((resolve, reject) => {
        axios.put(url, JSON.stringify(params), {
            headers: {
                'Content-type': 'application/json; charset=UTF-8'
            }
        }).then(res => {
            
            result(res, resolve, reject);
        }).catch(err => {
            reject(err)
        })
    })
}

export function del(url: string, params: any) {
    return new Promise<any>((resolve, reject) => {
        axios.delete(url, { params: params }).then(res => {
            result(res, resolve, reject);
        }).catch(err => {
            reject(err);
        })
    })
}

export function send(url: string, params: any, method: any, type: string) {
    return new Promise<any>((resolve, reject) => {
        if (method === 'post' || method == 'put') {
            let data: any, contentType: string;
            switch (type) {
                case 'json':
                    contentType = 'application/json; charset=UTF-8';
                    data = params;
                    break;
                case 'file':
                    contentType = 'multipart/form-data';
                    data = params
                    break;

                default:
                    contentType = 'application/x-www-form-urlencoded; charset=UTF-8'
                    data = QS.stringify(params);
                    break;
            }
            axios({
                url: url,
                method: method,
                data: data,
                headers: {
                    'Content-type': contentType
                }
            }).then(res => {
                result(res, resolve, reject);
            }).catch(err => {
                reject(err);
            })
        } else {
            axios({
                method: method,
                url: url,
                data: params
            }).then(res => {
                result(res, resolve, reject);
            }).catch(err => {
                reject(err);
            })
        }
    })
}