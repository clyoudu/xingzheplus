package github.vabshroo.xingzheplus.controller;

import com.alibaba.fastjson.JSONObject;
import github.vabshroo.xingzheplus.bvo.MessageResultBvo;
import github.vabshroo.xingzheplus.service.CrawlerTaskService;
import github.vabshroo.xingzheplus.task.CrawlerTask;
import github.vabshroo.xingzheplus.util.DateUtil;
import github.vabshroo.xingzheplus.util.HashMapBuilder;
import github.vabshroo.xingzheplus.websocket.handler.CrawlerWebSocketHandler;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2017/9/16
 * @time 23:42
 * @desc CrawlerController
 */
@Controller
public class CrawlerController {

    @Autowired
    CrawlerWebSocketHandler handler;

    @Autowired
    CrawlerTask crawlerTask;

    @RequestMapping("crawler")
    public String crawler(){
        return "crawler";
    }
    @ResponseBody
    @RequestMapping(value = "startCrawler",method = RequestMethod.POST)
    public MessageResultBvo startCrawler(@RequestBody String json,HttpSession session){
        JSONObject param;
        if(StringUtils.isBlank(json)){
            return MessageResultBvo.err("参数为空！");
        }

         param = JSONObject.parseObject(json);
        if(!param.containsKey("y1") || !param.containsKey("y2") || !param.containsKey("m1") || !param.containsKey("m2") || !param.containsKey("sessionId")){
            return MessageResultBvo.err("参数不完整！");
        }
        String y1 = param.getString("y1");
        String y2 = param.getString("y2");
        String m1 = format(param.getString("m1"));
        String m2 = format(param.getString("m2"));
        String sessionId = param.getString("sessionId");

        DateTime startDate = DateUtil.get(y1 + "-" + m1 + "-01",DateUtil.FMT_Y_M_D);
        DateTime endDate = DateUtil.get(y2 + "-" + m2 + "-01",DateUtil.FMT_Y_M_D);

        session.setAttribute("userSessionId",sessionId);

        new Thread(() -> crawlerTask.exec(new HashMapBuilder<String,Object>()
                .put("sessionId",sessionId)
                .put("startDate",startDate)
                .put("endDate",endDate)
                .put("handler",handler)
                .build())).start();

        return MessageResultBvo.msg("抓取开始！");

    }

    private String format(String m) {
        return m.length() == 2 ? m : "0" + m;
    }

}
