import {vueRouters} from "@/router/global";
import config from "@/config";

export const routes = [
    {
        path: '',
        redirect: '/dashboard',
    },
    {
        path: '',
        name: "layout",
        component: () => import('../layout/default.vue'),
        redirect: config.DASHBOARD_URL || '/dashboard',
        children: [...vueRouters()]
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('../views/login/index.vue')
    },
    {
        path: '/:pathMatch(.*)',
        redirect: '/404',
    },
    {
        path: '/404',
        component: () => import('../views/error/404.vue')
    },
]
