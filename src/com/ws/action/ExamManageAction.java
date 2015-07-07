package com.ws.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.common.PageBean;
import com.ws.po.Exam;
import com.ws.po.UserCommon;
import com.ws.service.ExamManageService;

@Component("examManageAction")
@Scope("prototype")
public class ExamManageAction extends ActionSupport  implements SessionAware{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Exam>examingManageList =null;
    private List<Exam>examedManageList =null;
    private PageBean pageBean;
    private int page =1;
    private Map<String, Object>session;
    private int examId;
    private Exam exam;
    @Resource
    private ExamManageService examManageService;
	public String index(){
		return "index";
	}
	public String setInfo()throws Exception{
		if(examId !=0){
			exam = examManageService.getExameById(examId);
			return "add";
		}else {
			return ERROR;
		}
	}
	public boolean del(int id)throws Exception{
		return examManageService.delete(id);
	}
	public String getExamingManage() throws Exception{
		try {
			UserCommon userCommon =(UserCommon) session.get(UserAction.USER_SESSION);
			if(userCommon!=null && userCommon.getRule()==2)
			{
				examingManageList=examManageService.getExamingByTeacher(userCommon);
				return SUCCESS;
			}
			else {
				return ERROR;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
   @SuppressWarnings("unchecked")
public String getExamedManage(){
		try {
			UserCommon userCommon =(UserCommon) session.get(UserAction.USER_SESSION);
			if(userCommon!=null && userCommon.getRule()==2)
			{
				pageBean=examManageService.getExamedByTeacher(userCommon,page,6);
				examedManageList =pageBean.getList();
				return SUCCESS;
			}
			else {
				return ERROR;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	public String addExam(){
		exam=null;
		return "add";
	}
	public boolean addForm(String titString,String workMajorString,String courseId,String startTime,String endTime,String workTopicId,String description,String examTimes,HttpSession session) throws Exception{
		System.out.print("添加考试任务，对象为："+workMajorString+"开始时间为："+startTime);
		try {
			UserCommon userCommon =(UserCommon) session.getAttribute(UserAction.USER_SESSION);
			if(userCommon!=null && userCommon.getRule()==2)
			{
			if(titString.length()>1 && workMajorString.length()>1 && courseId.length()>0 && startTime.length()>10 && endTime.length()>10 && workTopicId.length()>0)
				return examManageService.addExam(titString,workMajorString,courseId,startTime,endTime,workTopicId,description,userCommon,examTimes);
			else {
				return false;
			}	
			}
			else {
				return false;
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}

	}
	public boolean setForm(int id, String titString,String workMajorString,String courseId,String startTime,String endTime,String workTopicId,String description,String examTimes,HttpSession session) throws Exception{
		System.out.print("修改考试任务，对象为："+workMajorString+"开始时间为："+startTime);
		try {
			UserCommon userCommon =(UserCommon) session.getAttribute(UserAction.USER_SESSION);
			if(userCommon!=null && userCommon.getRule()==2)
			{
			if(titString.length()>1 && workMajorString.length()>1 && courseId.length()>0 && startTime.length()>10 && endTime.length()>10 && workTopicId.length()>0)
				return examManageService.updateExam(id,titString,workMajorString,courseId,startTime,endTime,workTopicId,description,userCommon,examTimes);
			else {
				return false;
			}	
			}
			else {
				return false;
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}

	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

	public List<Exam> getExamingManageList() {
		return examingManageList;
	}

	public void setExamingManageList(List<Exam> examingManageList) {
		this.examingManageList = examingManageList;
	}

	public List<Exam> getExamedManageList() {
		return examedManageList;
	}

	public void setExamedManageList(List<Exam> examedManageList) {
		this.examedManageList = examedManageList;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	public ExamManageService getExamManageService() {
		return examManageService;
	}
	public void setExamManageService(ExamManageService examManageService) {
		this.examManageService = examManageService;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	public Exam getExam() {
		return exam;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	
	
}
