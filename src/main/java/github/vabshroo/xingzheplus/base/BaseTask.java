package github.vabshroo.xingzheplus.base;

import org.joda.time.DateTime;

import java.util.Map;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2017/9/16
 * @time 9:13
 * @desc BaseTask
 */
public interface BaseTask {

    void exec(Map<String,Object> param);

}
