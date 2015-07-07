package com.ws.action;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.opensymphony.xwork2.ActionSupport;
import com.ws.po.ExamTopic;
import com.ws.po.UserCommon;
import com.ws.service.ExamTopicService;

@Component("examTopicAction")
@Scope("prototype")
public class ExamTopicAction extends ActionSupport implements SessionAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
    private ExamTopicService examTopicService;
	private String courseId;
    private Map<String, Object> session;
    private List<ExamTopic>examTopics;
	public String index(){
		
		return"index";
	}
	   public String addExamTopic(){
	    	return "add";
	    }
	public String addInfo(){
		return "addInfo";
	}
	
	public boolean addForm(String title,String[][] types,int courseId,HttpSession session) throws Exception{
		
		UserCommon userCommon = (UserCommon) session.getAttribute(UserAction.USER_SESSION);
		if(userCommon!=null && userCommon.getRule()==2)
		{
		return examTopicService.addExamTopic(title,types,userCommon,courseId);
		}else {
			return false;
		}
	}
	 /**
	    * 获取教师卷组
	    * @return
	    * @throws Exception
	    */
	    public  String getExamTopic() throws Exception{
	    	UserCommon userCommon =(UserCommon) session.get("user");
	    	if(userCommon !=null)
	    	{
	    		examTopics=examTopicService.getExamTopicByUserCommonId(userCommon.getId());
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
	    public String[][] getExamTopic(String courseId) throws Exception{
	    	if(!courseId.equals("undefined"))
	    	 {
	    		try {
					int course =Integer.parseInt(courseId);
					return examTopicService.getExamTopicByCourseId(course);
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

		public boolean del(int id) throws Exception{
			try {
				return examTopicService.delete(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}	
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public ExamTopicService getExamTopicService() {
		return examTopicService;
	}
	public void setExamTopicService(ExamTopicService examTopicService) {
		this.examTopicService = examTopicService;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session=arg0;
	}
	public List<ExamTopic> getExamTopics() {
		return examTopics;
	}
	public void setExamTopics(List<ExamTopic> examTopics) {
		this.examTopics = examTopics;
	}
	public Map<String, Object> getSession() {
		return session;
	}

	
	
}
