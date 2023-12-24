package plus.guiyun.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.guiyun.app.api.UserService;
import plus.guiyun.app.common.code.domain.model.LoginUser;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public LoginUser getUserName() {
        return userService.login("1", "1");
    }


}
