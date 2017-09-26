package github.vabshroo.xingzheplus.controller;

import com.alibaba.fastjson.JSONObject;
import github.vabshroo.xingzheplus.service.WorkoutViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2017/9/18
 * @time 22:01
 * @desc ChartController
 */
@Controller
public class DistanceController extends BaseController {

    @Autowired
    WorkoutViewService workoutViewService;

    @RequestMapping(value = "/distance",method = RequestMethod.GET)
    public String durationDistance(Model model){
        model.addAttribute("distance",workoutViewService.distance());
        model.addAttribute("time_distance", JSONObject.toJSONString(workoutViewService.timeDistance()));
        model.addAttribute("distance_distribution", workoutViewService.distanceDistribution());
        return "distance";
    }

}
