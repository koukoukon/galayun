package me.hibiki.galayun.service.impl;

import me.hibiki.galayun.domain.Work;
import me.hibiki.galayun.mapper.WorkMapper;
import me.hibiki.galayun.service.WorkService;
import me.hibiki.galayun.util.DatabaseHelper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * 接口实现
 *
 * @author 高弘昆
 * @date 2020/3/26 22:15
 */

//@Service
public class WorkServiceImpl implements WorkService {

    //    @Resource
    //    private WorkMapper workMapper;
    private SqlSessionFactory sqlSessionFactory = DatabaseHelper.getSqlSessionFactory();
    private WorkMapper workMapper;

    @Override
    public int insert(Work record, Integer subjectId) {
        return workMapper.insert(record, subjectId);
    }

    @Override
    public List<Work> listBySubjectIdWorks(Integer subjectId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        workMapper = sqlSession.getMapper(WorkMapper.class);
        List<Work> works = workMapper.listBySubjectIdWorks(subjectId);
        sqlSession.close();
        return works;
    }

    @Override
    public int countBySubjectId(Integer subjectId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        workMapper = sqlSession.getMapper(WorkMapper.class);
        int count = workMapper.countBySubjectId(subjectId);
        sqlSession.close();
        return count;
    }
}
