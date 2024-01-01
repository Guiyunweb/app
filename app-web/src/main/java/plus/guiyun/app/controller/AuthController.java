package plus.guiyun.app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.guiyun.app.api.UserService;
import plus.guiyun.app.common.code.domain.AjaxResult;
import plus.guiyun.app.common.code.domain.model.LoginBody;
import plus.guiyun.app.common.code.domain.model.LoginUser;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public AjaxResult<LoginUser> getUserName(@RequestBody @Valid LoginBody loginBody) {
        LoginUser loginUser = userService.login(loginBody.getAccount(), loginBody.getPassword());
        return AjaxResult.showSuccess(loginUser, "登录成功");
    }

}
