package tch.dao;

import java.util.List;

import tch.model.Teacher;

public interface TeacherMapper {
    int deleteByPrimaryKey(String id);

    int insert(Teacher record);

    int insertSelective(Teacher record);
    
    List<String> getAllTeacId();
    
    Teacher getTeacByAttr(Teacher teacher);

    Teacher getTeacByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
}