package github.vabshroo.xingzheplus.service.impl;

import github.vabshroo.xingzheplus.dao.WorkoutViewMapper;
import github.vabshroo.xingzheplus.service.WorkoutViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2017/9/20
 * @time 21:29
 * @desc WorkoutViewServiceImpl
 */
@Service
public class WorkoutViewServiceImpl implements WorkoutViewService {

    @Autowired
    WorkoutViewMapper workoutViewMapper;

    @Override
    public Map<String, Object> distance() {
        List<Map<String,Object>> distance = workoutViewMapper.distance();
        if(distance == null || distance.isEmpty()){
            return null;
        }
        return distance.get(0);
    }

    @Override
    public List<double[]> timeDistance() {
        List<double[]> result = new ArrayList<>();
        List<Map<String,Object>> list = workoutViewMapper.timeDistance();
        if(list != null && !list.isEmpty()){
            list.forEach((map -> result.add(new double[]{Double.parseDouble(map.get("w").toString())
                    ,Double.parseDouble(map.get("h").toString()),Double.parseDouble(map.get("distance").toString())})));
        }
        return result;
    }

    @Override
    public Map<String, Object> distanceDistribution() {
        List<Map<String,Object>> distanceDis = workoutViewMapper.distanceDistribution();
        if(distanceDis == null || distanceDis.isEmpty()){
            return null;
        }
        return distanceDis.get(0);
    }
}
