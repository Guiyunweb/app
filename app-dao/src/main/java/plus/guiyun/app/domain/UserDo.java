package plus.guiyun.app.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import plus.guiyun.app.framework.web.domain.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "tb_user")
public class UserDo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private Integer roleId;

}
