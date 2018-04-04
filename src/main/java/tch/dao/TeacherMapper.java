package tch.dao;

import tch.model.Teacher;

public interface TeacherMapper {
    int deleteByPrimaryKey(String id);

    int insert(Teacher teacher);

    int insertSelective(Teacher teacher);

    Teacher selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Teacher teacher);

    int updateByPrimaryKey(Teacher teacher);
}