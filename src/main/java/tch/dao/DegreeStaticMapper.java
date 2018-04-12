package tch.dao;

import tch.model.DegreeStatic;

public interface DegreeStaticMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DegreeStatic degreeStatic);

    int insertSelective(DegreeStatic degreeStatic);

    DegreeStatic getDegByPrimaryKey(Integer id);
    
    DegreeStatic getDegByAttr(DegreeStatic degreeStatic);

    int updateByPrimaryKeySelective(DegreeStatic degreeStatic);

    int updateByPrimaryKey(DegreeStatic degreeStatic);
}