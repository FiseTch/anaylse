package tch.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import tch.dao.PaperDetailMapper;
import tch.model.PaperDetail;
import tch.service.IPaperDetailService;

/**
 * 
 * 
 * Copyright:tch
 * 
 * @class: tch.impl
 * @Description: 
 *
 * @version: v1.0.0
 * @author: tongch
 * @date: 2018-04-19
 * Modification History:
 * date         Author          Version            Description
 *------------------------------------------------------------
 * 2018-04-19     tongch          v1.1.0
 */
@Service
@Scope("prototype")
public class PaperDetailServiceImpl implements IPaperDetailService {

	private static Log log = LogFactory.getLog(PaperDetailServiceImpl.class);
	 
	@Resource
	private PaperDetailMapper paperDetailMapper;
	public PaperDetail getPaperDetailById(String paperid) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return paperDetailMapper.getPaperDetailByPrimaryKey(paperid);
	}

	public List<PaperDetail> getPaperDetailByAttr(PaperDetail paperDetail) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return paperDetailMapper.getPaperDetailByAttr(paperDetail);
	}
	
	/**
	 * 通过属性（只能传递五个）模糊查询
	 */
	public List<PaperDetail> getGeneralPaperDetailByAttr(PaperDetail paperDetail) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return paperDetailMapper.getGeneralPaperDetailByAttr(paperDetail);
	}

	public int insertPaperDetail(PaperDetail paperDetail) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return paperDetailMapper.insert(paperDetail);
	}

	public int insertPaperDetailSelective(PaperDetail paperDetail) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return paperDetailMapper.insertSelective(paperDetail);
	}

	public int updateByIdSelective(PaperDetail paperDetail) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return paperDetailMapper.updateByPrimaryKeySelective(paperDetail);
	}

	public int updateById(PaperDetail paperDetail) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return paperDetailMapper.insert(paperDetail);
	}

	public int deleteById(String paperid) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return paperDetailMapper.deleteByPrimaryKey(paperid);
	}


}
