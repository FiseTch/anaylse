package tch.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import tch.model.Paper;
import tch.service.IPaperService;
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
		String paperId = "";
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
				paper = setPaperAttr(subject, score, subjectPerson, teacher, time, paperTime, term, num);				
	            String postfix = ExcelUpUtil.getPostfix(file.getOriginalFilename());  	             
                if(ExcelUpUtil.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)){ 
                	paperId = saveXlsDateToBase(paper, file);
                }else if(ExcelUpUtil.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)){  
                	paperId = saveXlsxDateToBase(paper, file);  
                } 	            		        
								
				List<Map<Integer,List<String>>> data = ExcelUpUtil.readExcel(file);
				session.setAttribute("data", data.get(0));
				
				if (paperId != null) {
					model.addObject("paperId", paperId);
					session.setAttribute("paperId", paperId);
				}
				model.addObject("data", data.get(0));//存取第一页的数据
				model.setViewName("showExcel");			
			} catch (IOException e) {
				log.error(e);
			}catch (Exception eFilePath){    
				log.error("新建文件操作出错");	
				log.error(eFilePath);
			}			
		}else {	
			model.addObject("errorMsg", "当前上传文件为空");
			model.addObject("logMsg", "上传文件为空："+Thread.currentThread().getStackTrace()[1].getMethodName());
			model.setViewName("view/result/uploadFailure");			
		}
		return model;
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
	private Paper setPaperAttr(String subject,String score,String subjectPerson,String teacher,String time,String paperTime,
			String term,String num) throws UnsupportedEncodingException{
		Paper paper = new Paper();
		paper.setPaperid(MyCommonUtil.getTimeString());//自动生成试卷唯一id
		paper.setSubject(MyCommonUtil.changeEncode(subject));
		paper.setScore((MyCommonUtil.changeEncode(score) == null) ? 100: Integer.parseInt(MyCommonUtil.changeEncode(score)));//如果分数为空的话默认设置100分
		paper.setSubjectperson(MyCommonUtil.changeEncode(subjectPerson));
		paper.setTeacher(MyCommonUtil.changeEncode(teacher));
		paper.setTime(MyCommonUtil.getDateFormatToDatabase(time));
		paper.setPapertime(MyCommonUtil.changeEncode(paperTime));
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
	private String saveXlsDateToBase(Paper paper,MultipartFile file) throws Exception{ 
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
	        for(int rowNum = 1;rowNum <= totalRows;rowNum++){ //去除表头，rows最后一行可以相等
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
	               
	            }
	            paper = setChangeAbleValue(sList, paper);
	            paper.setExcelOrder(rowNum);//excel顺序
	            int flag = paperService.insertPaperSelective(paper);
	            if (flag == 1) {
					totalNum += 1;
				}else{
					throw new Exception("第 "+ (rowNum+1) + " 行插入出错上传字段超过25" +Thread.currentThread().getStackTrace()[1].getMethodName());							 
				}
	        }  
	    }
             
        return (totalNum == (totalRows))?paper.getPaperid():null;  
 
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
	private String saveXlsxDateToBase(Paper paper, MultipartFile file) throws Exception {		
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
            for(int rowNum = 1;rowNum <= totalRows;rowNum++){  
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
                    paper.setExcelOrder(rowNum);//excel顺序
    	            int flag =   paperService.insertPaperSelective(paper);
    	            if (flag == 1) {
    					totalNum += 1;
    				}else{
    					throw new Exception("第 "+ (rowNum+1) + " 行插入出错上传字段超过25" +Thread.currentThread().getStackTrace()[1].getMethodName());							 
    				}
                }
               
            }           
        }
		return (totalNum == totalRows + 1)?paper.getPaperid():null;  
	}
}
