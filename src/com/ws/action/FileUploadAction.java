package com.ws.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javassist.expr.NewArray;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.common.commonUtil;

@Component("fileUploadAction")
@Scope("prototype")
public class FileUploadAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File file;            //文件
	private String fileFileName;  //文件名 
	private String filePath;      //文件路径
	private InputStream inputStream;
    private String msg;
    private int courseId;
    private int typeId;
	/**
	 * 图片上传
	 * 
	 * @return
	 */

	public String fileUpload() {

		System.out.print("课程Id为"+courseId+"题型Id为"+typeId);
		
		String path = ServletActionContext.getServletContext().getRealPath("/upload");
        File file = new File(path); // 判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            file.mkdir();
        }
        String[] fileSuffix = new String[] { ".xls",".xlsx" };// 只允许上传文件格式
        try {
            File f = this.getFile();
            // 判断文件格式
            System.out.print("开始"+fileFileName);
                if (!fileFileName.endsWith(fileSuffix[0])&&!fileFileName.endsWith(fileSuffix[1])) {
                    msg = "0";
                    return ERROR;
                }
             String fileTypeString=getFileType(fileFileName);
             commonUtil.p("文件类型为"+fileTypeString);
             SimpleDateFormat format=new SimpleDateFormat("YYYYMMddHHmm");
             String dateString=format.format(new Date());
             String fileNewName=dateString+"."+fileTypeString;
             commonUtil.p("新文件名"+fileNewName);
             this.fileFileName=fileNewName;
            FileInputStream inputStream = new FileInputStream(f);
            FileOutputStream outputStream = new FileOutputStream(path + "/"
                    + fileNewName);
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, length);
            }
            inputStream.close();
            outputStream.flush();
            msg = "1";
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            msg = "2";
            return ERROR;
        }
	}

	/**
	 * 用于图片页面显示
	 * 
	 * @return
	 */
	public String readImg() {
		try {
			inputStream = new FileInputStream(new File(ServletActionContext.getRequest().getParameter("path")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return filePath;
	}
	/**
	 * 图片下载
	 * @return
	 */
	public String download() {
        String path = filePath;
        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            //清空response
            response.reset();
            //设置response的Header
            String filenameString = new String(filename.getBytes("gbk"),"iso-8859-1");
            response.addHeader("Content-Disposition", "attachment;filename=" + filenameString);
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

	/**
	 * 取文件后缀
	 * @param fileUri
	 * @return
	 */
	public String getFileType(String fileName){
		 String fileType = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
		 return fileType;
		}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	
}
