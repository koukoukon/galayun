package me.hibiki.galayun.service;

import me.hibiki.galayun.domain.Subjective;

import java.util.List;

/**
 * @author 高弘昆
 * @date 2020/3/26 23:29
 */
public interface SubjectiveService {

    int insert(Subjective record, Integer chapterId);

    List<Subjective> listByChapterIdSubjectives(Integer chapterId);

    List<Subjective> listBySearchSubjectives(String keyword);

}
