package plus.guiyun.app.controller;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import plus.guiyun.app.api.UserService;
import plus.guiyun.app.api.vo.UserInfoVo;
import plus.guiyun.app.common.code.domain.AjaxResult;
import plus.guiyun.app.common.code.domain.model.LoginBody;
import plus.guiyun.app.common.code.redis.RedisCache;
import plus.guiyun.app.framework.config.TokenConfig;
import plus.guiyun.app.framework.web.service.TokenService;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    RedisCache redisCache;

    @RequestMapping("/login")
    public AjaxResult<UserInfoVo> getUserName(@RequestBody @Valid LoginBody loginBody) {
        return userService.login(loginBody.getAccount(), loginBody.getPassword());
    }

    @GetMapping("/updateToken")
    public AjaxResult<Object> updateToken() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
        String userKey = TokenService.getLoginUUID(request);
        redisCache.expire(userKey,TokenConfig.expireTime, TimeUnit.MINUTES);
        JSONObject param = new JSONObject();
        param.put("token",request.getHeader(TokenConfig.header));
        return AjaxResult.success(param);
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
