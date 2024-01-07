package plus.guiyun.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.guiyun.app.api.MenuService;
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
    public AjaxResult<List> getMenuTree() {
        return AjaxResult.success(service.getMenuTree());
    }


}
