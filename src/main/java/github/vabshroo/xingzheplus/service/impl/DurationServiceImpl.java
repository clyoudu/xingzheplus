package github.vabshroo.xingzheplus.service.impl;

import github.vabshroo.xingzheplus.dao.WorkoutViewMapper;
import github.vabshroo.xingzheplus.service.DurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2017/9/26
 * @time 20:45
 * @desc DurationServiceImpl
 */
@Service
public class DurationServiceImpl implements DurationService {

    @Autowired
    WorkoutViewMapper workoutViewMapper;

    @Override
    public Map<String, Object> durationSum() {
        return workoutViewMapper.durationSum().get(0);
    }
}
