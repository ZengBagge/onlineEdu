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
import com.ws.po.UserCommon;
import com.ws.po.Work;
import com.ws.service.WorkManageService;

@Component("workManageAction")
@Scope("prototype")
public class WorkManageAction  extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private List<Work>workingManageList =null;
    private List<Work>workedManageList =null;
    private Map<String, Object>session;
    @Resource
    private WorkManageService workManageService;
    private PageBean pageBean;
    private int page=1;
    private int workId;
    private Work work;
    
    
	public String index(){	
		  return "index";
	}
	public String infoSet()throws Exception{
		if(workId != 0){
			this.work = workManageService.getWorkById(workId);
            if(work != null){
            	return "add";
            }else {
				return ERROR;
			}
		}else {
			return ERROR;
		}
		
	}
	
	public boolean del(int id)throws Exception{
		return workManageService.delete(id);
	}
	public String getWorkingManage() throws Exception{
		try {
			UserCommon userCommon =(UserCommon) session.get("user");
			if(userCommon!=null && userCommon.getRule()==2)
			{
				workingManageList=workManageService.getWorkingByTeacher(userCommon);
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
public String getWorkedManage(){
		try {
			UserCommon userCommon =(UserCommon) session.get("user");
			if(userCommon!=null && userCommon.getRule()==2)
			{
				pageBean=workManageService.getWorkedByTeacher(userCommon,page,10);
				workedManageList = pageBean.getList();
				return "index";
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
	public String addWork(){
		work = null;
		return "add";
	}
	
	public boolean addForm(String titString,String workMajorString,String courseId,String startTime,String endTime,String workTopicId,String description,HttpSession session) throws Exception{
		System.out.print("添加任务，对象为："+workMajorString+"开始时间为："+startTime);
		try {
			UserCommon userCommon =(UserCommon) session.getAttribute(UserAction.USER_SESSION);
			if(userCommon!=null && userCommon.getRule()==2)
			{
			if(titString.length()>1 && workMajorString.length()>1 && courseId.length()>0 && startTime.length()>10 && endTime.length()>10 && workTopicId.length()>0)
				return workManageService.addWork(titString,workMajorString,courseId,startTime,endTime,workTopicId,description,userCommon);
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
	public boolean setForm(int id, String titString,String workMajorString,int courseId,String startTime,String endTime,String workTopicId,String description,HttpSession session) throws Exception{
		System.out.print("添加任务，对象为："+workMajorString+"开始时间为："+startTime);
		try {
			UserCommon userCommon =(UserCommon) session.getAttribute(UserAction.USER_SESSION);
			if(userCommon!=null && userCommon.getRule()==2)
			{
			if(titString.length()>1 && workMajorString.length()>1  && startTime.length()>10 && endTime.length()>10 && workTopicId.length()>0)
				return workManageService.setWork(id,titString,workMajorString,startTime,endTime,workTopicId,description,userCommon,courseId);
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
	public List<Work> getWorkingManageList() {
		return workingManageList;
	}

	public void setWorkingManageList(List<Work> workingManageList) {
		this.workingManageList = workingManageList;
	}

	public List<Work> getWorkedManageList() {
		return workedManageList;
	}

	public void setWorkedManageList(List<Work> workedManageList) {
		this.workedManageList = workedManageList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public WorkManageService getWorkManageService() {
		return workManageService;
	}

	public void setWorkManageService(WorkManageService workManageService) {
		this.workManageService = workManageService;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session=arg0;
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
	public int getWorkId() {
		return workId;
	}
	public void setWorkId(int workId) {
		this.workId = workId;
	}
	public Work getWork() {
		return work;
	}
	public void setWork(Work work) {
		this.work = work;
	}
	
	
}
