package me.hibiki.galayun.util;

import com.google.gson.Gson;
import me.hibiki.galayun.domain.Work;
import me.hibiki.galayun.domain.WorkExtend;
import me.hibiki.galayun.mapper.WorkMapper;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * @author 高弘昆
 * @Date 2020/3/3 20:58
 */
public class RequestWork {
    private String json;
    private String url = "https://www.galayun.com/Admin/AssignWork/list";
    private String cookie = "";
    private String userArgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36";

    /**
     * @param classId   班级编号
     * @param subjectId 科目编号
     * @return
     */
    public String getJson(int classId, int subjectId) {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 参数
        StringBuffer params = new StringBuffer();
        //url参数 ClassId=151&SubjectId=253&pageNumber=1&page=1&rows=10
        // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
        params.append("ClassId=" + classId);
        params.append("&SubjectId=" + subjectId);
        params.append("&page=" + 1);
        params.append("&rows=" + 200);
        // 创建Post请求
        HttpPost httpPost = new HttpPost(url + "?" + params);
        httpPost.setHeader("Cookie", cookie);
        // 设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        //设置用户代理
        httpPost.setHeader("User-Argent", userArgent);

        // 响应模型
        CloseableHttpResponse response = null;
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
     * 获取作业列表json添加作业
     *
     * @param classId   班级id
     * @param subjectId 科目id
     */
    public void insertWork(Integer classId, Integer subjectId) {
        SqlSession sqlSession = DatabaseHelper.getSqlSessionFactory().openSession();
        WorkMapper workMapper = sqlSession.getMapper(WorkMapper.class);

        String json = this.getJson(classId, subjectId);
        System.out.println(json);

        Gson gson = new Gson();
        WorkExtend workExtend = gson.fromJson(json, WorkExtend.class);
        System.out.println(workExtend);

        List<Work> works = workExtend.getWorks();
        Iterator<Work> iterator = works.iterator();
        while (iterator.hasNext()) {
            Work work = iterator.next();
            work.setChapterName(work.getChapterName().trim());
            workMapper.insert(work, subjectId);
        }
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 获取作业列表json添加作业
     *
     * @param classId   班级id
     * @param subjectId 科目id
     */
    public void insertWorkList(Integer classId, Integer subjectId) {
        SqlSession sqlSession = DatabaseHelper.getSqlSessionFactory().openSession();
        WorkMapper workMapper = sqlSession.getMapper(WorkMapper.class);

        String json = this.getJson(classId, subjectId);
        System.out.println(json);

        Gson gson = new Gson();
        WorkExtend workExtend = gson.fromJson(json, WorkExtend.class);
        System.out.println(workExtend);

        List<Work> works = workExtend.getWorks();
        int i = workMapper.insertList(works, subjectId);
        System.out.println("添加了" + i + "条作业记录");
        sqlSession.commit();
        sqlSession.close();
    }
}
