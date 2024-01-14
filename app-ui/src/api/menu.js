import {request} from "@/api/_service";

export function GET_MENU_TREE() {
    // 接口请求
    return request({
        url: '/menu/getMenuTree',
        method: 'get'
    })
}

/**
 * 查询全部
 * @returns {*}
 * @constructor
 */
export function GET_MENU_ALL() {
    // 接口请求
    return request({
        url: '/menu/all',
        method: 'get'
    })
}

export function GET_MENU_PARENT() {
    // 接口请求
    return request({
        url: '/menu/getParentMenu',
        method: 'get'
    })
}

/****** 基础请求 *******/

export function ADD(data = {}) {
    return request({
        url: '/menu',
        method: 'post',
        data
    })
}

export function GET_INFO(id) {
    return request({
        url: '/menu/' + id,
        method: 'get'
    })
}

export function GET_LIST(params = {}) {
    return request({
        url: '/menu/list',
        method: 'get',
        params
    })
}

export function DEL(id) {
    return request({
        url: '/menu/' + id,
        method: 'delete'
    })
}

export function UPDATE_ID(id, data = {}) {
    return request({
        url: '/menu/' + id,
        method: 'put',
        data
    })
}
