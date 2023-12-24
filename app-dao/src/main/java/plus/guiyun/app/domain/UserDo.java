package plus.guiyun.app.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import plus.guiyun.app.common.code.domain.entity.BaseUser;

@Getter
@Setter
@Entity
@Table(name = "tb_user")
public class UserDo extends BaseUser {

    @Id
    @GeneratedValue
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
