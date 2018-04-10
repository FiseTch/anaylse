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
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import tch.util.ExcelUpUtil;


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
@Scope("prototype")
@RequestMapping(value = "/getExcel")//访问路径
public class GetExcel {
	private static Log log = LogFactory.getLog(GetExcel.class);
/*	private static int totalRows; //sheet中总行数  
	private static int totalCells; //每一行总单元格数  
*/	/**
	 * 
	 * @user: tongchaohua
	 * @Title: upExcel
	 * @Description: 文件的上传
	 * @param file
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/upExcel")
	public ModelAndView  upExcel(@RequestParam("file")MultipartFile file,HttpSession session){
		ModelAndView modle = new ModelAndView();
		if (!file.isEmpty()) {
			String fileNameExtension = ExcelUpUtil.getPostfix(file.getOriginalFilename());
			//String fileNameExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
			if ("xls".equals(fileNameExtension) || "xlsx".equals(fileNameExtension)) {				
		        Date now = new Date();// 获取当前时间
		        Long longNow = now.getTime();
		        DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		        String strToday = dFormat.format(longNow);
				String foldPath = ExcelUpUtil.ROOT_CATALOG + File.separator + strToday;//文件上传的目标位置		
				File myFolderPath = new File(foldPath);  //新建文件夹  
				try {
					if (!myFolderPath.exists()) {   
						myFolderPath.mkdir();   
					}   
					byte[] bytes = file.getBytes();// 获取上传数据
					/*这样只是改变了文件名无法产生目录及在目录内生成文件
					String filePath = foldPath + file.getOriginalFilename();
					File myFilePath = new File(foldPath);*/
					File myFilePath = new File(myFolderPath, file.getOriginalFilename());
					if (!myFilePath.exists()) {
						myFilePath.createNewFile();
					}
					FileOutputStream fos = new FileOutputStream(myFilePath);//利用输出流向文件中传输数据		
					fos.write(bytes);
					fos.close();
					List<Map<Integer,List<String>>> data = ExcelUpUtil.readExcel(file);
					session.setAttribute("data", data.get(0));
					modle.addObject("data", data.get(0));//存取第一页的数据
				} catch (IOException e) {
					log.error(e);
				}catch (Exception eFilePath){    
					log.error("新建文件操作出错");	
					log.error(eFilePath);
				}
				modle.setViewName("result/uploadSuccess");
			}else{
				modle.setViewName("user/upExcel");
				modle.addObject("fail", "当前上传文件类型错误，文件类型为xls或者xlsx");
			}
		}else {			
			modle.setViewName("result/uploadFailure");			
		}
		return modle;
	}
}
