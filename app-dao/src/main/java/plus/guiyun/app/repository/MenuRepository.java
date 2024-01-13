package plus.guiyun.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plus.guiyun.app.domain.MenuDO;

import java.util.List;

public interface MenuRepository extends JpaRepository<MenuDO, Long> {

    List<MenuDO> findByParentIdIsNotNull();
}
