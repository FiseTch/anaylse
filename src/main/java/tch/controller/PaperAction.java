package tch.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import tch.model.Paper;
import tch.model.PaperDetail;
import tch.model.PaperDetailToString;
import tch.model.ReviewResult;
import tch.service.IPaperDetailService;
import tch.service.IPaperService;
import tch.service.IReviewResultService;
import tch.util.ExcelUpUtil;
import tch.util.MyCommonUtil;


/**
 * 
 * 
 * Copyright:tch
 * 
 * @class: tch.controller
 * @Description: 获取上转来的excel文件
 *
 * @version: v1.0.0
 * @author: tongch
 * @date: 2018-03-03
 * Modification History:
 * date         Author          Version            Description
 *------------------------------------------------------------
 * 2018-03-03     tongch          v1.1.0
 */
@Controller
@RequestMapping(value = "/paper")//访问路径
public class PaperAction {
	
	private static Log log = LogFactory.getLog(PaperAction.class);
	
	@Resource
	private IPaperService paperService;
	
	@Resource
	private IPaperDetailService paperDetailService;
	
	@Resource
	private IReviewResultService reviewResultService;
/*	private static int totalRows; //sheet中总行数  
	private static int totalCells; //每一行总单元格数  
*/	
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: upExcel
	 * @Description: TODO
	 * @param subject  课程名称
	 * @param score   试卷总分
	 * @param subjectPerson  出题人
	 * @param teacher  试卷审核人
	 * @param time   考试日期（yyyy-MM-dd）
	 * @param paperTime  考试用时（分钟）
	 * @param term  课程开设学期
	 * @param num   考试人数
	 * @param file
	 * @param session
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/upPaper", method = RequestMethod.POST)
	public ModelAndView  upExcel(@RequestParam("subject")String subject,@RequestParam("score")String score,@RequestParam("subjectPerson")String subjectPerson,
			@RequestParam("teacher")String teacher,@RequestParam("time")String time,@RequestParam("paperTime")String paperTime,
			@RequestParam("term")String term,@RequestParam("num")String num,@RequestParam("file")MultipartFile file,HttpSession session){
		ModelAndView model = new ModelAndView();
		Paper paper = new Paper();
		PaperDetail paperDetail = new PaperDetail();
		if (!file.isEmpty()) {			
	        String strToday =  MyCommonUtil.getDateFormat(new Date());// 获取当前时间
			String foldPath = ExcelUpUtil.ROOT_CATALOG + File.separator + strToday;//文件上传的目标文件夹		
			File myFolderPath = new File(foldPath);  //新建文件夹  
			try {
				if (!myFolderPath.exists()) {   
					myFolderPath.mkdir();   
				}   
				byte[] bytes = file.getBytes();// 获取上传数据
				/*这样只是改变了文件名无法产生目录及在目录内生成文件
				String filePath = foldPath + file.getOriginalFilename();
				File myFilePath = new File(foldPath);*/
				//在file的原文件名上加上时间
				String fileName = MyCommonUtil.getFilenameAddCurrentTime(file.getOriginalFilename());
				File myFilePath = new File(myFolderPath, fileName);
				if (!myFilePath.exists()) {
					myFilePath.createNewFile();
				}
				myFilePath.getAbsoluteFile();//得到文件的绝对路径
				FileOutputStream fos = new FileOutputStream(myFilePath);//利用输出流向文件中传输数据		
				fos.write(bytes);
				fos.close();//将文件保存在本地d盘中
				String downloadFilename = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(ExcelUpUtil.POINT));
				paper = setPaperAttr(subject, score, subjectPerson, teacher, time, downloadFilename, term, num);				
	            String postfix = ExcelUpUtil.getPostfix(file.getOriginalFilename());  	             
                if(ExcelUpUtil.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)){ 
                	paperDetail = saveXlsDateToBase(paper, file);
                }else if(ExcelUpUtil.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)){  
                	paperDetail = saveXlsxDateToBase(paper, file);  
                } 	            		        								
//				List<Map<Integer,List<String>>> data = ExcelUpUtil.readExcel(file);
//				session.setAttribute("data", data.get(0));				
				if (paperDetail != null) {
					paperDetail = setPaperDetailAttr(paperDetail,subject, score, subjectPerson, teacher, time, paperTime, term, num);
//					paperDetail.setPaperid(paperId);
//					paperDetail.setTotaltitle(data.get(0).get(1).size()-2);//总列数
					paperDetail.settId(MyCommonUtil.getUserId(session));
					int i = paperDetailService.insertPaperDetailSelective(paperDetail);
					if(i == 1){
						model.addObject("flag",true);
						PaperDetail paperDetail1 = paperDetailService.getPaperDetailById(paperDetail.getPaperid());
						List<PaperDetail> paperDetailList = new ArrayList<PaperDetail>();
						if(paperDetail1 != null){
							paperDetailList.add(paperDetail1);
							List<PaperDetailToString> paperDetailToString = convertReview(paperDetailList);//将数据库中的时间进行格式化，并且退后8小时
							model.addObject("flag1",true);
							model.addObject("paperDetailList", paperDetailToString);
						}else{
							model.addObject("flag1",false);
						}
					}else{
						model.addObject("flag",false);
					}								
					model.setViewName("upRecord");			
				}else {	
					model.addObject("flag", false);
					model.setViewName("view/result/uploadFailure");	
				}
//				model.addObject("data", data.get(0));//存取第一页的数据
			} catch (IOException e) {
				log.error(e);
			}catch (Exception eFilePath){    
				log.error("新建文件操作出错");	
				log.error(eFilePath);
			}			
		}else {	
			model.addObject("errorMsg", "当前页面为"+Thread.currentThread().getStackTrace()[1].getClassName());
			model.addObject("logMsg", "上传文件为空："+Thread.currentThread().getStackTrace()[1].getMethodName());
			model.setViewName("view/result/uploadFailure");			
		}
		return model;
	}
	
	
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: selctPaperRecord
	 * @Description: 对试卷详情模糊查询
	 * @param keyWord
	 * @param classfiy
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/selctPaperRecord",method = RequestMethod.POST)
	public ModelAndView selctPaperRecord(@RequestParam("keyWord")String keyWord,
			@RequestParam("classfiy")String classfiy,HttpSession session) throws UnsupportedEncodingException{
		ModelAndView model = new ModelAndView();
		classfiy = MyCommonUtil.changeEncode(classfiy);
		keyWord = MyCommonUtil.changeEncode(keyWord);
		if(keyWord == null){
			model = getPaperRecord(session);//若前台不传递数据，则查询全部
		}else{
			PaperDetail paperDetail = setPaperDetailAttrByClassify(classfiy, keyWord);
			String userId = MyCommonUtil.getUserId(session);
			if(null != userId){
				model.addObject("flag",true);
				paperDetail.settId(userId);
				List<PaperDetail> paperDetailList = paperDetailService.getGeneralPaperDetailByAttr(paperDetail);		
				if(null != paperDetailList && paperDetailList.size() > 0){				
					List<PaperDetailToString> paperDetailToString = convertReview(paperDetailList);			
					model.addObject("paperDetailList", paperDetailToString);
					model.addObject("flag1",true);
				}else{
					model.addObject("flag1",false);
				}
				model.setViewName("upRecord");
			}else{
				model.addObject("errorMsg", "当前页面为"+Thread.currentThread().getStackTrace()[1].getClassName());
				model.setViewName("view/result/uploadFailure");
			}
		}
		return model;
		
	}
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: getPaperRecord
	 * @Description: 查询paper表获得所有上传试卷记录
	 * @param session
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping("/getPaperRecord")
	public ModelAndView getPaperRecord(HttpSession session){
		ModelAndView model = new ModelAndView();
		List<PaperDetailToString> paperDetailToString = new ArrayList<PaperDetailToString>();
		String userId = MyCommonUtil.getUserId(session);
		if(null != userId){
			PaperDetail paperDetail = new PaperDetail();
			paperDetail.settId(userId);
			List<PaperDetail> paperDetailList = paperDetailService.getPaperDetailByAttr(paperDetail);			
			paperDetailToString = convertReview(paperDetailList);
			if(null != paperDetailToString && paperDetailToString.size() > 0){
				model.addObject("flag",true);
				model.addObject("paperDetailList", paperDetailToString);
			}else{
				model.addObject("flag", false);
			}
			model.addObject("flag1",true);
			model.setViewName("upRecord");
		}else{
			model.addObject("errorMsg", "当前页面为"+Thread.currentThread().getStackTrace()[1].getClassName());
			model.setViewName("view/result/uploadFailure");
		}
		return model;
	}

	private PaperDetail setPaperDetailAttr(PaperDetail paperDetail,String subject,String score,String subjectPerson,String teacher,String time,String paperTime,
			String term,String num) throws UnsupportedEncodingException{
		paperDetail.setSubject(MyCommonUtil.changeEncode(subject));
		paperDetail.setScore((MyCommonUtil.changeEncode(score) == null) ? 100: Integer.parseInt(MyCommonUtil.changeEncode(score)));//如果分数为空的话默认设置100分
		paperDetail.setSubjectperson(MyCommonUtil.changeEncode(subjectPerson));
		paperDetail.setTeacher(MyCommonUtil.changeEncode(teacher));
		paperDetail.setTime(MyCommonUtil.getDateFormatToDatabase(time));
		paperDetail.setPapertime(MyCommonUtil.changeEncode(paperTime));
		paperDetail.setTerm(MyCommonUtil.changeEncode(term));
		paperDetail.setNum((MyCommonUtil.changeEncode(num) == null)?50:Integer.parseInt(MyCommonUtil.changeEncode(num)));//设置默认人数为50人		
		return paperDetail;
	}
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: setPaperAttr
	 * @Description: 保存表头
	 * @param subject
	 * @param score
	 * @param subjectPerson
	 * @param teacher
	 * @param time
	 * @param paperTime
	 * @param term
	 * @param num
	 * @return
	 * @throws UnsupportedEncodingException
	 * @return: Paper
	 */
	private Paper setPaperAttr(String subject,String score,String subjectPerson,String teacher,String time,String downloadFilename,
			String term,String num) throws UnsupportedEncodingException{
		Paper paper = new Paper();
		paper.setPaperid(MyCommonUtil.getTimeString());//自动生成试卷唯一id
		paper.setSubject(MyCommonUtil.changeEncode(subject));
		paper.setScore((MyCommonUtil.changeEncode(score) == null) ? 100: Integer.parseInt(MyCommonUtil.changeEncode(score)));//如果分数为空的话默认设置100分
		paper.setSubjectperson(MyCommonUtil.changeEncode(subjectPerson));
		paper.setTeacher(MyCommonUtil.changeEncode(teacher));
		paper.setTime(MyCommonUtil.getDateFormatToDatabase(time));
		paper.setPapertime(downloadFilename);
		paper.setTerm(MyCommonUtil.changeEncode(term));
		paper.setNum((MyCommonUtil.changeEncode(num) == null)?50:Integer.parseInt(MyCommonUtil.changeEncode(num)));//设置默认人数为50人
		return paper;		
	}
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: setChangeAbleValue
	 * @Description: 对paper的可变参数赋值
	 * @param list
	 * @param paper
	 * @return
	 * @return: Paper
	 */
	private Paper setChangeAbleValue(String[] sList,Paper paper){		
		paper.setParam1(sList[0]);		
		paper.setParam2(sList[1]);
		paper.setParam3(sList[2]);
		paper.setParam4(sList[3]);
		paper.setParam5(sList[4]);
		paper.setParam6(sList[5]);
		paper.setParam7(sList[6]);
		paper.setParam8(sList[7]);
		paper.setParam9(sList[8]);
		paper.setParam10(sList[9]);
		paper.setParam11(sList[10]);
		paper.setParam12(sList[11]);
		paper.setParam13(sList[12]);
		paper.setParam14(sList[13]);
		paper.setParam15(sList[14]);
		paper.setParam16(sList[15]);
		paper.setParam17(sList[16]);
		paper.setParam18(sList[17]);
		paper.setParam19(sList[18]);
		paper.setParam20(sList[19]);
		paper.setParam21(sList[20]);
		paper.setParam22(sList[21]);
		paper.setParam23(sList[22]);
		paper.setParam24(sList[23]);
		paper.setParam25(sList[24]);
		return paper;
	}
	
	/**]
	 * 
	 * @user: tongchaohua
	 * @Title: saveXlsDateToBase
	 * @Description: 将xls格式的excel的数据保存到数据库中,如果保存成功，返回试卷id
	 * @param paper
	 * @param file
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	@SuppressWarnings("resource")
	private PaperDetail saveXlsDateToBase(Paper paper,MultipartFile file) throws Exception{ 
        // IO流读取文件  
        InputStream input = null;  
        HSSFWorkbook wb = null; 
        int totalNum = 0; 
        int totalRows = 0;
	    input = file.getInputStream();  
	    // 创建文档  
	    wb = new HSSFWorkbook(input);                         
	    //读取sheet(页)  
	    for(int numSheet=0;numSheet<wb.getNumberOfSheets();numSheet++){  
	        HSSFSheet hssfSheet = wb.getSheetAt(numSheet);  
	        if(hssfSheet == null){  
	            continue;  
	        }  
	        totalRows = hssfSheet.getLastRowNum();//得到总行数，从0开始算  需要+1        
	        for(int rowNum = 0;rowNum <= totalRows;rowNum++){ //去除表头，rows最后一行可以相等
	            HSSFRow hssfRow = hssfSheet.getRow(rowNum);  
	            String[] sList = new String[]{null,null,null,null,null,    null,null,null,null,null,
	            		null,null,null,null,null,    null,null,null,null,null,       null,null,null,null,null};//长度为25
	            if(hssfRow!=null){  
	                //设置一个长度最大为25的arraylist
	               int  totalCells = hssfRow.getLastCellNum();//从0开始算，正好合适，cell最后一行取不到
	                //读取列，从第一列开始  
	                if (totalCells <= 25) {							
	                	for(short c=0;c<totalCells;c++){//不需要加1                       	
	                		HSSFCell cell = hssfRow.getCell(c);                              
	                		sList[c] = ExcelUpUtil.getHValue(cell).trim(); 
	                	}          
					}else{
						throw new Exception("第 "+ (rowNum+1) + " 行上传超过25列");							
					}	               
	                paper = setChangeAbleValue(sList, paper);
	                paper.setNum(totalCells);
	                paper.setExcelorder(rowNum);//excel顺序
	                int flag = paperService.insertPaperSelective(paper);
	                if (flag == 1) {
	                	totalNum += 1;
	                }else{
	                	throw new Exception("第 "+ (rowNum+1) + " 行插入出错上传字段超过25" +Thread.currentThread().getStackTrace()[1].getMethodName());							 
	                }
	            }
	        }  
	    }
	    PaperDetail paperDetail = new PaperDetail();
	    paperDetail.setTotaltitle(paper.getNum()-2);//设置总列数
	    paperDetail.setPaperid(paper.getPaperid());//设置试卷id
        return (totalNum == (totalRows+1))?paperDetail:null;  
 
    } 
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: saveXlsxDateToBase
	 * @Description: 将xlsx格式的excel数据保存到数据库
	 * @param paper
	 * @param file
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	@SuppressWarnings("resource")
	private PaperDetail saveXlsxDateToBase(Paper paper, MultipartFile file) throws Exception {		
	    // IO流读取文件  
        InputStream input = null;  
        XSSFWorkbook wb = null; 
        int totalNum = 0;
        int totalRows = 0;        
        input = file.getInputStream();  
            // 创建文档  
        wb = new XSSFWorkbook(input);                         
        //读取sheet(页)  
        for(int numSheet=0;numSheet<wb.getNumberOfSheets();numSheet++){  
            XSSFSheet xssfSheet = wb.getSheetAt(numSheet);  
            if(xssfSheet == null){  
                continue;  
            }  
            totalRows = xssfSheet.getLastRowNum(); 
            //读取Row,从第一行开始  
            for(int rowNum = 0;rowNum <= totalRows;rowNum++){  
                XSSFRow xssfRow = xssfSheet.getRow(rowNum); 
                String[] sList = new String[]{null,null,null,null,null,    null,null,null,null,null,
	            		null,null,null,null,null,    null,null,null,null,null,       null,null,null,null,null};//长度为25                    
                if(xssfRow!=null){  	                       
                    int totalCells = xssfRow.getLastCellNum();  
                    //读取列，从第一列开始  
                    if (totalCells <= 25) {	
	                    for(int c=0;c<totalCells;c++){  
	                        XSSFCell cell = xssfRow.getCell(c);  	                                                       
	                        sList[c] = ExcelUpUtil.getXValue(cell).trim();  
	                    } 
                    }else{
                    	throw new Exception("第 "+ (rowNum+1) + " 行上传超过25列");	
                    }
                    paper = setChangeAbleValue(sList, paper);
                    paper.setNum(totalCells);
                    paper.setExcelorder(rowNum);//excel顺序
    	            int flag =   paperService.insertPaperSelective(paper);
    	            if (flag == 1) {
    					totalNum += 1;
    				}else{
    					throw new Exception("第 "+ (rowNum+1) + " 行插入出错上传字段超过25" +Thread.currentThread().getStackTrace()[1].getMethodName());							 
    				}
                }
               
            }           
        }
        PaperDetail paperDetail = new PaperDetail();
	    paperDetail.setTotaltitle(paper.getNum());//设置总列数
	    paperDetail.setPaperid(paper.getPaperid());//设置试卷id
		return (totalNum == totalRows + 1)?paperDetail:null;  
	}
	
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: getPaperIdByUserId
	 * @Description: 查询结果表·，找出所有的试卷id不包含重复
	 * @param userId
	 * @return
	 * @return: List<String>
	 */
	@SuppressWarnings("unused")
	private List<String> getPaperIdByUserId(String userId){
		ReviewResult review = new ReviewResult();
		review.settId(userId);
		List<String> paperIdList = new ArrayList<String>();
		List<ReviewResult> reviewList= reviewResultService.getRevByAttr(review);//通过tid查询所有的试卷Id
		if(null != reviewList && reviewList.size() > 0){
			for(int i = 0;i < reviewList.size();i++){//将查询到的试卷Id赋给paperidList，不包含重复的
				if(null != paperIdList && paperIdList.size() > 0){
					if(!paperIdList.contains(reviewList.get(i).getpId())){
						paperIdList.add(reviewList.get(i).getpId());
					}
				}else{
					paperIdList.add(reviewList.get(i).getpId());
				}
			}
		}
		return ( null != paperIdList && paperIdList.size() > 0)? paperIdList : null;		
	}

	
	private List<PaperDetailToString> convertReview(List<PaperDetail> paperDetailList){
		List<PaperDetailToString> paperDetailListToString = new ArrayList<PaperDetailToString>();
		if(null != paperDetailList && paperDetailList.size() > 0 ){
			for (int i = 0; i < paperDetailList.size(); i++) {
				PaperDetailToString paperDetailToString = new PaperDetailToString();
				
				//将Date换成String
				paperDetailToString.setTime(
						 MyCommonUtil.databaseToWeb(paperDetailList.get(i).getTime(),"yyyy-MM-dd"));
				paperDetailToString.setUptime(
				MyCommonUtil.databaseToWeb(paperDetailList.get(i).getUptime(),"yyyy-MM-dd HH:mm:ss"));
				
				paperDetailToString.setPapertime(paperDetailList.get(i).getPapertime());
				
				paperDetailToString.setNum(paperDetailList.get(i).getNum());
				paperDetailToString.setPaperid(paperDetailList.get(i).getPaperid());
				paperDetailToString.setScore(paperDetailList.get(i).getScore());
				paperDetailToString.setSubject(paperDetailList.get(i).getSubject());
				paperDetailToString.setSubjectperson(paperDetailList.get(i).getSubjectperson());
				paperDetailToString.setTeacher(paperDetailList.get(i).getTeacher());
				paperDetailToString.setTerm(paperDetailList.get(i).getTerm());
				paperDetailListToString.add(paperDetailToString);
			}
			
		}
		return (null != paperDetailListToString && paperDetailListToString.size() > 0) ? paperDetailListToString : null;		
	}	
	
	private PaperDetail setPaperDetailAttrByClassify(String classfiy,String keyWord){
		PaperDetail paperDetail = new PaperDetail();
		if(classfiy != null){
			switch(classfiy){
				case "term":
					paperDetail.setTerm(keyWord);
					break;
				case "subject":
					paperDetail.setSubject(keyWord);
					break;
				case "subjectPerson":
					paperDetail.setSubjectperson(keyWord);
					break;
				default:
					paperDetail.setPaperid(keyWord);
					break;
			}
		}
		return paperDetail;		
	}	
 }
