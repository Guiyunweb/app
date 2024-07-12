package plus.guiyun.app.api;

import plus.guiyun.app.api.vo.RouteVO;
import plus.guiyun.app.domain.MenuDO;
import plus.guiyun.app.framework.web.service.CurdService;

import java.util.List;

public interface MenuService extends CurdService<MenuDO, Long> {
    RouteVO getMenuTree();

    List<MenuDO> getParentMenu();

    public RouteVO updateUserMenu(Long userId);

    public RouteVO getUserMenu(Long userId);
}
