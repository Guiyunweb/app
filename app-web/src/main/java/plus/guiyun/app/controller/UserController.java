package plus.guiyun.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.guiyun.app.api.UserService;
import plus.guiyun.app.domain.UserDo;
import plus.guiyun.app.framework.web.controller.CurdController;
import plus.guiyun.app.framework.web.service.CurdService;

@RestController
@RequestMapping("/users")
public class UserController extends CurdController<UserService, UserDo, Long> {
}
