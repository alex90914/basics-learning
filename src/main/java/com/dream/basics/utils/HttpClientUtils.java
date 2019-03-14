package com.dream.basics.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.basics.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author wb
 * @ClassName: HttpClientUtils
 * @Description: (httpclient发送工具)
 * @date 2018年09月07日 下午15:58:32
 */
public class HttpClientUtils {
    /**
     * 默认的编码,解决中文乱码
     */
    public static String DEFAULT_ENCODE = "UTF-8";
    /**
     * 网络连接超时时间
     */
    private static final int TIMEOUT = 50000;

    /**
     * 发送Post请求
     *
     * @param url      请求路径
     * @param paramMap 参数
     * @return 响应体
     */
    public static String getSendPost(String url, Map<String, String> paramMap) {
        return getSendPost(url, paramMap, DEFAULT_ENCODE);
    }

    /**
     * 发送Post请求
     *
     * @param url      请求路径
     * @param paramMap 参数
     * @return 响应体
     */
    public static String getSendPost(String url, Map<String, String> paramMap, String encode) {
        StringBuffer buf = new StringBuffer();
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        if (paramMap.size() > 0) {
            NameValuePair[] params = new NameValuePair[paramMap.size()];
            Iterator<Entry<String, String>> it = paramMap.entrySet().iterator();
            int i = 0;
            while (it.hasNext()) {
                Entry<String, String> map = it.next();
                params[i] = new NameValuePair(map.getKey(), map.getValue());
                i++;
            }
            postMethod.setQueryString(params); // post请求参数用setQueryString
        }
        sendRequestAndReleaseConnection(client, buf, postMethod, encode);
        return buf.toString();
    }

    public static String post(String url, String paramName, String paramValue) {
        StringBuffer buf = new StringBuffer();
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        NameValuePair param = new NameValuePair(paramName, paramValue);
        postMethod.addParameter(param);
        sendRequestAndReleaseConnection(client, buf, postMethod, DEFAULT_ENCODE);
        return buf.toString();
    }

    /**
     * json参数请求,请求方可以直接直接使用实体类接收
     *
     * @param url        请求路径
     * @param jsonParams json参数
     * @return 服务端响应结果
     */
    public static Result<String> post(String url, String jsonParams, Map<String, String> headers) {
        StringBuffer buf = new StringBuffer();
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        postMethod.setRequestEntity(new StringRequestEntity(jsonParams));
        postMethod.addRequestHeader("Content-type", "application/json");
        headers.forEach((key, value) -> postMethod.addRequestHeader(key, value));
        sendRequestAndReleaseConnection(client, buf, postMethod, DEFAULT_ENCODE);
        String remote = buf.toString();
        if (StringUtils.isBlank(remote)) {
            return new Result<>(remote, 500, "失败");
        }
        return new Result<>(remote, 200, "成功");
    }

    /**
     * 发送http网络请求,并关闭连接
     *
     * @param client     http连接
     * @param buf
     * @param postMethod
     * @param encode
     */
    private static void sendRequestAndReleaseConnection(HttpClient client, StringBuffer buf, PostMethod postMethod, String encode) {
        try {
            HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams();
            managerParams.setConnectionTimeout(TIMEOUT);
            managerParams.setSoTimeout(TIMEOUT);
            client.executeMethod(postMethod);
            byte[] responseBody = postMethod.getResponseBody();
            String content = new String(responseBody, encode);
            buf.append(content);
        } catch (Exception e) {
            System.out.println("网络请求发起失败");
        } finally {
            postMethod.releaseConnection();
        }
    }

    public static void main(String[] args) {
        String url = "http://wo-api.uni-ubi.com/v1/BB2A533D86684563AAA1B06949C84C5E/auth";
        JSONObject params = new JSONObject();
        params.put("appId", "BB2A533D86684563AAA1B06949C84C5E");
        Map<String, String> headers = new HashMap<>();
        long timestamp = System.currentTimeMillis();
        String appKey = "8DD389FB28B94A598527F51943A02B68";
        String appSecret = "A269911BF87D40B78EE596FAE2069670";
        headers.put("appKey", "");
        headers.put("timestamp", "timestamp");
        headers.put("sign", DigestUtils.md5Hex(appKey + timestamp + appSecret));
        Result<String> post = post(url, params.toJSONString(), headers);
        System.out.println(JSON.toJSONString(post));
        /**
         *
         * Content-Type: application/json
         * appKey: 8DD389FB28B94A598527F51943A02B68
         * timestamp: 1552297014727
         * sign: 9b97ce269c41a82705af2b16fa9f1700
         *
         * {
         *   "appId": "BB2A533D86684563AAA1B06949C84C5E"
         * }
         * <> 2019-03-12T094319.200.json
         * <> 2019-03-12T094229.200.json
         * <> 2019-03-11T054137.200.json
         * <> 2019-03-11T054117.200.json
         * <> 2019-03-11T054019.200.json
         * <> 2019-03-11T053935.200.json
         *
         *
         *
         * #######################
         * #AppKey：8DD389FB28B94A598527F51943A02B68
         * #AppSecret：A269911BF87D40B78EE596FAE2069670
         * #appKey	String	N		应用Key，创建应用时生成，可在应用详情中查看
         * #timestamp	String	N		Unix毫秒时间戳
         * #sign	String	N		MD5-32(appKey+timestamp+appSecret)，MD5加密，32位小写
         */
    }


}
