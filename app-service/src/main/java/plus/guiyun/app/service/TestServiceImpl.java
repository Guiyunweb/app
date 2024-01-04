package plus.guiyun.app.service;

import org.springframework.stereotype.Service;
import plus.guiyun.app.api.TestService;
import plus.guiyun.app.api.UserService;
import plus.guiyun.app.domain.TestDo;
import plus.guiyun.app.domain.UserDo;
import plus.guiyun.app.framework.web.service.CurdServiceImpl;
import plus.guiyun.app.repository.TestRepository;
import plus.guiyun.app.repository.UserRepository;

@Service
public class TestServiceImpl extends CurdServiceImpl<TestRepository, TestDo, Long> implements TestService {
}
