package tch.dao;

import java.util.List;

import tch.model.PaperDetail;

public interface PaperDetailMapper {
    int deleteByPrimaryKey(String paperid);

    int insert(PaperDetail paperDetail);

    int insertSelective(PaperDetail paperDetail);

    PaperDetail getPaperDetailByPrimaryKey(String paperid);
    
    List<PaperDetail> getPaperDetailByAttr(PaperDetail paperDetail);
    
    List<PaperDetail> getGeneralPaperDetailByAttr(PaperDetail paperDetail);

    int updateByPrimaryKeySelective(PaperDetail paperDetail);

    int updateByPrimaryKey(PaperDetail paperDetail);
}