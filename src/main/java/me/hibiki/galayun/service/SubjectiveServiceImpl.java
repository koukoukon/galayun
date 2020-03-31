package me.hibiki.galayun.service;

import me.hibiki.galayun.domain.Subjective;
import me.hibiki.galayun.mapper.SubjectiveMapper;
import me.hibiki.galayun.util.DatabaseHelper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author 高弘昆
 * @date 2020/3/26 23:29
 */
//@Service
public class SubjectiveServiceImpl implements SubjectiveService {

    //    @Resource
    private SqlSessionFactory sqlSessionFactory = DatabaseHelper.getSqlSessionFactory();
    private SubjectiveMapper subjectiveMapper;

    @Override
    public int insert(Subjective record, Integer chapterId) {
        return subjectiveMapper.insert(record, chapterId);
    }

    @Override
    public List<Subjective> listByChapterIdSubjectives(Integer chapterId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        subjectiveMapper = sqlSession.getMapper(SubjectiveMapper.class);
        List<Subjective> subjectives = subjectiveMapper.listByChapterIdSubjectives(chapterId);
        sqlSession.close();
        return subjectives;
    }


    @Override
    public List<Subjective> listBySearchSubjectives(String keyword) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SubjectiveMapper subjectiveMapper = sqlSession.getMapper(SubjectiveMapper.class);
        List<Subjective> subjectives = subjectiveMapper.listBySearchSubjectives(keyword);
        sqlSession.close();
        return subjectives;
    }

}
