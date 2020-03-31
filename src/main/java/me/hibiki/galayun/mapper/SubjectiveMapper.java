package me.hibiki.galayun.mapper;

import me.hibiki.galayun.domain.Subjective;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubjectiveMapper {
    int insert(@Param("subjective") Subjective subjective, @Param("chapterId") Integer chapterId);

    int insertList(@Param("subjectives") List<Subjective> subjectives, @Param("chapterId") int chapterId);

    List<Subjective> listByChapterIdSubjectives(Integer chapterId);

    List<Subjective> listBySearchSubjectives(String keyword);

}