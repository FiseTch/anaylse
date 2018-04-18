package tch.dao;

import java.util.List;

import tch.model.Paper;

public interface PaperMapper {
    int deleteByPrimaryKey(String id);

    int insert(Paper paper);

    int insertSelective(Paper paper);

    Paper getPaperByPrimaryKey(String id);
    
    List<Paper> getPaperByAttr(Paper paper);

    int updateByPrimaryKeySelective(Paper paper);

    int updateByPrimaryKey(Paper paper);
}