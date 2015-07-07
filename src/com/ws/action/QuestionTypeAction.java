package com.ws.action;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.po.QuestionType;
import com.ws.service.QuestionTypeService;
@Component("questionTypeAction")
@Scope("prototype")
public class QuestionTypeAction extends ActionSupport implements ServletRequestAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private QuestionTypeService questionTypeService;
	private List<QuestionType>typeList;
	private HttpServletRequest request;
	public String index() {
		return "index";
	}
	public String addType(){
		return "add";
	}
	public String getQuestionTypeByCourseId() throws Exception{
		String courseId=(String) request.getAttribute("courseId");
		System.out.print("显示获取题型的课程ID为"+courseId);
		if(!courseId.equals("undefined"))
		{
		 try {
			int course =Integer.parseInt(courseId);
			 typeList=questionTypeService.getTypeListByCourseId(course);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			typeList=null;
		}
		}
		return null;
	}

	public boolean addForm( String title) throws Exception{
		return questionTypeService.addType(title);
	}
	public String getQuestionType()throws Exception{
		typeList=questionTypeService.getTypeList();
		return SUCCESS;
	}
	
	public QuestionTypeService getQuestionTypeService() {
		return questionTypeService;
	}
	public void setQuestionTypeService(QuestionTypeService questionTypeService) {
		this.questionTypeService = questionTypeService;
	}
	public List<QuestionType> getTypeList() {
		return typeList;
	}
	public void setTypeList(List<QuestionType> typeList) {
		this.typeList = typeList;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request=arg0;
	}
	
	

}
