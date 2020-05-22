package me.hibiki.galayun.service.impl;

import me.hibiki.galayun.domain.Subjective;
import me.hibiki.galayun.mapper.SubjectiveMapper;
import me.hibiki.galayun.service.SubjectiveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 高弘昆
 * @date 2020/3/26 23:29
 */
@Service("subjectiveService")
public class SubjectiveServiceImpl implements SubjectiveService {
    @Resource
    private SubjectiveMapper subjectiveMapper;

    @Override
    public int insert(Subjective subjective, Integer chapterId) {
        return subjectiveMapper.insert(subjective, chapterId);
    }

    @Override
    public int insertList(List<Subjective> subjectives, int chapterId) {
        return subjectiveMapper.insertList(subjectives,chapterId);
    }

    @Override
    public List<Subjective> listByChapterIdSubjectives(Integer chapterId) {
        return subjectiveMapper.listByChapterIdSubjectives(chapterId);
    }

    @Override
    public List<Subjective> listBySearchSubjectives(String keyword) {
        return subjectiveMapper.listBySearchSubjectives(keyword);
    }

}
