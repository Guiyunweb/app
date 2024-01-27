package plus.guiyun.app.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    /**
     * 路由的标识，必填且与页面组件的name一致
     */
    @NotNull(message = "菜单名不能为空")
    private String name;

    /**
     * 路由在游览器地址栏里的hash值，也就是说这个路由要用什么URL地址来访问。
     */
    private String path;

    /**
     * 加载的页面组件位置，系统将自动组装成 () => import('@/views/home') 的形式。
     */
    private String component;

//    private String meta;

    private Long parentId;

    private Long sortBy;


    /**
     * 菜单
     */
    private String title;

}
