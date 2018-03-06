package tch.dao;

import tch.model.DegreeStatic;

public interface DegreeStaticMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DegreeStatic record);

    int insertSelective(DegreeStatic record);

    DegreeStatic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DegreeStatic record);

    int updateByPrimaryKey(DegreeStatic record);
}