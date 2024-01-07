import axios from 'axios'
import Adapter from 'axios-mock-adapter'
import {get, isEmpty, merge} from 'lodash'
import qs from 'qs'
import tool from '@/utils/tool';
import {ElMessage} from 'element-plus'

/**
 * @description 记录和显示错误
 * @param {Error} error 错误对象
 */
function handleError(error) {
	// 打印到控制台
	if (process.env.NODE_ENV === 'development') {
		console.log('>>>>>> Error >>>>>>')
		console.log(error)
	}
}

/**
 * @description 创建请求实例
 */
function createService() {
	// 创建一个 axios 实例
	const service = axios.create()
	// 请求拦截
	service.interceptors.request.use(
		config => config,
		error => {
			// 发送失败
			console.log(error)
			return Promise.reject(error)
		}
	)
	// 响应拦截
	service.interceptors.response.use(
		response => {
			if (response.data.showType) {
				ElMessage({
					message: response.data.message,
					type: response.data.success ? 'success' : 'error',
					duration: 5 * 1000
				})
			}
			// 判断提示
			if (response.data.success) {
				return response.data
			} else {
				return response.data
			}
		},
		error => {
			const status = get(error, 'response.status')
			switch (status) {
				case 400:
					error.message = '请求错误';
					break
				case 401:
					error.message = '未授权，请登录';
					break
				case 403:
					error.message = '拒绝访问';
					break
				case 404:
					error.message = `请求地址出错: ${error.response.config.url}`;
					break
				case 408:
					error.message = '请求超时';
					break
				case 500:
					error.message = '服务器内部错误';
					break
				case 501:
					error.message = '服务未实现';
					break
				case 502:
					error.message = '网关错误';
					break
				case 503:
					error.message = '服务不可用';
					break
				case 504:
					error.message = '网关超时';
					break
				case 505:
					error.message = 'HTTP版本不受支持';
					break
				default:
					break
			}
			// ElMessage({
			// 	message: error.message,
			// 	type: 'error',
			// 	duration: 5 * 1000
			// })
			handleError(error)
			throw error
		}
	)
	return service
}

function stringify(data) {
	return qs.stringify(data, {allowDots: true, encode: false})
}

/**
 * @description 创建请求方法
 * @param {Object} service axios 实例
 */
function createRequest(service) {
	return function (config) {
		const token = tool.cookie.get('TOKEN')
		const configDefault = {
			headers: {
				'Content-Type': get(config, 'headers.Content-Type', 'application/json')
			},
			timeout: 5000,
			baseURL: process.env.VUE_APP_API,
			data: {}
		}
		if (token){
			configDefault.headers.Authorization = token
		}
		const option = merge(configDefault, config)
		// 处理 get 请求的参数
		// 请根据实际需要修改
		if (!isEmpty(option.params)) {
			option.url = option.url + '?' + stringify(option.params)
			option.params = {}
		}
		// 当需要以 form 形式发送时 处理发送的数据
		// 请根据实际需要修改
		if (!isEmpty(option.data) && option.headers['Content-Type'] === 'application/x-www-form-urlencoded') {
			option.data = stringify(option.data)
		}
		return service(option)
	}
}

// 用于真实网络请求的实例和请求方法
export const service = createService()
export const request = createRequest(service)

// 用于模拟网络请求的实例和请求方法
export const serviceForMock = createService()
export const requestForMock = createRequest(serviceForMock)

// 网络请求数据模拟工具
export const mock = new Adapter(serviceForMock)
