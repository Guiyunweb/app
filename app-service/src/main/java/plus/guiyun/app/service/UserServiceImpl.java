package plus.guiyun.app.service;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import plus.guiyun.app.api.UserService;
import plus.guiyun.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository repository;

    @Override
    public String getUserName(Long id) {
        return repository.getReferenceById(id).getName();
    }
}
