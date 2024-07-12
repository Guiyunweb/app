package plus.guiyun.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.guiyun.app.api.MenuService;
import plus.guiyun.app.api.UserService;
import plus.guiyun.app.api.vo.RouteVO;
import plus.guiyun.app.api.vo.UserInfoVo;
import plus.guiyun.app.common.code.domain.AjaxResult;
import plus.guiyun.app.common.code.domain.model.LoginUser;
import plus.guiyun.app.domain.UserDo;
import plus.guiyun.app.framework.web.controller.CurdController;

@RestController
@RequestMapping("/users")
public class UserController extends CurdController<UserService, UserDo, Long> {

    @Autowired
    MenuService menuService;

    @GetMapping("/userinfo")
    public AjaxResult<UserInfoVo> currentUser() {
        LoginUser user = getUserInfo();
        RouteVO route = menuService.getUserMenu(getUserId());

        return AjaxResult.showSuccess(new UserInfoVo(user,route), "登录成功");
    }

}
