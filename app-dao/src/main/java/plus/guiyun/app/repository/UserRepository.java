package plus.guiyun.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plus.guiyun.app.domain.UserDo;


public interface UserRepository extends JpaRepository<UserDo, Long> {
    UserDo findByAccount(String account);

}
