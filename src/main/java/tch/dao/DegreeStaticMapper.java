package tch.dao;

import tch.model.DegreeStatic;

public interface DegreeStaticMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DegreeStatic degreeStatic);

    int insertSelective(DegreeStatic degreeStatic);

    DegreeStatic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DegreeStatic degreeStatic);

    int updateByPrimaryKey(DegreeStatic degreeStatic);
}