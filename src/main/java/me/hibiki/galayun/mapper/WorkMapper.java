package me.hibiki.galayun.mapper;

import me.hibiki.galayun.domain.Work;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkMapper {
    /**
     * @param work
     * @param subjectId
     * @return
     */
    int insert(@Param("work") Work work, @Param("subjectId") Integer subjectId);

    /**
     * 添加作业集合
     *
     * @param works
     * @param subjectId
     * @return
     */
    int insertList(@Param("works") List<Work> works, @Param("subjectId") Integer subjectId);

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