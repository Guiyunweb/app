package plus.guiyun.app.service;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import plus.guiyun.app.api.UserService;
import plus.guiyun.app.common.code.domain.model.LoginUser;
import plus.guiyun.app.common.exception.ServiceException;
import plus.guiyun.app.common.utils.SecurityUtils;
import plus.guiyun.app.domain.UserDo;
import plus.guiyun.app.framework.web.service.TokenService;
import plus.guiyun.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository repository;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @Override
    public String getUserName(Long id) {
        return repository.getReferenceById(id).getUsername();
    }

    @Override
    public LoginUser login(String account, String password) {
        UserDo userDo = repository.findByAccount(account);
        if (ObjectUtils.isEmpty(userDo)) {
            throw new ServiceException("对不起,登录用户：" + account + " 不存在");
        }

        if (SecurityUtils.matchesPassword(password, userDo.getPassword())) {
            throw new ServiceException("对不起,密码错误");
        }
        userDo.setPassword(null);

        LoginUser loginUser = new LoginUser();
        loginUser.setUser(userDo);
        loginUser.setId(userDo.getId());
        loginUser.setToken(tokenService.createToken(loginUser));
        return loginUser;
    }
}
