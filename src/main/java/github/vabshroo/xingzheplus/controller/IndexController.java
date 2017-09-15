package github.vabshroo.xingzheplus.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2017/9/15
 * @time 20:36
 * @desc Dashboard
 */
@Controller
public class IndexController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("index")
    public String index(){
        return "index";
    }

}
