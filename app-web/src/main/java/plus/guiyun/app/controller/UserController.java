package plus.guiyun.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.guiyun.app.api.UserService;
import plus.guiyun.app.common.code.domain.AjaxResult;
import plus.guiyun.app.common.code.domain.model.LoginUser;
import plus.guiyun.app.domain.UserDo;
import plus.guiyun.app.framework.web.controller.CurdController;

@RestController
@RequestMapping("/users")
public class UserController extends CurdController<UserService, UserDo, Long> {

    @GetMapping("/currentUser")
    public AjaxResult<LoginUser> currentUser() {
        LoginUser user = getUserInfo();
        return AjaxResult.showSuccess(user, "登录成功");
    }

}
