package me.hibiki.galayun.service.impl;

import me.hibiki.galayun.domain.Objective;
import me.hibiki.galayun.mapper.ObjectiveMapper;
import me.hibiki.galayun.service.ObjectiveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 高弘昆
 * @date 2020/3/26 23:29
 */
@Service("objectiveService")
public class ObjectiveServiceImpl implements ObjectiveService {

    @Resource
    private ObjectiveMapper objectiveMapper;

    @Override
    public int insert(Objective objective, Integer chapterId) {
        return objectiveMapper.insert(objective, chapterId);
    }

    @Override
    public int insertList(List<Objective> objectives, int chapterId) {
        return objectiveMapper.insertList(objectives,chapterId);
    }

    @Override
    public List<Objective> listByChapterIdObjectives(Integer chapterId) {
        return objectiveMapper.listByChapterIdObjectives(chapterId);
    }

    @Override
    public List<Objective> listBySearchObjectives(String keyword) {
        return objectiveMapper.listBySearchObjectives(keyword);
    }

}
