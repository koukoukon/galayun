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
     * @param subjectId 科目编号
     * @return 作业列表
     */
    List<Work> listBySubjectIdWorks(Integer subjectId);

    /**
     * 统计某一科目下的作业总次数
     *
     * @param subjectId 科目编号
     * @return 总次数
     */
    int countBySubjectId(Integer subjectId);


}
