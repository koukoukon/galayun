package me.hibiki.galayun.test;


import me.hibiki.galayun.domain.Objective;
import me.hibiki.galayun.mapper.ObjectiveMapper;
import me.hibiki.galayun.util.DatabaseHelper;
import me.hibiki.galayun.util.RequestWork;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

import java.util.List;


/**
 * @author 高弘昆
 * @date 2020/3/26 20:48
 */
public class RequestWorkTest {
    @Test
    public void testInsertWork() {
        RequestWork requestWork = new RequestWork();
        requestWork.insertWork(151, 238);
    }

    @Test
    public void testSearch() {
        SqlSessionFactory sqlSessionFactory = DatabaseHelper.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ObjectiveMapper objectiveMapper = sqlSession.getMapper(ObjectiveMapper.class);
        List<Objective> mybatis = objectiveMapper.listBySearchObjectives("mybatis");
        System.out.println(mybatis);
        sqlSession.close();
    }

}
