package github.vabshroo.xingzheplus.service;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2017/9/20
 * @time 21:28
 * @desc WorkoutViewService
 */
public interface WorkoutViewService {

    Map<String,Object> distance();
    List<double[]> timeDistance();

}
