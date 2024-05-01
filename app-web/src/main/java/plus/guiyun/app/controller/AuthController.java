package plus.guiyun.app.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import plus.guiyun.app.api.UserService;
import plus.guiyun.app.common.code.domain.AjaxResult;
import plus.guiyun.app.common.code.domain.model.LoginBody;
import plus.guiyun.app.common.code.domain.model.LoginUser;
import plus.guiyun.app.common.code.redis.RedisCache;
import plus.guiyun.app.framework.web.service.TokenService;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    RedisCache redisCache;

    @RequestMapping("/login")
    public AjaxResult<LoginUser> getUserName(@RequestBody @Valid LoginBody loginBody) {
        LoginUser loginUser = userService.login(loginBody.getAccount(), loginBody.getPassword());
        return AjaxResult.success(loginUser, "登录成功");
    }

    @PostMapping("/outLogin")
    public AjaxResult<Object> outLogin() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
        String uuid = TokenService.getLoginUUID(request);
        redisCache.deleteObject(uuid);
        return AjaxResult.showSuccess( "退出登录成功");
    }

}
