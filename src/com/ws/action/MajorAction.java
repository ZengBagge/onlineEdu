package com.ws.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.po.Major;
import com.ws.service.MajorService;

@Component("majorAction")
@Scope("prototype")
public class MajorAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private MajorService majorservice;
	private List<Major>majorList;
	
	public String getAllMajor() throws Exception{
		
		majorList=majorservice.getAllMajor();
		return null;
	}


	
	/**************以下为get  set 方法*************/
	public List<Major> getMajorList() {
		return majorList;
	}

	public void setMajorList(List<Major> majorList) {
		this.majorList = majorList;
	}



	public MajorService getMajorservice() {
		return majorservice;
	}

	public void setMajorservice(MajorService majorservice) {
		this.majorservice = majorservice;
	}
	
}
