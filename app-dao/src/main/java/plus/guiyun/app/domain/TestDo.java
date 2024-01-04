package plus.guiyun.app.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import plus.guiyun.app.framework.web.domain.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "tb_test")
public class TestDo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String title;

    String body;

}
