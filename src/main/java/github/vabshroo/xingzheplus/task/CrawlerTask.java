package github.vabshroo.xingzheplus.task;

import com.alibaba.fastjson.JSONObject;
import github.vabshroo.xingzheplus.base.BaseTask;
import github.vabshroo.xingzheplus.bvo.MessageResultBvo;
import github.vabshroo.xingzheplus.service.CrawlerTaskService;
import github.vabshroo.xingzheplus.util.DateUtil;
import github.vabshroo.xingzheplus.websocket.handler.CrawlerWebSocketHandler;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2017/9/16
 * @time 9:12
 * @desc 抓取数据
 */
@Component
public class CrawlerTask implements BaseTask {

    private final static Logger LOGGER = LoggerFactory.getLogger(CrawlerTask.class);

    @Autowired
    CrawlerTaskService crawlerTaskService;

    public void exec(Map<String, Object> param) {
        CrawlerWebSocketHandler handler = (CrawlerWebSocketHandler) param.get("handler");

        DateTime startDate = (DateTime) param.get("startDate");
        DateTime endDate = (DateTime) param.get("endDate");
        String sessionId = (String) param.get("sessionId");

        Map<String,Object> header = crawlerTaskService.login(sessionId);

        if(header == null){
            handler.sendMessage(sessionId, JSONObject.toJSONString(MessageResultBvo.err("登录失败！")));
            return;
        }

        while(startDate.isBefore(endDate.plusMonths(1))){
            handler.sendMessage(sessionId, JSONObject.toJSONString(MessageResultBvo.msg("开始抓取 " + startDate.toString(DateUtil.FMT_Y_M))));
            LOGGER.info("开始抓取 " + startDate.toString(DateUtil.FMT_Y_M));

            List<Long> workoutList = crawlerTaskService.workoutList(header,startDate);
            LOGGER.info("轨迹列表：" + JSONObject.toJSONString(workoutList));
            handler.sendMessage(sessionId, JSONObject.toJSONString(MessageResultBvo.msg("轨迹列表：" + JSONObject.toJSONString(workoutList))));

            if(workoutList != null && !workoutList.isEmpty()){
                workoutList.forEach(workoutId -> {
                    handler.sendMessage(sessionId, JSONObject.toJSONString(MessageResultBvo.msg("开始抓取 " + workoutId)));
                    LOGGER.info("开始抓取 " + workoutId);

                    crawlerTaskService.workoutInfo(header,workoutId);
                    handler.sendMessage(sessionId, JSONObject.toJSONString(MessageResultBvo.msg("抓取轨迹统计结束 " + workoutId)));
                    LOGGER.info("抓取轨迹统计结束 " + workoutId);
                    crawlerTaskService.workoutTrack(header,workoutId);
                    handler.sendMessage(sessionId, JSONObject.toJSONString(MessageResultBvo.msg("抓取轨迹点结束 " + workoutId)));
                    LOGGER.info("抓取轨迹点结束 " + workoutId);
                });
            }


            handler.sendMessage(sessionId, JSONObject.toJSONString(MessageResultBvo.msg("抓取结束 " + startDate.toString(DateUtil.FMT_Y_M))));
            LOGGER.info("抓取结束 " + startDate.toString(DateUtil.FMT_Y_M));

            startDate = startDate.plusMonths(1);
        }
    }
}
