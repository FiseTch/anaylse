package tch.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tch.dao.PaperMapper;
import tch.model.Paper;


@Service("paperService")
public class PaperServiceImpl implements PaperMapper {

	
	@Autowired
	private PaperMapper paperMapper;
	
	public PaperMapper getPaperMapper() {
		return paperMapper;
	}

	public void setPaperMapper(PaperMapper paperMapper) {
		this.paperMapper = paperMapper;
	}

	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Paper record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(Paper record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Paper selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(Paper record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Paper record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
