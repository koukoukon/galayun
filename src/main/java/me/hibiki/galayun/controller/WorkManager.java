package me.hibiki.galayun.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.hibiki.galayun.domain.*;
import me.hibiki.galayun.service.impl.ObjectiveServiceImpl;
import me.hibiki.galayun.service.impl.SubjectiveServiceImpl;
import me.hibiki.galayun.service.WorkService;
import me.hibiki.galayun.service.impl.WorkServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "WorkListServlet", urlPatterns = "/work")
public class WorkManager extends BaseServlet {
    /**
     * 获取单一科目下的作业列表
     *
     * @param request
     * @param response
     */
    public void getWorkList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        Integer subjectId = Integer.valueOf(request.getParameter("subjectId"));
        WorkService workService = new WorkServiceImpl();
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
     * @param request
     * @param response
     */
    public void getWorkDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        Integer chapterId = Integer.valueOf(request.getParameter("chapterId"));
        List<Objective> objectives = new ObjectiveServiceImpl().listByChapterIdObjectives(chapterId);
        List<Subjective> subjectives = new SubjectiveServiceImpl().listByChapterIdSubjectives(chapterId);
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
        System.out.println(keyword);
        ObjectiveServiceImpl objectiveService = new ObjectiveServiceImpl();
        List<Objective> objectives = objectiveService.listBySearchObjectives(keyword);
        List<Subjective> subjectives = new SubjectiveServiceImpl().listBySearchSubjectives(keyword);
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
