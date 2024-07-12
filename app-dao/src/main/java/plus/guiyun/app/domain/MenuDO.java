package plus.guiyun.app.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import plus.guiyun.app.framework.web.domain.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "tb_menu")
public class MenuDO extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String route;

    private String component;

    /** 路由标题(可用来作document.title或者菜单的名称) */
    private String title;

    /** 需要登录权限 */
    private boolean requiresAuth;


    /**哪些类型的用户有权限才能访问的路由**/
    private String permissions;

    /** 菜单和面包屑对应的图标 */
    private String icon;

    private String keepAlive;

    private Long sortBy;

    private Long parentId;

}
