package plus.guiyun.app.api;

import plus.guiyun.app.domain.MenuDO;
import plus.guiyun.app.framework.web.service.CurdService;

import java.util.List;

public interface MenuService extends CurdService<MenuDO, Long> {
    List getMenuTree();

    List<MenuDO> getParentMenu();
}
