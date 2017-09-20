package github.vabshroo.xingzheplus.dao;

import github.vabshroo.xingzheplus.base.BaseMapper;
import github.vabshroo.xingzheplus.pojo.WorkoutView;
import java.util.List;
import java.util.Map;

public interface WorkoutViewMapper extends BaseMapper<WorkoutView> {
    List<Map<String,Object>> distance();
    List<Map<String,Object>> timeDistance();
}