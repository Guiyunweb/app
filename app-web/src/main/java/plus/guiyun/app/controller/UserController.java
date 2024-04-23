package plus.guiyun.app.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import plus.guiyun.app.api.UserService;
import plus.guiyun.app.common.code.domain.AjaxResult;
import plus.guiyun.app.common.code.domain.model.LoginBody;
import plus.guiyun.app.common.code.domain.model.LoginUser;
import plus.guiyun.app.domain.UserDo;
import plus.guiyun.app.framework.config.TokenConfig;
import plus.guiyun.app.framework.web.controller.CurdController;

import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController extends CurdController<UserService, UserDo, Long> {

    @GetMapping("/currentUser")
    public AjaxResult<LoginUser> currentUser() {
        LoginUser user = getUserInfo();
        return AjaxResult.showSuccess(user, "登录成功");
    }

}
