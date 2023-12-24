package plus.guiyun.app.service;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import plus.guiyun.app.api.UserService;
import plus.guiyun.app.common.code.domain.model.LoginUser;
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
    public LoginUser login(String username, String password) {
        UserDo userDo = repository.findByAccount(username);

        LoginUser loginUser = new LoginUser();
        loginUser.setUser(userDo);
        loginUser.setId(userDo.getId());
        loginUser.setToken(tokenService.createToken(loginUser));
        return loginUser;
    }
}
