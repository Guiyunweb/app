package plus.guiyun.app.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class TestController {


    @RequestMapping("/test")
    public String getUserName() {
        return "你好,XXX";
    }


}
