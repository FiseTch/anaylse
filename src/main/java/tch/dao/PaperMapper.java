package tch.dao;

import tch.model.Paper;

public interface PaperMapper {
    int deleteByPrimaryKey(String id);

    int insert(Paper paper);

    int insertSelective(Paper paper);

    Paper selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Paper paper);

    int updateByPrimaryKey(Paper paper);
}