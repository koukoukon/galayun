package me.hibiki.galayun.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.hibiki.galayun.domain.*;
import me.hibiki.galayun.service.ObjectiveService;
import me.hibiki.galayun.service.SubjectiveService;
import me.hibiki.galayun.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "WorkServlet", urlPatterns = "/work")
public class WorkManager extends BaseServlet {
    @Autowired
    private WorkService workService;
    @Autowired
    private ObjectiveService objectiveService;
    @Autowired
    private SubjectiveService subjectiveService;


    /**
     * 获取单一科目下的作业列表
     *
     * @param request 请求
     * @param response 响应
     * @throws IOException 异常
     */
    public void getWorkList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        Integer subjectId = Integer.valueOf(request.getParameter("subjectId"));
        List<Work> works = workService.listBySubjectIdWorks(subjectId);
        int count = workService.countBySubjectId(subjectId);
        WorkExtend workExtend = new WorkExtend();
        workExtend.setWorks(works);
        workExtend.setCount(count);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = gsonBuilder.create();
        String json = gson.toJson(workExtend);
        try (PrintWriter writer = response.getWriter()) {
            writer.print(json);
            writer.flush();
        }
    }

    /**
     * 获取作业详情
     *
     * @param request 请求
     * @param response 相应
     * @throws IOException 异常
     */
    public void getWorkDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        Integer chapterId = Integer.valueOf(request.getParameter("chapterId"));
        List<Objective> objectives = objectiveService.listByChapterIdObjectives(chapterId);
        List<Subjective> subjectives = subjectiveService.listByChapterIdSubjectives(chapterId);
        WorkDetail workDetail = new WorkDetail();
        workDetail.setObjectives(objectives);
        workDetail.setSubjectives(subjectives);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = gsonBuilder.create();
        String json = gson.toJson(workDetail);
        try (PrintWriter writer = response.getWriter()) {
            writer.print(json);
            writer.flush();
        }
    }

    /**
     * 搜索作业详情
     */
    public void searchWorkDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        String keyword = request.getParameter("keyword");
        List<Objective> objectives = objectiveService.listBySearchObjectives(keyword);
        List<Subjective> subjectives = subjectiveService.listBySearchSubjectives(keyword);
        WorkDetail workDetail = new WorkDetail();
        workDetail.setObjectives(objectives);
        workDetail.setSubjectives(subjectives);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = gsonBuilder.create();
        String json = gson.toJson(workDetail);
        try (PrintWriter writer = response.getWriter()) {
            writer.print(json);
            writer.flush();
        }
    }
}
