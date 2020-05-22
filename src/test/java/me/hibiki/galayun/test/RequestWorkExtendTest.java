package me.hibiki.galayun.test;


import com.google.gson.Gson;
import me.hibiki.galayun.domain.Work;
import me.hibiki.galayun.domain.WorkExtend;
import me.hibiki.galayun.service.WorkService;
import me.hibiki.galayun.util.HttpRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author 高弘昆
 * @date 2020/3/26 20:48
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class RequestWorkExtendTest {
    @Resource
    private WorkService workService;
    @Resource
    private HttpRequest httpRequest;

    /**
     * 获取作业列表json添加作业
     *
     * @param classId   班级id
     * @param subjectId 科目id
     */
    public void insertWork(Integer classId, Integer subjectId) {
        String json = httpRequest.requestWorkExtendJson(classId, subjectId);
        Gson gson = new Gson();
        
        WorkExtend workExtend = gson.fromJson(json, WorkExtend.class);
        List<Work> works = workExtend.getWorks();
        for (Work work : works) {
            work.setChapterName(work.getChapterName().trim());
            workService.insert(work, subjectId);
        }
    }

    @Test
    public void testInsertWork(){
        insertWork(151,252);
    }

    /**
     * 获取作业列表json添加作业
     *
     * @param classId   班级id
     * @param subjectId 科目id
     */
    public void insertWorkList(Integer classId, Integer subjectId) {
        String json = httpRequest.requestWorkExtendJson(classId, subjectId);
        Gson gson = new Gson();
        WorkExtend workExtend = gson.fromJson(json, WorkExtend.class);
        List<Work> works = workExtend.getWorks();
        int i = workService.insertList(works, subjectId);
        System.out.println("添加了" + i + "条作业记录");
    }
}
