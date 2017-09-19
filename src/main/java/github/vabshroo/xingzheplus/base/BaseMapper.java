package github.vabshroo.xingzheplus.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2017/9/16
 * @time 13:19
 * @desc BaseMapper
 */
public interface BaseMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
