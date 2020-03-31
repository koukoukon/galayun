package me.hibiki.galayun.util;

import com.google.gson.Gson;
import me.hibiki.galayun.domain.Objective;
import me.hibiki.galayun.domain.Subjective;
import me.hibiki.galayun.domain.WorkDetail;
import me.hibiki.galayun.mapper.ObjectiveMapper;
import me.hibiki.galayun.mapper.SubjectiveMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

/**
 * @author 高弘昆
 * @Date 2020/3/3 23:39
 */
public class RequestWorkDetail {
    private String json;
    private String url = "http://www.galayun.com/Admin/AssignWork/AnswerDetail";
    private String cookie = "";
    private String userArgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36";

    /**
     * 获取作业详情
     *
     * @param chapterId 章节id
     * @return
     */
    public String getJson(int chapterId) {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url + "?" + "ChapterId=" + chapterId);
        httpPost.setHeader("Cookie", cookie);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        httpPost.setHeader("User-Argent", userArgent);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (response.getStatusLine().getStatusCode() == 200) {
                if (responseEntity != null) {
                    json = EntityUtils.toString(responseEntity, "UTF-8");
                    System.out.println("响应内容为:" + json);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
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
     * 获取作业详细记录并添加
     *
     * @param start        开始章节id
     * @param end          结束章节id
     * @param objectiveEnd 客观题章节结束id,有的章节没有客观题所以需要进行判断一下
     */
    public void insertWorkDetail(int start, int end, Integer objectiveEnd) {
        if (objectiveEnd == null) {
            System.out.println("客观题章节结束id不能为null");
            return;
        }
        SqlSession sqlSession = DatabaseHelper.getSqlSessionFactory().openSession();
        ObjectiveMapper objectiveMapper = sqlSession.getMapper(ObjectiveMapper.class);
        SubjectiveMapper subjectiveMapper = sqlSession.getMapper(SubjectiveMapper.class);

        for (int i = start; i <= end; i++) {
            System.out.println("当前在第" + i + "章节");
            String json = this.getJson(i);
            Gson gson = new Gson();

            WorkDetail workDetail = gson.fromJson(json, WorkDetail.class);
            System.out.println(workDetail);
            //添加客观题
            List<Objective> objectives = workDetail.getObjectives();
            System.out.println("客观题：" + objectives);
            if (i <= objectiveEnd) {
                int o = objectiveMapper.insertList(objectives, i);
                System.out.println("添加了" + o + "条客观题");
            }
            //添加主观题
            List<Subjective> subjectives = workDetail.getSubjectives();
            System.out.println("主观题" + subjectives);
            int s = subjectiveMapper.insertList(subjectives, i);
            System.out.println("添加了" + s + "条主观题");
        }
        sqlSession.commit();
        sqlSession.close();
    }
}
