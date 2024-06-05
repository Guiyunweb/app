package plus.guiyun.app.api.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MenuTree {

    private Long id;

    private String name;

    private String path;

    private String component;

    private MetaVO meta;

    private Long parentId;

    private List<MenuTree> children;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private Date createTime;
//
//
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private Date updateTime;

}
