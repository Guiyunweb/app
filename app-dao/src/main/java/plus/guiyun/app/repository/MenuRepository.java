package plus.guiyun.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plus.guiyun.app.domain.MenuDO;

public interface MenuRepository extends JpaRepository<MenuDO, Long> {
}
