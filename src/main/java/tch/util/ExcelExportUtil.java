package tch.util;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.CharEncoding;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import tch.model.User;

public class ExcelExportUtil extends AbstractExcelView {
	
	
    @SuppressWarnings({ "static-access", "deprecation" })
    protected void buildExcelDocument(Map<String, Object> modle, HSSFWorkbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        @SuppressWarnings("unchecked")
        //List<User> list = (List<User>) model.get("infoList");      
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short)8);            //设置字体的大小
        font.setFontName("微软雅黑");                        //设置字体的样式，如：宋体、微软雅黑等
        font.setItalic(false);                            //斜体true为斜体
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);    //对文中进行加粗
        font.setColor(HSSFColor.BLACK.index);            //设置字体的颜色
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        Sheet sheet = workbook.createSheet();
    
        // 第一行文字说明
        Row row = sheet.createRow(0);
        sheet.autoSizeColumn(1,true);//设置宽度自适应
        Cell cell = row.createCell(0, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);    
        cell.setCellValue("序号（学号）");
		for (int i = 1; i < ((Integer)modle.get("totalNum")+1); i++) {//默认最多有49道题				
			cell = row.createCell(i, Cell.CELL_TYPE_NUMERIC);//设置类型为数值型
			cell.setCellStyle(style);    
			cell.setCellValue(new HSSFRichTextString("第"+ i + "题得分"));
		}
		cell = row.createCell((Integer)modle.get("totalNum") + 1, Cell.CELL_TYPE_STRING);//设置类型为数值型
		cell.setCellStyle(style);    
		cell.setCellValue(new HSSFRichTextString("总分"));
           /* cell = row.createCell(2, Cell.CELL_TYPE_STRING);
            cell.setCellStyle(style);    
            cell.setCellValue("合同登记时间");
            
            cell = row.createCell(3, Cell.CELL_TYPE_STRING);
            cell.setCellStyle(style);    
            cell.setCellValue("合同金额");

            cell = row.createCell(4, Cell.CELL_TYPE_STRING);
            cell.setCellStyle(style);    
            cell.setCellValue("履行方式");

            cell = row.createCell(5, Cell.CELL_TYPE_STRING);
            cell.setCellStyle(style);    
            cell.setCellValue("合同类型");

            cell = row.createCell(6, Cell.CELL_TYPE_STRING);
            cell.setCellStyle(style);    
            cell.setCellValue("开始时间");

            cell = row.createCell(7, Cell.CELL_TYPE_STRING);
            cell.setCellStyle(style);    
            cell.setCellValue("结束时间");

            cell = row.createCell(8, Cell.CELL_TYPE_STRING);
            cell.setCellStyle(style);    
            cell.setCellValue("备注");*/

            // 下面是具体内容
            /*for (int i = 0; i < length; i++) {
                sheet.setColumnWidth((short) i, (short) (35.7 * 100));
                row = sheet.createRow(i + 1);
                // 合同名称
                cell = row.createCell(0, cell.CELL_TYPE_STRING);
                cell.setCellValue(list.get(i).getName());
                // 合同单位
                cell = row.createCell(1, cell.CELL_TYPE_STRING);
                cell.setCellValue(list.get(i).getUnit());

                // 合同登记时间
                cell = row.createCell(2, cell.CELL_TYPE_STRING);
                cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(list.get(i).getReg_time()));

                
                // 合同金额
                cell = row.createCell(3, cell.CELL_TYPE_STRING);
                //把float对象转换为String对象
                float con_money=list.get(i).getCon_money();
                String str =String.valueOf(con_money);
                cell.setCellValue(str);

                // 合同履行方式
                cell = row.createCell(4, cell.CELL_TYPE_STRING);
                cell.setCellValue(list.get(i).getPerform_style());

                // 合同类型
                cell = row.createCell(5, cell.CELL_TYPE_STRING);
                cell.setCellValue(list.get(i).getCon_type());

                // 开始时间
                cell = row.createCell(6, cell.CELL_TYPE_STRING);
                cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(list.get(i).getCon_start_time()));

                // 结束时间
                cell = row.createCell(7, cell.CELL_TYPE_STRING);
                cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(list.get(i).getCon_end_time()));

                // 备注
                cell = row.createCell(8, cell.CELL_TYPE_STRING);
                cell.setCellValue(list.get(i).getRemark());
            }
*/
            //web浏览通过MIME类型判断文件是excel类型
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        // 对文件名进行处理。防止文件名乱码
        String fileName = "学生成绩表模板.xls"; 
        String userAgent = request.getHeader("User-Agent"); 
        //针对IE或者以IE为内核的浏览器：
        if (userAgent.contains("MSIE")||userAgent.contains("Trident")) {
        	fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
        	//非IE浏览器的处理：
        	fileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
        }
        // Content-disposition属性设置成以附件方式进行下载
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));        
        OutputStream os = response.getOutputStream();
        workbook.write(os);
        os.flush();
        os.close();
    }
}
