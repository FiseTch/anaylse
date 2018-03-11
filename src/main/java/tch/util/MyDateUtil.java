package tch.util;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
	 * @Title: formatDate
	 * @Description: 对日期进行格式化
	 * @param date
	 * @param format 时间格式
	 * @return
	 * @return: String
	 */
    public static String formatDate(Date date,String format){  
        String result="";  
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
     * @Description: 得到yyyy-MM-dd HH:mm:ss的时间格式
     * @param data
     * @return
     * @return: String
     */
    
    public static String getDateFormat(Date data){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(data);       
    }  
     /**
      * 
      * @user: tongchaohua
      * @Title: getDateFormat
      * @Description: 将String类型的时间变成yyyy-MM-dd HH:mm:ss格式的时间
      * @param time
      * @return
      * @return: Date
      */
    public static Date getDateFormat(String time){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return df.parse(time);
        } catch (ParseException e) {           
            e.printStackTrace();
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
     * @Description: 传入一个Strin类型的时间
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
     * @Description: 如果不传参，则获取本地时间的String类型
     * @return
     * @return: String
     */
    public static String getTimeString(){
        Calendar calendar = new GregorianCalendar();
        return getTimeString(calendar);
    }
          
        
    public static int absoluteDay(Calendar cal, boolean use1904windowing) {    
        return DateUtil.absoluteDay(cal, use1904windowing);    
    }  
   
} 