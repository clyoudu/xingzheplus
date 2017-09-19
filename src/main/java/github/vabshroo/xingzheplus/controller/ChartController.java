package github.vabshroo.xingzheplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2017/9/18
 * @time 22:01
 * @desc ChartController
 */
@Controller
public class ChartController extends BaseController {

    @RequestMapping(value = "/durationDistance")
    public String durationDistance(){
        return "chart_duration_distance";
    }

}
