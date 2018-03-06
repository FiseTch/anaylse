package tch.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.util.ReflectionUtils;

public class DownUtil {

    private static Log log = LogFactory.getLog(DownUtil.class);
    
    private static CellStyle titleStyle; // 标题行样式
    private static Font titleFont; // 标题行字体
    private static CellStyle headStyle; // 表头行样式
    private static Font headFont; // 表头行字体
    private static CellStyle contentStyle; // 内容行样式
    private static Font contentFont; // 内容行字体
    
    /**
     * 初始化HSSFWorkbook
     * 
     * @Method_Name : init
     * @return  HSSFWorkbook
     */
    private static HSSFWorkbook init() {
        HSSFWorkbook wb = new HSSFWorkbook();

        titleFont = wb.createFont();
        titleStyle = wb.createCellStyle();
        headStyle = wb.createCellStyle();
        headFont = wb.createFont();
        contentStyle = wb.createCellStyle();
        contentFont = wb.createFont();

        initTitleCellStyle();
        initTitleFont();
        initHeadCellStyle();
        initHeadFont();
        initContentCellStyle();
        initContentFont();
        return wb;
    }
    
    /**
     * 根据数据集合生成excel文件并下载
     * 
     * @Method_Name : downloadExcel
     * @param excelExportEnum excel文件名称，工作簿和生成的内容标题都用这个名称
     * @param map 列名与集合中实体类属性的对应关系
     * @param list 需要导出的数据集合
     * @return 
     * @throws Exception
     * @return 
     */
    public static void downloadExcel(HttpServletRequest request, HttpServletResponse response,ExcelExportEnum excelExportEnum, LinkedHashMap<String, String> map, List<?> list) throws Exception {

        // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = init();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet(excelExportEnum.getTypeName());  
        
        int columnNum = map.keySet().size();
        String[] colNames = (String[]) map.keySet().toArray(new String[columnNum]);
        String[] colContents = (String[]) map.values().toArray(new String[columnNum]);
        
        // 第三步，填充sheet 数据
        createTableTitleRow(sheet, excelExportEnum.getTypeName(), columnNum);
        creatTableHeadRow(sheet.createRow((int) 1), colNames);
        creatTableDataRows(excelExportEnum, sheet, list, colContents);
        
        // 第四步，自动调整列宽
        adjustColumnSize(sheet, columnNum);
        
        // 第六步，输出Excel文件  
        String fileName = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "_" + excelExportEnum.getTypeName() +".xls";
        outWrite(request, response, wb, fileName);  
    }

    private static void outWrite(HttpServletRequest request, HttpServletResponse response, HSSFWorkbook wb,
            String fileName) throws IOException {
        OutputStream output = null;
        try {
            String userAgent = request.getHeader("User-Agent");
            output = response.getOutputStream();
            response.reset();  
            response.setHeader("Content-disposition", "attachment; filename="+ MyDateUtil.encodeFileName(fileName, userAgent));  
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setCharacterEncoding("utf-8");  
            wb.write(output);  
            output.flush(); 
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(output != null){
                output.close();  
            }
        }
    }
    
    /**
     * @Description: 创建标题行(需合并单元格)
     */
    private static void createTableTitleRow(HSSFSheet sheet, String fileName, int columnNum) {
        CellRangeAddress titleRange = new CellRangeAddress(0, 0, 1, columnNum);
        sheet.addMergedRegion(titleRange);
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) 800);
        HSSFCell cell = row.createCell(1);
        cell.setCellStyle(titleStyle);
        cell.setCellValue(fileName);
    }
    
    /**
     * @Description: 创建表头行(需合并单元格)
     */
    private static void creatTableHeadRow(HSSFRow row, String[] colNames) {
        // 表头
        row.setHeight((short) 400);
        // 列头名称
        HSSFCell cell = null;
        for (int i = 0; i < colNames.length; i++) {
            cell = row.createCell(i + 1);
            cell.setCellStyle(headStyle);
            cell.setCellValue(colNames[i]);
        }
    }
    
    /**
     * @Description: 创建表格数据
     */
    private static void creatTableDataRows(ExcelExportEnum excelExportEnum, HSSFSheet sheet, List<?> list, String[] colContents) {
        HSSFRow row = null;
        for (int i = 0; i < list.size(); i++) { 
            row = sheet.createRow((int) i + 2);
            row.setHeight((short) 350);
            if(excelExportEnum.equals(ExcelExportEnum.Broker))
                createTeableBrokerData(colContents, (Object[])list.get(i), row);
            else if(excelExportEnum.equals(ExcelExportEnum.appointment))
                createTeableAppointmentData(colContents, new Object[]{list.get(i)}, row);
            else if(excelExportEnum.equals(ExcelExportEnum.recommend))
                createTeableRecommendData(colContents, new Object[]{list.get(i)}, row);
            else
                createTeableDefaultDate(colContents, list.get(i), row);
        }
    }
    /**
     * @Description: 预约客户信息
     */
    private static void createTeableRecommendData(String[] colContents, Object[] objects, HSSFRow row) {
        Object value = null;
        HSSFCell cell = null;
        String colContent = "";
        Object object = objects[0];
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd"); 
        DecimalFormat df = new DecimalFormat("#.00"); 
        for (int i = 0; i < colContents.length; i++) {
            colContent = colContents[i];
            cell = row.createCell(i + 1);
            cell.setCellStyle(contentStyle);
            if (ReflectionUtils.findField(object.getClass(), colContent) == null)
                value = null;
            else
                value = ReflectionUtils.invokeMethod((Method) object, colContent);
            if("sex".equals(colContent)){
                cell.setCellValue(value == null ? "--" : ("1".equals(value.toString()) ? "男" : "女"));
            }else if("status".equals(colContent)){
                if(value == null){
                    cell.setCellValue("--");
                }else if("1".equals(value.toString())){
                    cell.setCellValue("致电");
                }else if("2".equals(value.toString())){
                    cell.setCellValue("到访");
                }else if("3".equals(value.toString())){
                    cell.setCellValue("签约");
                }
            }else if("showRoomTime".equals(colContent) || "followUp".equals(colContent)){
                cell.setCellValue(value == null ? "--" : sdf.format(value));
            }else if("brokerAmount".equals(colContent)){
                cell.setCellValue(value == null ? "--" : df.format(value));
            }else
                cell.setCellValue(value == null ? "--" : value.toString());
        }
    }
    /**
     * @Description: 推荐客户信息
     */
    private static void createTeableAppointmentData(String[] colContents, Object[] objects, HSSFRow row) {
        Object value = null;
        HSSFCell cell = null;
        String colContent = "";
        Object object = objects[0];
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd"); 
        for (int i = 0; i < colContents.length; i++) {
            colContent = colContents[i];
            cell = row.createCell(i + 1);
            cell.setCellStyle(contentStyle);
            if (ReflectionUtils.findField(object.getClass(), colContent) == null)
                value = null;
            else
                value = ReflectionUtils.invokeMethod((Method) object, colContent);//?
            if("status".equals(colContent)){
                if(value == null){
                    cell.setCellValue("--");
                }else if("1".equals(value.toString())){
                    cell.setCellValue("致电");
                }else if("2".equals(value.toString())){
                    cell.setCellValue("到访");
                }else if("3".equals(value.toString())){
                    cell.setCellValue("签约");
                }
            }else if("showRoomTime".equals(colContent) || "followUp".equals(colContent)){
                cell.setCellValue(value == null ? "--" : sdf.format(value));
            }else
                cell.setCellValue(value == null ? "--" : value.toString());
        }
    }

    /**
     * @Description: 经纪人信息
     */
    private static void createTeableBrokerData(String[] colContents, Object[] objects, HSSFRow row){
        Object value = null;
        HSSFCell cell = null;
        String colContent = "";
        Object object = objects[0];
        for (int i = 0; i < colContents.length; i++) {
            colContent = colContents[i];
            cell = row.createCell(i + 1);
            cell.setCellStyle(contentStyle);
            
            if("count".equals(colContent)){//推荐人次
                cell.setCellValue(objects[1] == null ? "--" : objects[1].toString());
                continue;
            }
            if("amount".equals(colContent)){//已结佣总金额（元）
                cell.setCellValue(objects[2] == null ? "--" : objects[2].toString());
                continue;
            }
            
            if (ReflectionUtils.findField(object.getClass(), colContent) == null)
                value = null;
            else
                value = ReflectionUtils.invokeMethod((Method) object, colContent);
            if("sex".equals(colContent))
                cell.setCellValue(value == null ? "--" : ("1".equals(value.toString()) ? "男" : "女"));
            else if("status".equals(colContent))
                cell.setCellValue(value == null ? "--" : ("1".equals(value.toString()) ? "是" : "否"));
            else
                cell.setCellValue(value == null ? "--" : value.toString());
        }
    }
    
    /**
     * @Description: 默认信息
     */
    private static void createTeableDefaultDate(String[] colContents, Object object, HSSFRow row){
        Object value = null;
        HSSFCell cell = null;
        for (int i = 0; i < colContents.length; i++) {
            value = ReflectionUtils.invokeMethod((Method) object, colContents[i]);
            cell = row.createCell(i + 1);
            cell.setCellStyle(contentStyle);
            cell.setCellValue(value == null ? "" : value.toString());
        }
    }
    
    /**
     * @Description: 自动调整列宽
     */
    private static void adjustColumnSize(HSSFSheet sheet, int columnNum) {
        for (int i = 0; i < columnNum; i++) {
            sheet.autoSizeColumn(i + 1);
        }
    }
    
    /**
     * @Description: 初始化标题行样式
     */
    private static void initTitleCellStyle() {
        titleStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleStyle.setFont(titleFont);
        titleStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
    }
    
    /**
     * @Description: 初始化表头行样式
     */
    private static void initHeadCellStyle() {
        headStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headStyle.setFont(headFont);
        headStyle.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
        headStyle.setBorderTop(BorderStyle.MEDIUM);
        headStyle.setBorderBottom(BorderStyle.THIN);
        headStyle.setBorderLeft(BorderStyle.THIN);
        headStyle.setBorderRight(BorderStyle.THIN);
        headStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        headStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        headStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        headStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
    }
    
    /**
     * @Description: 初始化内容行样式
     */
    private static void initContentCellStyle() {
        contentStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        contentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentStyle.setFont(contentFont);
        contentStyle.setBorderTop(BorderStyle.THIN);
        contentStyle.setBorderBottom(BorderStyle.THIN);
        contentStyle.setBorderLeft(BorderStyle.THIN);
        contentStyle.setBorderRight(BorderStyle.THIN);
        contentStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        contentStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        contentStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        contentStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        contentStyle.setWrapText(true); // 字段换行
    }
    
    /**
     * @Description: 初始化标题行字体
     */
    private static void initTitleFont() {
        titleFont.setFontName("华文楷体");
        titleFont.setFontHeightInPoints((short) 20);
        titleFont.setBold(true);
        titleFont.setCharSet(Font.DEFAULT_CHARSET);
        titleFont.setColor(IndexedColors.BLACK.getIndex());
    }
    
    /**
     * @Description: 初始化表头行字体
     */
    private static void initHeadFont() {
        headFont.setFontName("宋体");
        headFont.setFontHeightInPoints((short) 10);
        headFont.setBold(true);
        headFont.setCharSet(Font.DEFAULT_CHARSET);
        headFont.setColor(IndexedColors.BLACK.getIndex());
    }

    /**
     * @Description: 初始化内容行字体
     */
    private static void initContentFont() {
        contentFont.setFontName("宋体");
        contentFont.setFontHeightInPoints((short) 10);
        contentFont.setBold(false);
        contentFont.setCharSet(Font.DEFAULT_CHARSET);
        contentFont.setColor(IndexedColors.BLACK.getIndex());
    }
}
