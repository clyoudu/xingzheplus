package github.vabshroo.xingzheplus.bvo;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2017/9/15
 * @time 20:27
 * @desc 返回消息类
 */
public class MessageResultBvo implements Serializable {

    private final static Integer CODE_SUC = 0;
    private final static Integer CODE_ERR = 500;

    private final static String MSG_ERR = "操作失败！";
    private final static String MSG_SUC = "操作成功！";

    private Integer code;
    private String msg;
    private Object data;

    public static MessageResultBvo data(Object data){
        return bvo(CODE_SUC,MSG_SUC,data);
    }

    public static MessageResultBvo err(String msg){
        return bvo(CODE_ERR,msg,null);
    }

    public static MessageResultBvo msg(String msg){
        return bvo(CODE_SUC,msg,null);
    }

    public static MessageResultBvo err(){
        return err(MSG_ERR);
    }

    public static MessageResultBvo bvo(Integer code,String msg,Object data){
        return new MessageResultBvo().setCode(code).setMsg(msg).setData(data);
    }

    public static MessageResultBvo throwable(Throwable e){
        return bvo(CODE_ERR,MSG_ERR,e);
    }

    public Integer getCode() {
        return code;
    }

    public MessageResultBvo setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public MessageResultBvo setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public MessageResultBvo setData(Object data) {
        this.data = data;
        return this;
    }
}
