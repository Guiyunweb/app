package plus.guiyun.app.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import plus.guiyun.app.api.MenuService;
import plus.guiyun.app.api.vo.MenuTree;
import plus.guiyun.app.domain.MenuDO;
import plus.guiyun.app.framework.web.service.CurdServiceImpl;
import plus.guiyun.app.repository.MenuRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MenuServiceImpl extends CurdServiceImpl<MenuRepository, MenuDO, Long> implements MenuService {

    @Autowired
    private MenuRepository repository;

    public List<MenuTree> getMenuTree() {
        List<MenuDO> list = repository.findAll();
        List<MenuTree> rootList = getRootMenuList(list); // 获取根菜单列表
        if (rootList != null) {
            for (MenuTree rootMenu : rootList) {
                buildMenuTree(rootMenu, list); // 递归构建菜单树
            }
        }
        return rootList;
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
                MenuTree tree = new MenuTree();
                BeanUtils.copyProperties(menu, tree);
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
                MenuTree tree = new MenuTree();
                BeanUtils.copyProperties(menu, tree);
                trees.add(tree);
            }
        }
        return trees;
    }
}

