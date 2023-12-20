package plus.guiyun.app.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_user")
public class UserDo {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer age;
    private String password;

}
