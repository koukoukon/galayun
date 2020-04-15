package me.hibiki.galayun.service.impl;

import me.hibiki.galayun.domain.Work;
import me.hibiki.galayun.mapper.WorkMapper;
import me.hibiki.galayun.service.WorkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 接口实现
 *
 * @author 高弘昆
 * @date 2020/3/26 22:15
 */

@Service("workService")
public class WorkServiceImpl implements WorkService {

    @Resource
    private WorkMapper workMapper;


    @Override
    public int insert(Work record, Integer subjectId) {
        return workMapper.insert(record, subjectId);
    }

    @Override
    public List<Work> listBySubjectIdWorks(Integer subjectId) {
        return workMapper.listBySubjectIdWorks(subjectId);
    }

    @Override
    public int countBySubjectId(Integer subjectId) {
        return workMapper.countBySubjectId(subjectId);
    }
}
