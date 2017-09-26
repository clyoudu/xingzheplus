package github.vabshroo.xingzheplus.controller;

import github.vabshroo.xingzheplus.service.DurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2017/9/26
 * @time 20:43
 * @desc DurationController
 */
@Controller
public class DurationController extends BaseController {

    @Autowired
    DurationService durationService;

    @RequestMapping("duration")
    public String duration(Model model){
        model.addAttribute("duration_sum",durationService.durationSum());
        return "duration";
    }

}
