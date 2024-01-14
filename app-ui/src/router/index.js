import {createRouter, createWebHistory} from 'vue-router'
import {routes} from './routes'
import util from "@/libs/util";


const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: routes
})

let isGetRouter = false;

router.beforeEach(async (to, from, next) => {

    let token = util.cookies.get('token')
    // 设置登录
    if (to.path === "/login") {
        next();
        return false;
    }
    // 设置Token
    if (!token) {
        next({
            path: '/login',
        })
        return false;
    }

    next();
})




export default router
