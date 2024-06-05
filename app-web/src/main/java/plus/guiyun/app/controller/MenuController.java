package plus.guiyun.app.controller;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.guiyun.app.api.MenuService;
import plus.guiyun.app.api.vo.RouteVO;
import plus.guiyun.app.common.code.domain.AjaxResult;
import plus.guiyun.app.domain.MenuDO;
import plus.guiyun.app.framework.web.controller.CurdController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController extends CurdController<MenuService, MenuDO, Long> {

    @Autowired
    private MenuService service;

    @GetMapping("/getMenuTree")
    public AjaxResult<RouteVO> getMenuTree() {
        return AjaxResult.success(service.getMenuTree());
    }

    @GetMapping("/test")
    public AjaxResult<JSONObject> test() {
        JSONObject param = JSONObject.parse("{\"routes\":[{\"path\":\"/multi-menu\",\"component\":\"basic\",\"children\":[{\"name\":\"multi-menu_first\",\"path\":\"/multi-menu/first\",\"component\":\"multi\",\"children\":[{\"name\":\"multi-menu_first_second\",\"path\":\"/multi-menu/first/second\",\"component\":\"self\",\"meta\":{\"title\":\"二级菜单\",\"requiresAuth\":true,\"icon\":\"mdi:menu\"}},{\"name\":\"multi-menu_first_second-new\",\"path\":\"/multi-menu/first/second-new\",\"component\":\"multi\",\"children\":[{\"name\":\"multi-menu_first_second-new_third\",\"path\":\"/multi-menu/first/second-new/third\",\"component\":\"self\",\"meta\":{\"title\":\"三级菜单\",\"requiresAuth\":true,\"icon\":\"mdi:menu\"}}],\"meta\":{\"title\":\"二级菜单(有子菜单)\",\"icon\":\"mdi:menu\"}}],\"meta\":{\"title\":\"一级菜单\",\"icon\":\"mdi:menu\"}}],\"meta\":{\"title\":\"多级菜单\",\"icon\":\"carbon:menu\",\"order\":7}},{\"name\":\"dashboard\",\"path\":\"/dashboard\",\"component\":\"basic\",\"children\":[{\"name\":\"dashboard_workbench\",\"path\":\"/dashboard/workbench\",\"component\":\"self\",\"meta\":{\"title\":\"工作台\",\"requiresAuth\":true}}],\"meta\":{\"title\":\"仪表盘\",\"icon\":\"carbon:dashboard\",\"order\":1}}],\"home\":\"dashboard_workbench\"}");
        return AjaxResult.success(param);
    }

    @GetMapping("/getParentMenu")
    public AjaxResult<List> getParentMenu() {
        return AjaxResult.success(service.getParentMenu());
    }


}
