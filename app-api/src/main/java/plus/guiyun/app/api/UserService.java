package plus.guiyun.app.api;

import plus.guiyun.app.api.vo.UserInfoVo;
import plus.guiyun.app.common.code.domain.AjaxResult;
import plus.guiyun.app.domain.UserDo;
import plus.guiyun.app.framework.web.service.CurdService;

public interface UserService extends CurdService<UserDo, Long> {

    String getUserName(Long account);

    AjaxResult<UserInfoVo> login(String account, String password);

}
