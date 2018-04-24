package tch.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.mail.internet.MimeUtility;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.DateUtil;  
  
/**
 *
 * @author tongchaohua
 *
 */
public class MyCommonUtil extends DateUtil{  
	
	private static final Log log = LogFactory.getLog(MyCommonUtil.class);
	/** 
	   * 设置下载文件中文件的名称 
	   *  
	   * @param filename 
	   * @param request 
	   * @return 
	 * @throws UnsupportedEncodingException 
	   */  
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: changeEncode
	 * @Description: 防止乱码
	 * @param s
	 * @return
	 * @throws UnsupportedEncodingException
	 * @return: String
	 */
	public static String changeEncode(String s) throws UnsupportedEncodingException{
		return (s==null)?null:URLDecoder.decode(s.trim(),"UTF-8");
	}
	public static String encodeFileName(String filename, String agent) {
		/**
		 * 获取客户端浏览器和操作系统信息 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE
		 * 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
		 * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1;
		 * zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6
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
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: valueOfString
	 * @Description: 给字符串加0
	 * @param str
	 * @param len
	 * @return
	 * @return: String
	 */
	private static String valueOfString(String str, int len) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len - str.length(); i++) {
			sb.append("0");
		}
		return (sb.length() == 0) ? (str) : (sb.toString() + str);
	}
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: formatDateToDate
	 * @Description: 将date类型的转化成自己定义（yyyy-MM-dd）格式的date
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 * @return: Date
	 */
	public static Date formatDateToDate(Date date){

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (date != null) {
			String temp = sdf.format(date);
			ParsePosition pos = new ParsePosition(0); 
			Date d = sdf.parse(temp,pos);
			return d;
		}else{			
			return date;				
		}
	}
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: formatDate
	 * @Description: 对日期进行格式化
	 * @param date
	 * @param format 时间格式
	 * @return
	 * @return: String
	 */
    public static String formatDate(Date date,String format){  
        String result = null;  
        SimpleDateFormat sdf=new SimpleDateFormat(format);  
        if(date!=null){  
            result=sdf.format(date);  
        }  
        return result;  
    }  
    /**
     * 
     * @user: tongchaohua
     * @Title: getDateFormat
     * @Description: 得到yyyy-MM-dd的时间格式
     * @param data
     * @return
     * @return: String
     */
    
    public static String getDateFormat(Date data){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(data);       
    } 
    
    /**
     * 
     * @user: tongchaohua
     * @Title: getDateFormatToDatabase
     * @Description: 
     * 将yyyy-MM-dd格式的 前端日期保存至数据库 
     * @param time
     * @return
     * @return: Date
     */
    public static Date getDateFormatToDatabase(String time){
    	if(time != null){   		
    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		try {
    			return df.parse(time+" 23:00:00");
    		} catch (ParseException e) {           
    			e.printStackTrace();
    		}  
    	}else{   		
    		return null;
    	}
    	return null;
    }
     /**
      * 
      * @user: tongchaohua
      * @Title: getDateFormat
      * @Description: 将yyyy-MM-dd HH:mm:ss格式的时间转成date
      * @param time
      * @return
      * @return: Date
      */
    public static Date getDateFormat(String time){
    	if(time != null){   		
    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		try {
    			return df.parse(time);
    		} catch (ParseException e) {           
    			e.printStackTrace();
    		}  
    	}else{   		
    		return null;
    	}
    	return null;
    }
    
    /**
     * 
     * @user: tongchaohua
     * @Title: getTimeString
     * @Description: 将时间化为字母
     * @param calendar
     * @return
     * @return: String
     */
    private static String getTimeString(Calendar calendar) {      
        StringBuffer sb = new StringBuffer();
        sb.append(String.valueOf(calendar.get(Calendar.YEAR)))     
          .append(valueOfString(String.valueOf(calendar.get(Calendar.MONTH) + 1),2))
          .append(valueOfString(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)),2))
          .append(valueOfString(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)),2))
          .append(valueOfString(String.valueOf(calendar.get(Calendar.MINUTE)),2))
          .append(valueOfString(String.valueOf(calendar.get(Calendar.SECOND)),2))
          .append(valueOfString(String.valueOf(calendar.get(Calendar.MILLISECOND)),3));       
        return sb.toString();
    }  
    /**
     * 
     * @user: tongchaohua
     * @Title: getTimeString
     * @Description: 传入一个yyyy-MM-dd HH:mm:ss格式的时间，并将其转换成一串字符串
     * @param time
     * @return
     * @return: String
     */
    public static String getTimeString(String time){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(getDateFormat(time));
        return getTimeString(calendar);
    }
    /**
     * 
     * @user: tongchaohua
     * @Title: getTimeString
     * @Description: 通过时间生成唯一Id
     * 如果不传参，则获取当前默认格式的时间，并将其转换成一串字符串输出
     * @return
     * @return: String
     */
    public static String getTimeString(){
    	log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
        Calendar calendar = new GregorianCalendar();
        return getTimeString(calendar);
    }
          
    /**
     *  
     * @user: tongchaohua
     * @Title: getFilenameAddCurrentTime
     * @Description: 传入一个文件名，将其加入 20180416125622638 当前时间的后缀，精确到毫秒(17位)	
     * @param originalFilename 参数必须包含.
     * @return
     * @return: String
     */    
    public static String getFilenameAddCurrentTime(String originalFilename){
    	String prefixName = originalFilename.substring(0, originalFilename.lastIndexOf(ExcelUpUtil.POINT));
		String suffixName = originalFilename.substring(originalFilename.lastIndexOf(ExcelUpUtil.POINT)+1);
		String fileName = prefixName + MyCommonUtil.getTimeString() + ExcelUpUtil.POINT + suffixName;
		return fileName;
    }
    
    public static int absoluteDay(Calendar cal, boolean use1904windowing) {    
        return DateUtil.absoluteDay(cal, use1904windowing);    
    }  
   
} 