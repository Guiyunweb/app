import util from "@/libs/util";

export const vueRouters = function () {
    let apiMenu = util.data.get("menu")
    if (apiMenu) {
        return flatAsyncRoutes(apiMenu)
    } else {
        return []
    }
}

/**
 * 路由扁平化
 */
function flatAsyncRoutes(menus) {
    let route = []
    for (const menu of menus) {
        if (menu.component) {
            route.push({
                path: menu.path,
                name: menu.name,
                meta: menu.meta,
                component: loadComponent(menu.component),
            })
        } else {
            route.push({
                path: menu.path,
                name: menu.name,
                meta: menu.meta,
                children: flatAsyncRoutes(menu.children)
            })
        }
    }
    return route
}

function loadComponent(component) {
    let view = `../views/${component}.vue`
    return () => import(/* @vite-ignore */view)
}
