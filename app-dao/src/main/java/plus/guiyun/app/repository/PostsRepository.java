package plus.guiyun.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plus.guiyun.app.domain.PostsDo;

public interface PostsRepository extends JpaRepository<PostsDo, Long> {
}
