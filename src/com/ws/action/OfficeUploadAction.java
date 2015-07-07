package com.ws.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.common.FlashPaper;

public class OfficeUploadAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File file;  
    private String fileFileName;  
    private String fileContentType;  
      
    public String execute() {  
        HttpServletRequest request = ServletActionContext.getRequest();  
         
        return "suc";  
    }  
    /** 
     * 获得不同文件的后缀 
     */  
    public void setFileContentType(String fileContentType) {  
        if (fileContentType.contains("msword")) {  
            this.fileContentType = ".doc";  
        } else if (fileContentType.contains("excel")) {  
            this.fileContentType = ".xls";  
        } else if (fileContentType.contains("pdf")) {  
            this.fileContentType = ".pdf";  
        } else if (fileContentType.contains("powerpoint")) {  
            this.fileContentType = ".ppt";  
        } else {  
            this.fileContentType = ".doc";  
        }  
    }  
    public File getFile() {  
        return file;  
    }  
    public void setFile(File file) {  
        this.file = file;  
    }  
    public String getFileContentType() {  
        return fileContentType;  
    }  
    public String getFileFileName() {  
        return fileFileName;  
    }  
    public void setFileFileName(String fileFileName) {  
        this.fileFileName = fileFileName;  
    }  
}
