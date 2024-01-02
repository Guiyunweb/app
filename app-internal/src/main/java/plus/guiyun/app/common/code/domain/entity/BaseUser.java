package plus.guiyun.app.common.code.domain.entity;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import plus.guiyun.app.framework.web.domain.BaseEntity;

@Getter
@Setter
public class BaseUser extends BaseEntity {

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
