package plus.guiyun.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import plus.guiyun.app.api.MenuService;
import plus.guiyun.app.api.vo.MenuTree;
import plus.guiyun.app.api.vo.MetaVO;
import plus.guiyun.app.api.vo.RouteVO;
import plus.guiyun.app.common.code.redis.RedisCache;
import plus.guiyun.app.common.constant.Constants;
import plus.guiyun.app.domain.MenuDO;
import plus.guiyun.app.framework.config.TokenConfig;
import plus.guiyun.app.framework.web.service.CurdServiceImpl;
import plus.guiyun.app.repository.MenuRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class MenuServiceImpl extends CurdServiceImpl<MenuRepository, MenuDO, Long> implements MenuService {

    @Autowired
    RedisCache redisCache;

    public RouteVO getMenuTree() {
        Sort sort = Sort.by("sortBy");
        List<MenuDO> list = repository.findAll(sort);
        List<MenuTree> rootList = getRootMenuList(list); // 获取根菜单列表
        if (!ObjectUtils.isEmpty(rootList)) {
            for (MenuTree rootMenu : rootList) {
                buildMenuTree(rootMenu, list); // 递归构建菜单树
            }
        }

        RouteVO route = new RouteVO();
        route.setHome("dashboard_workbench");
        route.setRoutes(rootList);
        return route;
    }

    @Override
    public List<MenuDO> getParentMenu() {
        return repository.findByParentIdIsNotNull();
    }

    @Override
    public RouteVO updateUserMenu(Long userId) {
        RouteVO route = getMenuTree();
        redisCache.setCacheObject(Constants.ROUTER_USER + userId, route, TokenConfig.expireTime, TimeUnit.MINUTES);
        return route;
    }

    @Override
    public RouteVO getUserMenu(Long userId) {
        return redisCache.getCacheObject(Constants.ROUTER_USER + userId);
    }

    /**
     * 构建菜单树
     *
     * @param parentMenu 父菜单
     */
    private void buildMenuTree(MenuTree parentMenu, List<MenuDO> list) {
        List<MenuTree> childList = getChildMenuList(parentMenu.getId(), list); // 获取子菜单列表
        if (childList != null && !childList.isEmpty()) {
            for (MenuTree childMenu : childList) {
                buildMenuTree(childMenu, list); // 递归构建菜单树
            }
            parentMenu.setComponent("basic");
            parentMenu.setChildren(childList); // 设置父菜单的子菜单
        }
    }

    /**
     * 根据父菜单ID获取子菜单列表
     *
     * @param parentId 父菜单ID
     * @return 子菜单列表
     */
    private List<MenuTree> getChildMenuList(Long parentId, List<MenuDO> list) {
        List<MenuTree> trees = new ArrayList<>();
        for (MenuDO menu : list) {
            if (Objects.equals(menu.getParentId(), parentId)) {
                MenuTree tree = menuToMenutree(menu);
                trees.add(tree);
            }
        }
        return trees;
    }

    /**
     * 获取根菜单列表
     *
     * @return 根菜单列表
     */
    private List<MenuTree> getRootMenuList(List<MenuDO> list) {
        List<MenuTree> trees = new ArrayList<>();
        for (MenuDO menu : list) {
            if (ObjectUtils.isEmpty(menu.getParentId())) {
                MenuTree tree = menuToMenutree(menu);
                trees.add(tree);
            }
        }
        return trees;
    }

    MenuTree menuToMenutree(MenuDO menu) {
        MenuTree tree = new MenuTree();
        MetaVO meta = new MetaVO();

        String route = menu.getRoute();
        String name = menu.getRoute().replaceFirst("/", "").replace("/", "_");

        // 菜单基础
        tree.setId(menu.getId());
        tree.setComponent(menu.getComponent());
        tree.setPath(route);
        tree.setName(name);

        meta.setTitle(menu.getTitle());
        meta.setRequiresAuth(menu.isRequiresAuth());
        meta.setIcon(menu.getIcon());
        meta.setOrder(menu.getSortBy());


        tree.setMeta(meta);
        return tree;
    }

}

