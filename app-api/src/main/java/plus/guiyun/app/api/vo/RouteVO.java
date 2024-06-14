package plus.guiyun.app.api.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RouteVO {

    private String home;

    private List<MenuTree> routes;

}
