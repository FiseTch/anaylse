package tch.dao;

import tch.model.Subject;

public interface SubjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Subject subject);

    int insertSelective(Subject subject);

    Subject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Subject subject);

    int updateByPrimaryKey(Subject subject);
}