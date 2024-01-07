package plus.guiyun.app.api.vo;

import com.alibaba.fastjson2.JSONObject;
import jakarta.persistence.Convert;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class MenuTree {

    private Long id;

    /**
     * 路由的标识，必填且与页面组件的name一致
     */
    private String name;

    /**
     * 路由在游览器地址栏里的hash值，也就是说这个路由要用什么URL地址来访问。
     */
    private String path;

    /**
     * 加载的页面组件位置，系统将自动组装成 () => import('@/views/home') 的形式。
     */
    private String component;

    private JSONObject meta;

    private Long parentId;

    private List<MenuTree> children;

}
