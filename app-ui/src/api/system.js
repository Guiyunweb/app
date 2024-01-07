import {request} from '@/api/_service.js'

/**
 * @description 登录
 * @param {Object} data 登录携带的信息
 */
export function SYS_USER_LOGIN(data = {}) {
	// 接口请求
	return request({
		url: '/auth/login',
		method: 'post',
		data
	})
}


