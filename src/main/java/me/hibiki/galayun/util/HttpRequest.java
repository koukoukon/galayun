package me.hibiki.galayun.util;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 高弘昆
 * @date 2020/4/22 17:00
 */
@Component
public class HttpRequest {
    private String contentType = "application/x-www-form-urlencoded; charset=UTF-8";
    private String cookie = "ASP.NET_SessionId=etxjzatvf1obj5xdwefdj1fo; Himall-EmployeeManager=c04veXhHWE5aNUpmUWM5a1hmNTRQcFJiV1htY3czaW5EQmJsTVVKVjFMSVJtL25JYlcrOU9rV3p0UG5hMDNwbUtLK3pFTlB3V1pBN2ZjQkxsVENOaHc9PQ==; Himall-PlatformManager=c04veXhHWE5aNUpmUWM5a1hmNTRQcFJiV1htY3czaW5EQmJsTVVKVjFMTFJxWUsyM1JOVVQwM2UyVnFzY3lCZmkxYzJ5R1Vrckx1MmNhS1diWUl5aUE9PQ==";
    private String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.113 Safari/537.36";

    public String request(String url, StringBuilder parameters) {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Post请求
        HttpPost httpPost = new HttpPost(url + "?" + parameters);
        //设置cookie
        httpPost.setHeader("Cookie", cookie);
        // 设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
        httpPost.setHeader("Content-Type", contentType);
        //设置用户代理
        httpPost.setHeader("User-Agent", userAgent);
        // 响应模型
        CloseableHttpResponse response = null;
        String json = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (response.getStatusLine().getStatusCode() == 200) {
                if (responseEntity != null) {
                    json = EntityUtils.toString(responseEntity, "UTF-8");
                    System.out.println("响应内容为:" + json);
                }
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return json;
    }

    /**
     * 请求作业列表json
     *
     * @param classId   班级编号
     * @param subjectId 科目编号
     * @return json
     */
    public String requestWorkExtendJson(int classId, int subjectId) {
        String url = "https://www.galayun.com/Admin/AssignWork/list";
        //url参数 ClassId=151&SubjectId=253&pageNumber=1&page=1&rows=10
        StringBuilder parameters = new StringBuilder();
        parameters.append("ClassId=" + classId);
        parameters.append("&SubjectId=" + subjectId);
        parameters.append("&page=" + 1);
        parameters.append("&rows=" + 200);
        return request(url, parameters);
    }

    /**
     * 获取作业详情
     *
     * @param chapterId 章节id
     * @return json
     */
    public String requestWorkDetailJson(int chapterId) {
        String url = "https://www.galayun.com/Admin/AssignWork/AnswerDetail";
        StringBuilder parameters = new StringBuilder();
        parameters.append("ChapterId=" + chapterId);
        return request(url, parameters);
    }

}
