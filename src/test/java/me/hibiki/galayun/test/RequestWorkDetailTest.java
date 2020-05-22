package me.hibiki.galayun.test;

import com.google.gson.Gson;
import me.hibiki.galayun.domain.Objective;
import me.hibiki.galayun.domain.Subjective;
import me.hibiki.galayun.domain.WorkDetail;
import me.hibiki.galayun.service.ObjectiveService;
import me.hibiki.galayun.service.SubjectiveService;
import me.hibiki.galayun.util.HttpRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 高弘昆
 * @date 2020/3/26 22:32
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class RequestWorkDetailTest {
    @Resource
    private HttpRequest httpRequest;
    @Resource
    private ObjectiveService objectiveService;
    @Resource
    private SubjectiveService subjectiveService;
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
        for (int i = start; i <= end; i++) {
            System.out.println("当前在第" + i + "章节");
            String json = httpRequest.requestWorkDetailJson(i);
            Gson gson = new Gson();

            WorkDetail workDetail = gson.fromJson(json, WorkDetail.class);
            System.out.println(workDetail);
            //添加客观题
            List<Objective> objectives = workDetail.getObjectives();
            System.out.println("客观题：" + objectives);
            if (i <= objectiveEnd) {
                int o = objectiveService.insertList(objectives, i);
                System.out.println("添加了" + o + "条客观题");
            }
            //添加主观题
            List<Subjective> subjectives = workDetail.getSubjectives();
            System.out.println("主观题" + subjectives);
            int s = subjectiveService.insertList(subjectives, i);
            System.out.println("添加了" + s + "条主观题");
        }
    }
    @Test
    public void testInsertWorkDetail(){
        insertWorkDetail(2925,2926,2926);
    }
}
