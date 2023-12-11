package plus.guiyun.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import plus.guiyun.app.api.UserService;
import plus.guiyun.app.domain.UserDo;
import plus.guiyun.app.repository.UserRepository;

@Service
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public String getUserName(Long id) {
        return repository.getReferenceById(id).getName();
    }
}
