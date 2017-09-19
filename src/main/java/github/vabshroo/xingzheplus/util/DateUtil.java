package github.vabshroo.xingzheplus.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2017/9/16
 * @time 21:36
 * @desc 时间相关
 */
public class DateUtil {

    public static String FMT_Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";
    public static String FMT_Y_M_D = "yyyy-MM-dd";
    public static String FMT_Y_M = "yyyy-MM";

    public static Long gpxTimeToLong(String time){
        DateTimeFormatter format = DateTimeFormat.forPattern(FMT_Y_M_D_H_M_S);
        DateTime dateTime = DateTime.parse(time, format);
        return dateTime.getMillis();
    }

    public static DateTime get(String time,String format){
       return DateTime.parse(time, DateTimeFormat.forPattern(format));
    }

    public static void main(String[] args){
        System.out.println(gpxTimeToLong("2017-01-01T12:12:!2Z"));
    }

}
