package me.hibiki.galayun.service;

import me.hibiki.galayun.domain.Objective;

import java.util.List;

/**
 * @author 高弘昆
 * @date 2020/3/26 23:29
 */
public interface ObjectiveService {
    int insert(Objective record, Integer chapterId);

    List<Objective> listByChapterIdObjectives(Integer chapterId);

    List<Objective> listBySearchObjectives(String keyword);


}
