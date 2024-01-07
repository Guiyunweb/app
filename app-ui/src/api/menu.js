import {request} from "@/api/_service";

export function GET_MENU_TREE() {
	// 接口请求
	return request({
		url: '/menu/getMenuTree',
		method: 'get'
	})
}
