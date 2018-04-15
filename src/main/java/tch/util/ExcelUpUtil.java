package tch.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;


public class ExcelUpUtil {

	private static int totalRows; //sheet中总行数  
	private static int totalCells; //每一行总单元格数  
	public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";  
    public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";  
    public static final String EMPTY = "";  
    public static final String POINT = ".";  
    public static final int DEAFULT = 0;
    public static final String ROOT_CATALOG = "D:"+ File.separator + "anaylse";
    public static SimpleDateFormat sdf =   new SimpleDateFormat("yyyy/MM/dd");  
    /** 
     * 获得path的后缀名 
     * @param path 
     * @return 
     */  
    public static String getPostfix(String path){  
        if(path==null || EMPTY.equals(path.trim())){  
            return EMPTY;  
        }  
        if(path.contains(POINT)){  
            return path.substring(path.lastIndexOf(POINT)+1,path.length());  
        }  
        return EMPTY;  
    }  
    /** 
     * 单元格格式 
     * @param hssfCell 
     * @return 
     */  
    @SuppressWarnings({ "static-access", "deprecation" })  
    public static String getHValue(HSSFCell hssfCell){  
         if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {  
             return String.valueOf(hssfCell.getBooleanCellValue());  
         } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {  
             String cellValue = "";  
             if(HSSFDateUtil.isCellDateFormatted(hssfCell)){                  
                 Date date = HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue());  
                 cellValue = sdf.format(date);  
             }else{  
                 DecimalFormat df = new DecimalFormat("#.##");  
                 cellValue = df.format(hssfCell.getNumericCellValue());  
                 String strArr = cellValue.substring(cellValue.lastIndexOf(POINT)+1,cellValue.length());  
                 if(strArr.equals("00")){  
                     cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));  
                 }    
             }  
             return cellValue;  
         } else {  
            return String.valueOf(hssfCell.getStringCellValue());  
         }  
    }  
    /** 
     * 单元格格式 
     * @param xssfCell 
     * @return 
     */  
    public static String getXValue(XSSFCell xssfCell){  
         if (xssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {  
             return String.valueOf(xssfCell.getBooleanCellValue());  
         } else if (xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {  
             String cellValue = "";  
             if(MyCommonUtil.isCellDateFormatted(xssfCell)){  
                 Date date = MyCommonUtil.getJavaDate(xssfCell.getNumericCellValue());  
                 cellValue = sdf.format(date);  
             }else{  
                 DecimalFormat df = new DecimalFormat("#.##");  
                 cellValue = df.format(xssfCell.getNumericCellValue());  
                 String strArr = cellValue.substring(cellValue.lastIndexOf(POINT)+1,cellValue.length());  
                 if(strArr.equals("00")){  
                     cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));  
                 }    
             }  
             return cellValue;  
         } else {  
            return String.valueOf(xssfCell.getStringCellValue());  
         }  
    } 
    /**
     * 
     * @user: tongchaohua
     * @Title: getValue
     * @Description: TODO
     * @param cell
     * @return
     * @return: String
     */
    public static String  getValue(XSSFCell cell){
    	
	    switch(cell.getCellType()){
	    case Cell.CELL_TYPE_STRING:
	        return cell.getRichStringCellValue().getString().trim();
	    case Cell.CELL_TYPE_NUMERIC:
	        if (DateUtil.isCellDateFormatted(cell)) {
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//非线程安全
	            return sdf.format(cell.getDateCellValue());
	        } else {
	            return String.valueOf(cell.getNumericCellValue());
	        }
	    case Cell.CELL_TYPE_BOOLEAN:
	        return String.valueOf(cell.getBooleanCellValue());
	    case Cell.CELL_TYPE_FORMULA:
	        return cell.getCellFormula();
	    default:
	        return null;
	    }
    }
    /** 
     * read the Excel .xlsx,.xls 
     * @param file jsp中的上传文件 
     * @return 
     * @throws IOException  
     */  
    public static List<Map<Integer,List<String>>> readExcel(MultipartFile file) throws IOException {  
        if(file==null||EMPTY.equals(file.getOriginalFilename().trim())){  
            return null;  
        }else{  
            String postfix = getPostfix(file.getOriginalFilename());  
            if(!EMPTY.equals(postfix)){  
                if(OFFICE_EXCEL_2003_POSTFIX.equals(postfix)){  
                    return readXls(file);  
                }else if(OFFICE_EXCEL_2010_POSTFIX.equals(postfix)){  
                    return readXlsx(file);  
                }else{                    
                    return null;  
                }  
            }  
        }
		return null;  
    }   
    /** 
     * read the Excel 2010 .xlsx 
     * @param file 
     * @param beanclazz 
     * @param titleExist 
     * @return 
     * @throws IOException  
     */   
    public static List<Map<Integer,List<String>>> readXlsx(MultipartFile file){  
       List<Map<Integer,List<String>>> list = new ArrayList<Map<Integer,List<String>>>();  
        // IO流读取文件  
        Map<Integer,List<String>> map = new HashMap<Integer, List<String>>();
        InputStream input = null;  
        XSSFWorkbook wb = null;  
        List<String> rowList = null;  
        try {  
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
                    if(xssfRow!=null){  
                        rowList = new ArrayList<String>();  
                        totalCells = xssfRow.getLastCellNum();  
                        //读取列，从第一列开始  
                        for(int c=0;c<totalCells;c++){  
                            XSSFCell cell = xssfRow.getCell(c);  
                            if(cell==null){  
                                rowList.add(EMPTY);  
                                continue;  
                            }                             
                            rowList.add(getXValue(cell).trim());  
                        }     
                    map.put(rowNum,rowList);                                            
                    }  
                } 
                list.add(map);
            }  
            return list;  
        } catch (IOException e) {             
            e.printStackTrace();  
        } finally{  
            try {  
                input.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return null;  
          
    }  
    /** 
     * read the Excel 2003-2007 .xls 
     * @param file 
     * @param beanclazz 
     * @param titleExist 
     * @return 
     * @throws IOException  
     */  
    public static List<Map<Integer,List<String>>> readXls(MultipartFile file){   
        List<Map<Integer,List<String>>> list = new ArrayList<Map<Integer,List<String>>>();  
        // IO流读取文件  
    	Map<Integer,List<String>> map = new HashMap<Integer, List<String>>();
        InputStream input = null;  
        HSSFWorkbook wb = null;  
        List<String> rowList = null;  
        try {  
            input = file.getInputStream();  
            // 创建文档  
            wb = new HSSFWorkbook(input);                         
            //读取sheet(页)  
            for(int numSheet=0;numSheet<wb.getNumberOfSheets();numSheet++){  
                HSSFSheet hssfSheet = wb.getSheetAt(numSheet);  
                if(hssfSheet == null){  
                    continue;  
                }  
                totalRows = hssfSheet.getLastRowNum(); 
                List<String> totalList = new ArrayList<String>(); 
                totalList.add(String.valueOf(totalRows+1));
                //读取Row,从第一行开始  
                map.put(0, totalList);//存row的行数
                for(int rowNum = 0;rowNum <= totalRows;rowNum++){  
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);  
                    if(hssfRow!=null){  
                        rowList = new ArrayList<String>();  
                        totalCells = hssfRow.getLastCellNum();  
                        //读取列，从第一列开始  
                        for(short c=0;c<totalCells;c++){  
                            HSSFCell cell = hssfRow.getCell(c);  
                            if(cell==null){  
                                rowList.add(EMPTY);  
                                continue;  
                            }                             
                            rowList.add(getHValue(cell).trim());  
                        }          
                        map.put(rowNum+1,rowList);  
                    }                     
                }  
                list.add(map);
            }  
            return list;  
        } catch (IOException e) {             
            e.printStackTrace();  
        } finally{  
            try {  
                input.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return null;  
    } 
}
