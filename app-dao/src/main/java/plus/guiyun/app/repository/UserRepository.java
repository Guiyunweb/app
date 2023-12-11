package plus.guiyun.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plus.guiyun.app.domain.UserDo;

import java.util.List;


public interface UserRepository extends JpaRepository<UserDo, Long> {
    @Override
    <S extends UserDo> List<S> saveAll(Iterable<S> entities);
}
