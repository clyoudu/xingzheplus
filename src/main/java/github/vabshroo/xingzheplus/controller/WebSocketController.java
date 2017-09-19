package github.vabshroo.xingzheplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2017/9/16
 * @time 22:43
 * @desc WebSocketController
 */
@Controller
public class WebSocketController {

    @RequestMapping("testws")
    public String testws(){
        return "testws";
    }

}
