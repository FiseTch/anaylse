package tch.util;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.mail.internet.MimeUtility;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.DateUtil;  
  
/**
 *
 * @author tongchaohua
 *
 */
public class MyDateUtil extends DateUtil{  
	
	/** 
	   * 设置下载文件中文件的名称 
	   *  
	   * @param filename 
	   * @param request 
	   * @return 
	   */  
	  public static String encodeFileName(String filename, String agent) {  
	    /** 
	     * 获取客户端浏览器和操作系统信息 
	     * 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar) 
	     * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6 
	     */  
	   // String agent = request.getHeader("USER-AGENT");  
	    try {  
	      if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {  
	        String newFileName = URLEncoder.encode(filename, "UTF-8");  
	        newFileName = StringUtils.replace(newFileName, "+", "%20");  
	        if (newFileName.length() > 150) {  
	          newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");  
	          newFileName = StringUtils.replace(newFileName, " ", "%20");  
	        }  
	        return newFileName;  
	      }  
	      if ((agent != null) && (-1 != agent.indexOf("Mozilla")))  
	        return MimeUtility.encodeText(filename, "UTF-8", "B");  
	  
	      return filename;  
	    } catch (Exception ex) {  
	      return filename;  
	    }  
	  }
  
    public static String formatDate(Date date,String format){  
        String result="";  
        SimpleDateFormat sdf=new SimpleDateFormat(format);  
        if(date!=null){  
            result=sdf.format(date);  
        }  
        return result;  
    }  
      
      
    public static Date formatString(String str,String format) throws Exception{  
        SimpleDateFormat sdf=new SimpleDateFormat(format);  
        return sdf.parse(str);  
    }  
      
    public static void main(String[] args) throws Exception{  
        Date date=formatString("1993/10/12", "yyyy/MM/dd");  
        String str=formatDate(date, "yyyy-MM-dd");  
        System.out.println(str);  
    }  
    public static int absoluteDay(Calendar cal, boolean use1904windowing) {    
        return DateUtil.absoluteDay(cal, use1904windowing);    
    }  
   
} 