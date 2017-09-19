package github.vabshroo.xingzheplus.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import github.vabshroo.xingzheplus.dao.UserInfoMapper;
import github.vabshroo.xingzheplus.dao.UserWorkoutMapper;
import github.vabshroo.xingzheplus.dao.WorkoutPropMapper;
import github.vabshroo.xingzheplus.dao.WorkoutTrackMapper;
import github.vabshroo.xingzheplus.pojo.UserInfo;
import github.vabshroo.xingzheplus.pojo.UserWorkout;
import github.vabshroo.xingzheplus.pojo.WorkoutProp;
import github.vabshroo.xingzheplus.pojo.WorkoutTrack;
import github.vabshroo.xingzheplus.service.CrawlerTaskService;
import github.vabshroo.xingzheplus.util.DateUtil;
import github.vabshroo.xingzheplus.util.GPSUtil;
import github.vabshroo.xingzheplus.util.HashMapBuilder;
import github.vabshroo.xingzheplus.util.UrlRepository;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static github.vabshroo.xingzheplus.util.HttpClientUtil.*;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2017/9/16
 * @time 9:19
 * @desc CrawlerTaskServiceImpl
 */
@Service
public class CrawlerTaskServiceImpl implements CrawlerTaskService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CrawlerTaskServiceImpl.class);

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UserWorkoutMapper userWorkoutMapper;

    @Autowired
    WorkoutPropMapper workoutPropMapper;

    @Autowired
    WorkoutTrackMapper workoutTrackMapper;

    public Map<String, Object> login(String sessionId) {
        Map<String,String> header = new HashMap<>();
        header.put("Cookie","sessionid="+sessionId);
        String userInfo = get(UrlRepository.API_GET_USER_INFO,header,null);

        if(StringUtils.isBlank(userInfo)){
            return null;//session 过期或其他原因需要重新登录
        }

        JSONObject jsonObject = JSONObject.parseObject(userInfo);

        Long userId = jsonObject.getLong("userid");
        String userName = jsonObject.getString("username");

        UserInfo user = new UserInfo().setSessionId(sessionId).setUserid(userId).setUsername(userName);

        try {
            userInfoMapper.insertSelective(user);
        }catch (DuplicateKeyException e){
            userInfoMapper.updateByPrimaryKeySelective(user);
        }

        return new HashMapBuilder<String,Object>()
                .put("Cookie","sessionid="+sessionId)
                .put("userInfo",user)
                .build();
    }

    public List<Long> workoutList(Map<String, Object> basicHeader, DateTime statDate) {
        Map<String,String> param = new HashMap<>();
        Long userid = ((UserInfo)basicHeader.get("userInfo")).getUserid();
        param.put("user_id",userid.toString());
        param.put("year",String.valueOf(statDate.getYear()));
        param.put("month",String.valueOf(statDate.getMonthOfYear()));

        Map<String,String> header = new HashMapBuilder<String,String>().put("Cookie",basicHeader.get("Cookie").toString()).build();

        String workout = get(UrlRepository.API_GET_MONTH_WORKOUT_INFO,header,param);

        if(StringUtils.isBlank(workout)){
            return null;//本月没有骑行记录或者出了错
        }

        JSONObject jsonObject = JSONObject.parseObject(workout);
        JSONArray woInfo = jsonObject.getJSONObject("data").getJSONArray("wo_info");

        List<UserWorkout> list = new ArrayList<>();
        List<Long> workoutList = new ArrayList<>();
        if(woInfo != null && !woInfo.isEmpty()){
            woInfo.forEach(o -> {
                Long workoutId = ((JSONObject)o).getLong("id");
                workoutList.add(workoutId);
                list.add(new UserWorkout().setUserid(userid).setWorkoutId(workoutId));
            });
        }

        if(!list.isEmpty()){

            list.forEach(out -> {
                try {
                    userWorkoutMapper.insertSelective(out);
                }catch (DuplicateKeyException e){
                    userWorkoutMapper.updateByPrimaryKeySelective(out);
                }
            });
        }
        return workoutList;
    }

    public void workoutInfo(Map<String, Object> basicHeader, Long workoutId) {
        Map<String,String> param = new HashMap<>();
        param.put("workout_id",workoutId.toString());

        Map<String,String> header = new HashMapBuilder<String,String>().put("Cookie",basicHeader.get("Cookie").toString()).build();

        String workout = get(UrlRepository.API_GET_WORKOUT_INFO,header,param);

        if(StringUtils.isBlank(workout)){
            return;//轨迹不存在或者出错
        }

        List<WorkoutProp> list = new ArrayList<>();
        JSONObject jsonObject = JSONObject.parseObject(workout);

        JSONObject workoutInfo = jsonObject.getJSONObject("workout");

        workoutInfo.forEach((s, o) -> {
            String propValue = o.toString();
            if(propValue.length() > 1000){
                propValue = propValue.substring(0,1000);
            }
            list.add(new WorkoutProp().setPropName(s).setPropValue(propValue).setWorkoutId(workoutId));
        });

        if(!list.isEmpty()){
            list.forEach(workoutProp -> {
                try {
                    workoutPropMapper.insertSelective(workoutProp);
                }catch (DuplicateKeyException e){
                    Example example = new Example(WorkoutProp.class);
                    example.createCriteria().andEqualTo("workoutId",workoutProp.getWorkoutId()).andEqualTo("propName",workoutProp.getPropName());
                    workoutPropMapper.updateByExampleSelective(workoutProp,example);
                }
            });
        }
    }

    public void workoutTrack(Map<String, Object> basicHeader, Long workoutId) {
        Map<String,String> header = new HashMapBuilder<String,String>().put("Cookie",basicHeader.get("Cookie").toString()).build();
        String gpx = downGet(String.format(UrlRepository.API_GET_WORKOUT_GPX,workoutId),header,null);
        if(StringUtils.isBlank(gpx)){
            return;//轨迹不存在或者
        }

        Document doc = Jsoup.parse(gpx);

        List<WorkoutTrack> list = new ArrayList<>();

        try{
            Elements trackPoints = doc.getElementsByTag("gpx").first()
                    .getElementsByTag("trk").first()
                    .getElementsByTag("trkseg").first()
                    .getElementsByTag("trkpt");

            trackPoints.forEach(trackPoint -> {
                Double ele = Double.parseDouble(trackPoint.getElementsByTag("ele").first().text());
                Double lat = Double.parseDouble(trackPoint.attr("lat"));
                Double lon = Double.parseDouble(trackPoint.attr("lon"));
                Long time = DateUtil.gpxTimeToLong(trackPoint.getElementsByTag("time").first().text().replaceAll("T"," ").replaceAll("Z",""));
                double[] gcj = GPSUtil.wgs84togcj02(lon,lat);
                double[] bd = GPSUtil.wgs84tobd09(lon,lat);
                list.add(new WorkoutTrack().setWorkoutId(workoutId)
                        .setTrackNo(time).setAltitude(ele)
                        .setGpsLat(lat).setGpsLon(lon)
                        .setGcjLat(gcj[1])
                        .setGcjLon(gcj[0])
                        .setBdLat(bd[1])
                        .setBdLon(bd[0])
                );
            });
        }catch (Exception e){
            LOGGER.error("解析gpx出错！{}",gpx);
        }


        if(!list.isEmpty()){


            Example exampleD = new Example(WorkoutTrack.class);
            exampleD.createCriteria().andEqualTo("workoutId",workoutId);
            workoutTrackMapper.deleteByExample(exampleD);

            list.forEach(workoutTrack -> {
                try {
                    workoutTrackMapper.insertSelective(workoutTrack);
                }catch (DuplicateKeyException e){
                    Example example = new Example(WorkoutTrack.class);
                    example.createCriteria().andEqualTo("workoutId",workoutTrack.getWorkoutId()).andEqualTo("trackNo",workoutTrack.getTrackNo());
                    workoutTrackMapper.updateByExampleSelective(workoutTrack,example);
                }
            });
        }
    }

}
