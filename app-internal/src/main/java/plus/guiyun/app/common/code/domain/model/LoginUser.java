package plus.guiyun.app.common.code.domain.model;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import plus.guiyun.app.common.code.domain.entity.BaseUser;

import java.util.Collection;

@Getter
@Setter
public class LoginUser implements UserDetails {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户账户
     */
    private String account;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 用户唯一标识
     */
    private String token;


    /**
     * 基础用户信息
     */
    private BaseUser user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JSONField(serialize = false)
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getAccount();
    }


    /**
     * 账户是否未过期,过期无法验证
     *
     * @return
     */
    @Override
    @JSONField(serialize = false)
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return
     */
    @Override
    @JSONField(serialize = false)
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return
     */
    @Override
    @JSONField(serialize = false)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return
     */
    @Override
    @JSONField(serialize = false)
    public boolean isEnabled() {
        return true;
    }

}
