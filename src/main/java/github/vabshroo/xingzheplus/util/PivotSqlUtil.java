package github.vabshroo.xingzheplus.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2017/9/19
 * @time 22:16
 * @desc 纵表便横标
 */
public class PivotSqlUtil {

    /**
     * 将纵表转为横表的sql
     * 采用的方式为：每个属性用一个sql查询出来，然后通过逻辑主键关联展示为横表
     *
     * @param tableName 表名
     * @param logicKey 逻辑主键
     * @param propColName 属性列名
     * @param valueColName 属性值列名
     * @param props 所有属性
     * @return
     */
    public static String pivotSql(String tableName, String logicKey, String propColName, String valueColName, List<String> props) {

        tableName = formatNames(tableName);
        logicKey = formatNames(logicKey);
        propColName = formatNames(propColName);
        valueColName = formatNames(valueColName);

        StringBuilder builder = new StringBuilder();

        builder.append("SELECT \n")
                .append("\t").append(logicKey).append(",\n");

        String finalPropColName = propColName;
        String finalValueColName = valueColName;
        List<String> max = new ArrayList<>();
        String finalLogicKey = logicKey;
        props.forEach(prop -> {
            if(!prop.equals(finalLogicKey)){
                max.add("\t" + "MAX(IF(" + finalPropColName +"='" + prop + "'," + finalValueColName + ",NULL)) '" + prop + "'");
            }

        });

        builder.append(StringUtils.join(max,",\n"))
                .append("\nFROM \n\t").append(tableName).append("\n").append("GROUP BY \n\t").append(logicKey);



        return builder.toString();
    }

    /**
     * 防止有mysql关键字导致sql语句出错
     * @param name
     * @return
     */
    private static String formatNames(String name) {
        return "`" + name + "`";
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/ApplicationContext-datasource.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
        List<Map<String, Object>> propsListMap = jdbcTemplate.queryForList("SELECT prop_name FROM workout_prop GROUP BY prop_name ORDER BY COUNT(*) DESC;");
        List<String> props = new ArrayList<>();
        propsListMap.forEach(map -> props.add(map.get("prop_name").toString()));

        System.out.println(pivotSql("workout_prop", "workout_id", "prop_name", "prop_value", props));
    }

}
