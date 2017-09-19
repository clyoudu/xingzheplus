package github.vabshroo.xingzheplus.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2017/9/16
 * @time 7:40
 * @desc HttpClient
 */
public class HttpClientUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

    public static String post(String url, Map<String,String> header,Object param){
        if(StringUtils.isBlank(url)){
            return null;
        }

        HttpClient client = HttpClientManager.getHttpClient();
        try {

            HttpPost httpPost = new HttpPost(url);

            if(header != null && !header.isEmpty()){
                header.forEach((key, value) -> httpPost.setHeader(new BasicHeader(key, value)));
            }

            if(param != null){
                if(param instanceof JSONObject){
                    StringEntity httpEntity = new StringEntity(JSONObject.toJSONString(param),"UTF-8");
                    httpEntity.setContentEncoding("UTF-8");
                    httpEntity.setContentType("application/json");
                    httpPost.setEntity(httpEntity);
                }else if(param instanceof Map){
                    List<NameValuePair> formParams = new ArrayList<>();
                    ((Map<String,Object>) param).forEach((key,value) -> formParams.add(new BasicNameValuePair(key, value.toString())));
                    UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
                    httpPost.setEntity(uefEntity);
                }
            }

            HttpResponse response = client.execute(httpPost);

            HttpEntity entity = response.getEntity();

            return EntityUtils.toString(entity,"UTF-8");
        }catch (Exception e){
            LOGGER.error("POST error!",e);
        }

        return null;
    }

    /**
     * get 请求
     * @param url
     * @param header
     * @param param
     * @return
     */
    public static String get(String url,Map<String,String> header,Object param){

        if(StringUtils.isBlank(url)){
            return null;
        }

        HttpClient client = HttpClientManager.getHttpClient();
        try {

            if(param != null){
                url = genGetUrl(url,param);
            }

            HttpGet httpGet = new HttpGet(url);

            if(header != null && !header.isEmpty()){
                header.forEach((key, value) -> httpGet.setHeader(new BasicHeader(key, value)));
            }

            HttpResponse response = client.execute(httpGet);

            HttpEntity entity = response.getEntity();

            return EntityUtils.toString(entity,"UTF-8");
        }catch (Exception e){
            LOGGER.error("GET error!",e);
        }

        return null;

    }

    public static String downGet(String url,Map<String,String> header,Object param){
        if(StringUtils.isBlank(url)){
            return null;
        }

        HttpClient client = HttpClientManager.getHttpClient();
        try {

            if(param != null){
                url = genGetUrl(url,param);
            }

            HttpGet httpGet = new HttpGet(url);

            if(header != null && !header.isEmpty()){
                header.forEach((key, value) -> httpGet.setHeader(new BasicHeader(key, value)));
            }

            HttpResponse response = client.execute(httpGet);

            HttpEntity entity = response.getEntity();

            StringBuffer result = new StringBuffer();
            InputStream in = entity.getContent();
            byte[] buffer = new byte[4096];

            int readLength = 0;
            while ((readLength=in.read(buffer)) > 0) {
                byte[] bytes = new byte[readLength];
                System.arraycopy(buffer, 0, bytes, 0, readLength);
                result.append(new String(bytes,"UTF-8"));
            }

            return result.toString();
        }catch (Exception e){
            LOGGER.error("GET error!",e);
        }

        return null;
    }

    /**
     * 拼接url
     *
     * @param url
     * @param param
     * @return
     */
    private static String genGetUrl(String url, Object param) {
        if(param instanceof String){
            url += "?" + param;
        }else if(param instanceof Map){
            url += "?";
            List<String> values = new ArrayList<>();
            ((Map) param).forEach((key, value) -> {
                try {
                    values.add(URLEncoder.encode(key.toString(), "UTF-8") + "=" + URLEncoder.encode(value.toString(),"UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            });
            url += StringUtils.join(values,"&");
        }
        return url;
    }

    /*public static void main(String[] args){
        ExecutorService service = Executors.newFixedThreadPool(128);

        int i = 0;
        while(i ++ < 1000){
            service.submit(() -> LOGGER.info(get("https://www.baidu.com/",null,null)));
        }
        service.shutdown();
    }*/

    public static void main(String args[]){
        Map<String,String> header = new HashMap<>();
        header.put("Cookie","sessionid=xsmwyskl53c66lw25ufjwva2n6xpxtwl");
        String userInfo = downGet("http://www.imxingzhe.com/xing/28504050/gpx/",header,null);
        System.out.println(userInfo);
    }

}
