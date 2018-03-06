package tch.util;
/*package com.tch.util;

import java.io.File;  
import java.io.FileInputStream;  
import java.sql.Connection;  
import java.sql.ResultSet;  
  
import net.sf.json.JSONArray;  
import net.sf.json.JSONObject;  
  
import org.apache.poi.hssf.usermodel.HSSFRow;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.poifs.filesystem.POIFSFileSystem;  
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.struts2.ServletActionContext;  
  
import com.opensymphony.xwork2.ActionSupport;  
import com.steadyjack.dao.UserDao;  
import com.steadyjack.model.PageBean;  
import com.steadyjack.model.User;  
import com.steadyjack.util.DateUtil;  
import com.steadyjack.util.DbUtil;  
import com.steadyjack.util.ExcelUtil;  
import com.steadyjack.util.JsonUtil;  
import com.steadyjack.util.ResponseUtil;  
import com.steadyjack.util.StringUtil;  
  
public class UserAction extends ActionSupport {  
  
    *//** 
     *  
     *//*  
    private static final long serialVersionUID = 1L;  
  
    private String page;  
    private String rows;  
    private String id;  
    private User user;  
    private String delId;  
      
    private File userUploadFile;  
      
    public String getPage() {  
        return page;  
    }  
    public void setPage(String page) {  
        this.page = page;  
    }  
    public String getRows() {  
        return rows;  
    }  
    public void setRows(String rows) {  
        this.rows = rows;  
    }  
      
    public String getDelId() {  
        return delId;  
    }  
    public void setDelId(String delId) {  
        this.delId = delId;  
    }  
    public User getUser() {  
        return user;  
    }  
    public void setUser(User user) {  
        this.user = user;  
    }  
      
      
    public String getId() {  
        return id;  
    }  
    public void setId(String id) {  
        this.id = id;  
    }  
  
    public File getUserUploadFile() {  
        return userUploadFile;  
    }  
    public void setUserUploadFile(File userUploadFile) {  
        this.userUploadFile = userUploadFile;  
    }  
  
    DbUtil dbUtil=new DbUtil();  
    UserDao userDao=new UserDao();  
      
    //��ȡ�û��б�  
    public String list()throws Exception{  
        Connection con=null;  
        PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));  
        try{  
            con=dbUtil.getCon();  
            JSONObject result=new JSONObject();  
            JSONArray jsonArray=JsonUtil.formatRsToJsonArray(userDao.userList(con, pageBean));  
            int total=userDao.userCount(con);  
            result.put("rows", jsonArray);  
            result.put("total", total);  
            ResponseUtil.write(ServletActionContext.getResponse(),result);  
        }catch(Exception e){  
            e.printStackTrace();  
        }finally{  
            try {  
                dbUtil.closeCon(con);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return null;  
    }  
      
    //�����û�  : ��ͨexcel����  
    public String export()throws Exception{  
        Connection con=null;  
        try {  
            con=dbUtil.getCon();  
            Workbook wb=new HSSFWorkbook();  
            String headers[]={"���","����","�绰","Email","QQ","��������"};  
            ResultSet rs=userDao.userList(con, null);  
            ExcelUtil.fillExcelData(rs, wb, headers);  
            ResponseUtil.export(ServletActionContext.getResponse(), wb, "�û�excel��.xls");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                dbUtil.closeCon(con);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return null;  
    }  
      
    //�û����� : ����Ԥ�����úõ�excelģ���ļ����е���  
    public String export2()throws Exception{  
        Connection con=null;  
        try {  
            con=dbUtil.getCon();  
            ResultSet rs=userDao.userList(con, null);  
            Workbook wb=ExcelUtil.fillExcelDataWithTemplate(rs, "�û�ģ���ļ�.xls");  
            ResponseUtil.export(ServletActionContext.getResponse(), wb, "����ģ�浼���û�excel��.xls");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                dbUtil.closeCon(con);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return null;  
    }  
      
  
    //excel�ļ�����,������������  
    public String upload()throws Exception{  
        //��ʱ��WorkbookӦ���Ǵ� �ͻ���������ϴ������� uploadFile��,��ʵ����ȡ���ش��̵�һ����  
        POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(userUploadFile));  
        HSSFWorkbook wb=new HSSFWorkbook(fs);  
        HSSFSheet hssfSheet=wb.getSheetAt(0);  
          
        if(hssfSheet!=null){  
            //����excel,�ӵڶ��п�ʼ �� rowNum=1,�����ȡ��Ԫ�������,Ȼ����и�ʽ����,���������ݿ�  
            for(int rowNum=1;rowNum<=hssfSheet.getLastRowNum();rowNum++){  
                HSSFRow hssfRow=hssfSheet.getRow(rowNum);  
                if(hssfRow==null){  
                    continue;  
                }  
                  
                User user=new User();  
                user.setName(ExcelUtil.formatCell(hssfRow.getCell(0)));  
                user.setPhone(ExcelUtil.formatCell(hssfRow.getCell(1)));  
                user.setEmail(ExcelUtil.formatCell(hssfRow.getCell(2)));  
                user.setQq(ExcelUtil.formatCell(hssfRow.getCell(3)));  
                  
                //���ڵ�Ԫ��������Ҫ�������⴦��  
                user.setBirth(DateUtil.formatString(ExcelUtil.formatCell2(hssfRow.getCell(4)), "yyyy-MM-dd"));  
                Connection con=null;  
                try{  
                    con=dbUtil.getCon();  
                    userDao.userAdd(con, user);  
                }catch(Exception e){  
                    e.printStackTrace();  
                }finally{  
                    dbUtil.closeCon(con);  
                }  
            }  
        }  
        JSONObject result=new JSONObject();  
        result.put("success", "true");  
        ResponseUtil.write(ServletActionContext.getResponse(), result);  
        return null;  
    }  
      
}  
*/