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
	public ModelAndView  upExcel(@RequestParam("file")MultipartFile file){
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
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: down
	 * @Description: 文件的下载
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return: void
	 */
	@RequestMapping("/downExcel")
    public void down(HttpServletRequest request,HttpServletResponse response) throws Exception{  
        //模拟文件，myfile.txt为需要下载的文件  
        String fileName = request.getSession().getServletContext().getRealPath("upload")+"/myfile.txt";  
        //获取输入流  
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));  
        //假如以中文名下载的话  
        String filename = "下载文件.txt";  
        //转码，免得文件名中文乱码  
        filename = URLEncoder.encode(filename,"UTF-8");  
        //设置文件下载头  
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
        response.setContentType("multipart/form-data");   
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }  
        out.close();  
    } 
	
 
}
