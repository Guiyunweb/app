package plus.guiyun.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.guiyun.app.api.PostsService;
import plus.guiyun.app.domain.PostsDo;
import plus.guiyun.app.framework.web.controller.CurdController;

@RestController
@RequestMapping("/posts")
public class PostsController extends CurdController<PostsService, PostsDo, Long> {

}
