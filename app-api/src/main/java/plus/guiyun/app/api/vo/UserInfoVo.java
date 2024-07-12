package plus.guiyun.app.api.vo;

import lombok.Getter;
import lombok.Setter;
import plus.guiyun.app.common.code.domain.model.LoginUser;

/**
 * 登录用户信息
 */
@Getter
@Setter
public class UserInfoVo {

    public UserInfoVo(LoginUser user,RouteVO route) {
        this.route = route;
        this.user = user;
    }

    private RouteVO route;

    private LoginUser user;

}
