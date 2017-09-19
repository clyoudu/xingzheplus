package github.vabshroo.xingzheplus.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2017/9/16
 * @time 9:19
 * @desc CrawlerTaskService
 */
public interface CrawlerTaskService {

    /**
     * 返回包括sessionId在内的cookie/header,记录和更新用户信息
     * @param sessionId
     * @return
     */
    Map<String,Object> login(String sessionId);

    /**
     * 返回statDate所有轨迹编号
     * @param basicHeader
     * @param statDate
     * @return
     */
    List<Long> workoutList(Map<String,Object> basicHeader, DateTime statDate);

    /**
     * 轨迹详细数据
     * @param basicHeader
     * @param workoutId
     * @return
     */
    void workoutInfo(Map<String,Object> basicHeader,Long workoutId);

    /**
     * 时间/海拔/速度
     * @param basicHeader
     * @param workoutId
     * @return
     */
    void workoutTrack(Map<String,Object> basicHeader,Long workoutId);

}
