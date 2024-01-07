import store from '@/store'
import {nextTick} from 'vue'

export function beforeEach(to, from){
	let adminMain = document.querySelector('#adminui-main')
	if(!adminMain){return false}
	store.commit("updateViewTags", {
		fullPath: from.fullPath,
		scrollTop: adminMain.scrollTop
	})
}

export function afterEach(to){
	let adminMain = document.querySelector('#adminui-main')
	if(!adminMain){return false}
	nextTick(()=>{
		let beforeRoute = store.state.viewTags.viewTags.filter(v => v.fullPath == to.fullPath)[0]
		if(beforeRoute){
			adminMain.scrollTop = beforeRoute.scrollTop || 0
		}
	})
}
