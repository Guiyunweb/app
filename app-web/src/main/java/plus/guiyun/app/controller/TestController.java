package plus.guiyun.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.guiyun.app.api.TestService;
import plus.guiyun.app.domain.TestDo;
import plus.guiyun.app.framework.web.controller.CurdController;

@RestController
@RequestMapping("/test")
public class TestController extends CurdController<TestService, TestDo, Long> {

}
