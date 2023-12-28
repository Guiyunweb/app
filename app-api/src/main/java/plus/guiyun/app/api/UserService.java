package plus.guiyun.app.api;

import plus.guiyun.app.common.code.domain.model.LoginUser;

public interface UserService {

    String getUserName(Long id);

    LoginUser login(String account, String password);

}
