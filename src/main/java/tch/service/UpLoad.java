package tch.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UpLoad extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		File f = new File("F:/tch/temp");//ָ����ʱ�ļ����λ�� 
		DiskFileItemFactory ff =new DiskFileItemFactory(1024*1024*5, f);//��ʱ�ļ��Ĵ�С�ʹ��λ��
		//�������ڽ����Ķ��� 
		ServletFileUpload sf =new ServletFileUpload(ff); 
		sf.setFileSizeMax(1024*1024*10);//�������������λ�����ֵ�� 10M 
		sf.setSizeMax(1024*1024*20);//�����ļ������ֵ��20M 
		String path =this.getServletContext().getRealPath("/imgs"); 
		System.out.println("�洢·����"+path); 
	}

}
