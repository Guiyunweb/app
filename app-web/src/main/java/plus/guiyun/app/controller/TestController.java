package plus.guiyun.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.guiyun.app.api.UserService;
import plus.guiyun.app.common.utils.SecurityUtils;

@Component
@RestController
public class TestController {

    @Autowired
    UserService userService;

    @RequestMapping("/test")
    public Long getUserName() {
        String name = userService.getUserName(1L);
        return SecurityUtils.getUserId();
    }

    @RequestMapping("/test2")
    public String test2() {
        return "你好,Test2";
    }


}
