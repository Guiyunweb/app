package plus.guiyun.app.common.code.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseUser {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 账户名
     */
    private String account;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
