package github.vabshroo.xingzheplus.controller;

import com.alibaba.fastjson.JSONObject;
import github.vabshroo.xingzheplus.service.WorkoutTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2017/9/25
 * @time 21:05
 * @desc LocationController
 */
@Controller
public class LocationController extends BaseController {

    @Autowired
    WorkoutTrackService workoutTrackService;

    @RequestMapping(value = "location")
    public String location(Model model){
        model.addAttribute("data", JSONObject.toJSONString(workoutTrackService.track()));
        return "location";
    }

}
