package me.hibiki.galayun.service;

import me.hibiki.galayun.domain.Work;

import java.util.List;

/**
 * 接口
 *
 * @author 高弘昆
 * @date 2020/3/26 22:15
 */
public interface WorkService {

    int insert(Work work, Integer subjectId);

    int insertList(List<Work> works,Integer subjectId);

    /**
     * 获取某一科目下的作业记录集合
     *
     * @param subjectId
     * @return
     */
    List<Work> listBySubjectIdWorks(Integer subjectId);

    /**
     * 统计某一科目下的作业总次数
     *
     * @param subjectId
     * @return
     */
    int countBySubjectId(Integer subjectId);


}
