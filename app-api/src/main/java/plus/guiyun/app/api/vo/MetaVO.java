package plus.guiyun.app.api.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MetaVO {

    private String title;
    private boolean requiresAuth;
    private String icon;
    private Long order;

}
