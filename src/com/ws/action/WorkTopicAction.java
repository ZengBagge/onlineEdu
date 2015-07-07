package com.ws.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.po.UserCommon;
import com.ws.po.WorkTopic;
import com.ws.service.WorkTopicService;

@Component("workTopicAction")
@Scope("prototype")
public class WorkTopicAction  extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;  
    private List<WorkTopic>workTopicList;
    private String courseId;
   @Resource
   private WorkTopicService workTopicService;
    
   /**
    * 获取教师卷组
    * @return
    * @throws Exception
    */
    public  String getWorkTopic() throws Exception{
    	UserCommon userCommon =(UserCommon) session.get(UserAction.USER_SESSION);
    	if(userCommon !=null)
    	{
    	workTopicList=workTopicService.getWorkTopicByUserCommonId(userCommon.getId());
    	 return null;
    	}
    	else {
			return "login";
		}
    }
    /**
     * 获取课程卷组
     * @param courseId
     * @return
     * @throws Exception 
     */
    public String[][] getWorkTopic(String courseId) throws Exception{
    	if(!courseId.equals("undefined"))
    	 {
    		try {
				int course =Integer.parseInt(courseId);
				return workTopicService.getWorkTopicByCourseId(course);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
    	 }
    	else {
    	 	return null;
		}
   
    }
    public String addInfo(){
    	
    	return "addInfo";
    }
    public boolean del(int id) throws Exception{
    	return workTopicService.delete(id);
    }
    public boolean addForm(String name,String topicIds,String typeIds,int courseId,HttpSession session) throws Exception{
    	
    	try {
			System.out.print("选择的题目ID串为："+topicIds+"题型串为："+typeIds);
			UserCommon userCommon=(UserCommon) session.getAttribute(UserAction.USER_SESSION);
			if(userCommon !=null && userCommon.getRule()==2)   //判断是否登录以及登录权限
			{
			    	if(name !=null && topicIds !=null && typeIds !=null)
			    		return workTopicService.addWork(name,topicIds,typeIds,userCommon.getId(),courseId);
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
    public String addWorkTopic(){
    	return "add";
    }
    public String index(){   	
    	return "index";
    }
    

	public List<WorkTopic> getWorkTopicList() {
		return workTopicList;
	}

	public void setWorkTopicList(List<WorkTopic> workTopicList) {
		this.workTopicList = workTopicList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public WorkTopicService getWorkTopicService() {
		return workTopicService;
	}
	public void setWorkTopicService(WorkTopicService workTopicService) {
		this.workTopicService = workTopicService;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		session=arg0;
	}
	
	
    
}
