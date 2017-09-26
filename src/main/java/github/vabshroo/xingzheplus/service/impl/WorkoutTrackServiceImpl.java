package github.vabshroo.xingzheplus.service.impl;

import github.vabshroo.xingzheplus.dao.WorkoutTrackMapper;
import github.vabshroo.xingzheplus.pojo.WorkoutTrack;
import github.vabshroo.xingzheplus.service.WorkoutTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2017/9/25
 * @time 21:10
 * @desc WorkoutTrackServiceImpl
 */
@Service
public class WorkoutTrackServiceImpl implements WorkoutTrackService {

    @Autowired
    WorkoutTrackMapper workoutTrackMapper;

    @Override
    public ArrayList track() {
        Example example = new Example(WorkoutTrack.class);
        example.setOrderByClause(" workout_id asc,track_no asc ");
        List<WorkoutTrack> list = workoutTrackMapper.selectByExample(example);
        Map<Long,List<List<Double>>> map = new HashMap<>();

        list.forEach(workoutTrack -> {
            if(!map.containsKey(workoutTrack.getWorkoutId())){
                map.put(workoutTrack.getWorkoutId(),new ArrayList<>());
            }

            map.get(workoutTrack.getWorkoutId()).add(Arrays.asList(workoutTrack.getBdLon(),workoutTrack.getBdLat()));
        });

        return new ArrayList<>(map.values());
    }
}
