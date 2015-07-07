package com.ws.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.po.Classes;
import com.ws.po.UserCommon;
import com.ws.service.ClassesService;

@Component("classesAction")
@Scope("prototype")
public class ClassesAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object>session ;
	private Classes classes;
	private List<Classes>classesList;
	@Resource
	private ClassesService classService;
	
	public String info(){
		return "info";
	}
	
	public String getClassesByUserCommon() throws Exception{
		UserCommon userCommon = (UserCommon) session.get(UserAction.USER_SESSION);
		if(userCommon != null && userCommon.getRule() == 1)
		{
			this.classes = classService.getClassesByUserCommonId(userCommon.getId()); 
		}
		return null;
	}
	
	public String myClasses() throws Exception{
		UserCommon userCommon = (UserCommon) session.get(UserAction.USER_SESSION);
		if(userCommon != null && userCommon.getRule() == 2)
		{
			this.classesList = classService.getClassesListByTeacher(userCommon.getId());
			
		}
		return "myclasses";
	}
	
	public boolean addClasses(String classUid,String name,int majorId ,HttpSession session){
		
		try {
			UserCommon userCommon = (UserCommon) session.getAttribute(UserAction.USER_SESSION);
			return 	classService.addClasses(classUid, name, majorId, userCommon);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public Classes getClasses() {
		return classes;
	}

	public List<Classes> getClassesList() {
		return classesList;
	}

	public void setClassesList(List<Classes> classesList) {
		this.classesList = classesList;
	}

	public ClassesService getClassService() {
		return classService;
	}

	public void setClassService(ClassesService classService) {
		this.classService = classService;
	}

	
}
