package plus.guiyun.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.guiyun.app.api.UserService;
import plus.guiyun.app.common.code.domain.Result;
import plus.guiyun.app.common.code.domain.SuccessResult;
import plus.guiyun.app.common.code.domain.model.LoginBody;
import plus.guiyun.app.common.code.domain.model.LoginUser;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public Result<LoginUser> getUserName(@RequestBody LoginBody loginBody) {
        LoginUser loginUser = userService.login(loginBody.getAccount(), loginBody.getPassword());
        return new SuccessResult<>("登录成功", loginUser);
    }

}
