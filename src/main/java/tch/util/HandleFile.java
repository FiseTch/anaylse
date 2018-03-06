package tch.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class HandleFile {
	
	/**
     * 保存文件到本地,并返回文件的url相对路径
     * 
     * @param file
     *            文件
     * @param basePath
     *            项目根目录
     * @return 文件的url相对路径
     * @throws IOException
     */
    public static String saveFile(MultipartFile file) throws IOException {

         /*第2步：创建文件夹*/
        /*1.1 根据今天的日期创建文件夹相对路径 upload/2017-05-12 */
        Date now = new Date();// 获取当前时间
        Long longNow = now.getTime();
        DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strToday = dFormat.format(longNow);
        String relFolderPath = "upload" + File.separator + strToday; // 文件夹相对路径

         /*1.2 文件夹全路径：项目路径/upload/2017-05-12 */
        String basePath = "D:\\anaylse";
        String fullFolderPath = basePath + File.separator + relFolderPath; // 文件夹全路径

        /*1.3 根据文件夹全路径判断，如果文件夹不存在，创建文件夹*/ 
        File outFolder = new File(fullFolderPath);
        if (!outFolder.exists()) {
            outFolder.mkdirs();
        }

         /*第2步：创建文件*/
        /*2.1 根据文件夹全路径，文件名，当前日期的时间戳 创建文件全路径*/ 
        String fileName = file.getOriginalFilename(); //获取文件名（含后缀）
        String fullFilePath = fullFolderPath + File.separator + longNow + fileName; // 文件全路径

        String fullUrlPath = "upload/" + strToday + "/" + longNow + fileName;// 要存入数据的相对路径

         /*第2.2步：根据文件全路径，创建文件*/     
        File outFile = new File(fullFilePath);
        if (!outFile.exists()) {// 如果文件不存在，创建文件
            outFile.createNewFile();
        }

         /*第3步：根据空文件，创建字符流的目的地（输出流）*/
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(outFile)); // 创建文件输出流

        /*第4步：根据上传的文件，创建缓冲区，生成字符流，存入缓冲区*/
        /*第4步：获取输入流*/
        InputStream iStream = file.getInputStream();
        BufferedInputStream biStream = new BufferedInputStream(iStream);
        /*第5步：把输入流写入输出流*/
        int f;
		while ((f = biStream .read()) != -1) {
			stream .write(f);        
        }
       
        
        /*第6步：刷新流，关闭流*/
		stream .flush();
		stream .close();
        biStream .close();
        iStream.close();

        return fullUrlPath;
    }

    /**
     * 根据文件绝对路径，删除一个图片，删除记录
     * 
     * @param fullFilePath
     */
    public static void deleteFile(String fullFilePath) {
        File deleteFile = new File(fullFilePath);
        deleteFile.delete();
    }
}

