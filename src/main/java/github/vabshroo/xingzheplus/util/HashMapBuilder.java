package github.vabshroo.xingzheplus.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2017/9/16
 * @time 14:23
 * @desc HashMapBuilder
 */
public class HashMapBuilder<K,V> {

    Map<K,V> map = new HashMap<>();

    public HashMapBuilder<K,V> put(K key,V value){
        map.put(key,value);
        return this;
    }

    public HashMapBuilder<K,V> putAll(Map<K,V> toPut){
        if(toPut != null && !toPut.isEmpty()){
            map.putAll(toPut);
        }
        return this;
    }

    public Map<K,V> build (){
        return map;
    }

}
