package plus.guiyun.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plus.guiyun.app.domain.TestDo;

public interface TestRepository extends JpaRepository<TestDo, Long> {
}
