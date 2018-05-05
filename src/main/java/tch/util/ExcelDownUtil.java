package tch.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import tch.model.ReviewResult;

/**
 * 
 * 
 * Copyright:tch
 * 
 * @class: tch.util
 * @Description: excel结果下载
 *
 * @version: v1.0.0
 * @author: tongch
 * @date: 2018-04-18
 * Modification History:
 * date         Author          Version            Description
 *------------------------------------------------------------
 * 2018-04-18     tongch          v1.1.0
 */
public class ExcelDownUtil extends AbstractExcelView {
	@Override
	 @SuppressWarnings({ "static-access", "deprecation" })
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        ReviewResult reviewResult = (ReviewResult) model.get("reviewResult");  
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short)8);            //设置字体的大小
        font.setFontName("微软雅黑");                        //设置字体的样式，如：宋体、微软雅黑等
        font.setItalic(false);                            //斜体true为斜体
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);    //对文中进行加粗
        font.setColor(HSSFColor.BLACK.index);            //设置字体的颜色
       
        //设置单元格基本string类型格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        //设置单元格的数字格式
        HSSFCellStyle style1 = workbook.createCellStyle();
        style1.setFont(font);
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style1.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));  
        
        Sheet sheet = workbook.createSheet();
    
        // 第一行文字说明
        Row row = sheet.createRow(0);
        sheet.autoSizeColumn(1,true);//设置宽度自适应
        Cell cell = row.createCell(0, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);    
        cell.setCellValue(new HSSFRichTextString(ConstantTch.RESULT_ID));	
        
        cell = row.createCell(1, Cell.CELL_TYPE_STRING);//
		cell.setCellStyle(style);    
		cell.setCellValue(new HSSFRichTextString(ConstantTch.RESULT_PID));
		
		cell = row.createCell(2, Cell.CELL_TYPE_STRING);//
		cell.setCellStyle(style);    
		cell.setCellValue(new HSSFRichTextString(ConstantTch.RESULT_TID));
		
		cell = row.createCell(3, Cell.CELL_TYPE_STRING);//
		cell.setCellStyle(style);    
		cell.setCellValue(new HSSFRichTextString(ConstantTch.RESULT_TIME));
		
		cell = row.createCell(4, Cell.CELL_TYPE_STRING);//设置类型为数值型
		cell.setCellStyle(style);    
		cell.setCellValue(new HSSFRichTextString(ConstantTch.RESULT_RELIABILITY));
        
		cell = row.createCell(5, Cell.CELL_TYPE_STRING);//设置类型为数值型
		cell.setCellStyle(style);    
		cell.setCellValue(new HSSFRichTextString(ConstantTch.RESULT_DISTINCTION));	
		
		cell = row.createCell(6, Cell.CELL_TYPE_STRING);//设置类型为数值型
		cell.setCellStyle(style);    
		cell.setCellValue(new HSSFRichTextString(ConstantTch.RESULT_DIFFICULTY));
		
		cell = row.createCell(7, Cell.CELL_TYPE_STRING);//设置类型为数值型
		cell.setCellStyle(style);    
		cell.setCellValue(new HSSFRichTextString(ConstantTch.RESULT_VALIDITY));
		
		//填充数据
	    sheet.setColumnWidth((short) 0, (short) (35.7 * 100));
        row = sheet.createRow(1);
         // 校标度
        cell = row.createCell(0, cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue(new HSSFRichTextString(reviewResult.getId()));
        //区分度
        cell = row.createCell(1, cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue(new HSSFRichTextString(reviewResult.getpId()));
        //难度
        cell = row.createCell(2, cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue(new HSSFRichTextString(reviewResult.gettId()));
        //信度
        cell = row.createCell(3, cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue(new HSSFRichTextString(MyCommonUtil.formatDate(reviewResult.getTime(),"yyyy-MM-dd HH:mm:ss")));
        
        cell = row.createCell(4, cell.CELL_TYPE_NUMERIC);
        cell.setCellStyle(style1);
        cell.setCellValue(reviewResult.getReliability());
        
        cell = row.createCell(5, cell.CELL_TYPE_NUMERIC);
        cell.setCellStyle(style1);
        cell.setCellValue(reviewResult.getDistinction());
        
        cell = row.createCell(6, cell.CELL_TYPE_NUMERIC);
        cell.setCellStyle(style1);
        cell.setCellValue(reviewResult.getDifficulty());
        
        cell = row.createCell(7, cell.CELL_TYPE_NUMERIC);
        cell.setCellStyle(style1);
        cell.setCellValue(reviewResult.getValidityB());
        
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
              
        String fileName = ConstantTch.DOWNFILENAME + reviewResult.getId() + ConstantTch.FILEEXTENSION; 
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
