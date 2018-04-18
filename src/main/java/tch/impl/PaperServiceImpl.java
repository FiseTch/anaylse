package tch.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import tch.dao.PaperMapper;
import tch.model.Paper;
import tch.service.IPaperService;

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
 * @date: 2018-04-05
 * Modification History:
 * date         Author          Version            Description
 *------------------------------------------------------------
 * 2018-04-05     tongch          v1.1.0
 */
@Service("paperService")
@Scope("prototype")
public class PaperServiceImpl implements IPaperService {

	private Log log = LogFactory.getLog(UserServiceImpl.class);
	
	@Resource
	private PaperMapper paperMapper;

	/**
	 * 通过主键id删除记录
	 */
	public int deleteById(String id) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return paperMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 插入记录（属性不允许为空）
	 */
	public int insertPaper(Paper paper) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return paperMapper.insert(paper);
	}

	/**
	 * 插入记录（除主键外，其余的属性允许为空）
	 */
	public int insertPaperSelective(Paper paper) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return paperMapper.insertSelective(paper);
	}

	/**
	 * 通过id查询记录
	 */
	public Paper getPaperById(String id) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return paperMapper.getPaperByPrimaryKey(id);
	}

	/**
	 * 根据主键id更新记录（其余属性允许为空）
	 */
	public int updateByIdSelective(Paper paper) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return paperMapper.updateByPrimaryKeySelective(paper);
	}

	/**
	 * 根据主键id更新记录（属性不允许为空）
	 */
	public int updateById(Paper paper) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return paperMapper.updateByPrimaryKey(paper);
	}

	/**
	 * 根据属性值查记录
	 */
	public List<Paper> getPaperByAttr(Paper paper) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return paperMapper.getPaperByAttr(paper);
	}


}
