package plus.guiyun.app.framework.web.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable{


    private static final long serialVersionUID = 1L;


    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    @CreationTimestamp
    private Date createTime;


    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    @UpdateTimestamp
    private Date updateTime;


}
