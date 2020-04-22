package me.hibiki.galayun.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.hibiki.galayun.domain.WorkExtend;
import me.hibiki.galayun.service.WorkService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

/**
 * @author 高弘昆
 * @date 2020/4/16 10:10
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class WorkTest {
    @Resource
    private WorkService workService;
    @Test
    public void testWork(){
        WorkExtend workExtend = new WorkExtend();
        workExtend.setWorks(workService.listBySubjectIdWorks(251));
        workExtend.setCount(workService.countBySubjectId(251));
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = gsonBuilder.create();
        String json = gson.toJson(workExtend);
        System.out.println(json);
    }
}
