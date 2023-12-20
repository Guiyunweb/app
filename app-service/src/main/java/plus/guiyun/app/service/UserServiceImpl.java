package plus.guiyun.app.service;

import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import plus.guiyun.app.api.UserService;
import plus.guiyun.app.common.code.domain.model.LoginUser;
import plus.guiyun.app.domain.UserDo;
import plus.guiyun.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository repository;

    @Override
    public String getUserName(Long id) {
        return repository.getReferenceById(id).getName();
    }

    @Override
    public LoginUser login(String username, String password) {
        UserDo userDo = repository.getReferenceById(1L);

        // 认证成功后，设置认证信息到 Spring Security 上下文中
//        Authentication authentication =
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        return null;
    }
}
