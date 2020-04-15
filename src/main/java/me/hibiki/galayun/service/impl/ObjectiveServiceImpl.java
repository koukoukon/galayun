package me.hibiki.galayun.service.impl;

import me.hibiki.galayun.domain.Objective;
import me.hibiki.galayun.mapper.ObjectiveMapper;
import me.hibiki.galayun.service.ObjectiveService;
import me.hibiki.galayun.util.DatabaseHelper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author 高弘昆
 * @date 2020/3/26 23:29
 */
//@Service
public class ObjectiveServiceImpl implements ObjectiveService {

    //    @Resource
    private SqlSessionFactory sqlSessionFactory = DatabaseHelper.getSqlSessionFactory();
    private ObjectiveMapper objectiveMapper;

    @Override
    public int insert(Objective record, Integer chapterId) {
        return objectiveMapper.insert(record, chapterId);
    }

    @Override
    public List<Objective> listByChapterIdObjectives(Integer chapterId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        objectiveMapper = sqlSession.getMapper(ObjectiveMapper.class);
        List<Objective> objectives = objectiveMapper.listByChapterIdObjectives(chapterId);
        sqlSession.close();
        return objectives;
    }


    @Override
    public List<Objective> listBySearchObjectives(String keyword) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ObjectiveMapper objectiveMapper = sqlSession.getMapper(ObjectiveMapper.class);
        List<Objective> objectives = objectiveMapper.listBySearchObjectives(keyword);
        sqlSession.close();
        return objectives;
    }

}
