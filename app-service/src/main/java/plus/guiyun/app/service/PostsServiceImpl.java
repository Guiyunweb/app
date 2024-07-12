package plus.guiyun.app.service;


import org.springframework.stereotype.Service;
import plus.guiyun.app.api.PostsService;
import plus.guiyun.app.domain.PostsDo;
import plus.guiyun.app.framework.web.service.CurdServiceImpl;
import plus.guiyun.app.repository.PostsRepository;

@Service
public class PostsServiceImpl  extends CurdServiceImpl<PostsRepository, PostsDo, Long> implements PostsService {
}
