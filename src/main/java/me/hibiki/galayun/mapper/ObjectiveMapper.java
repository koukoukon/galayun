package me.hibiki.galayun.mapper;

import me.hibiki.galayun.domain.Objective;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ObjectiveMapper {
    int insert(@Param("objective") Objective objective, @Param("chapterId") Integer chapterId);

    /**
     * 添加多个客观题
     *
     * @param objectives 客观题集合
     * @param chapterId  章节id
     * @return
     */
    int insertList(@Param("objectives") List<Objective> objectives, @Param("chapterId") int chapterId);

    List<Objective> listByChapterIdObjectives(Integer chapterId);

    List<Objective> listObjectives();

    List<Objective> listBySearchObjectives(String keyword);

}