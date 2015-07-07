package com.ws.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.common.commonUtil;
import com.ws.po.Course;
import com.ws.po.UserCommon;
import com.ws.service.CourseService;
import com.ws.service.WorkJournalService;

@Component("courseAction")
@Scope("prototype")
public class CourseAction extends ActionSupport  implements SessionAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private CourseService courseService;
	private Course co;
	private List<Course>courseList;
	private Map<String, Object> session;  
	private int courseId;
	private int moth;
	@Resource
	private WorkJournalService workJournalService;
	public String index(){
		return "index";
	}

	public String getCourse() throws Exception{
		 
		UserCommon userCommon=(UserCommon)session.get("user");
		if(userCommon!=null)
			{
					String uidString=userCommon.getUid();
					courseList = (List<Course>) courseService.getCourseByUid(uidString);
					System.out.print(uidString);
			}
		return null;
	}
   
	/**
	 * 修改课程信息
	 * @return
	 * @throws Exception
	 */
	public String infoSet()throws Exception{
		co=courseService.getCourseById(courseId);
		if(co !=null)
		{
			System.out.print(co.getTitleString());
		return "set";
		}
		else {
			return "error";
		}
	}
	public boolean setForm(int courseId,String courseName,String courseTest,String courseIntroduce,String courseMajors,String courseQuestionType,HttpSession session) throws Exception{
		System.out.print(courseIntroduce);
		UserCommon userCommon=(UserCommon)session.getAttribute(UserAction.USER_SESSION);
		if(userCommon !=null &&userCommon.getRule() == 2){
			if(courseMajors.isEmpty())
				return false;
			else			
			   return	courseService.setCourse(courseId,courseName,courseTest,courseIntroduce,courseMajors, courseQuestionType);
		}
		else {
			return false;
		}
	}
	/**
	 * 添加课程
	 */
	public String addCourse(){
		return "add";
	}
	public boolean addForm(String courseName,String courseTest,String courseIntroduce,String courseMajors,String courseQuestionType,HttpSession session) throws Exception{
		System.out.print(courseIntroduce);
		UserCommon userCommon=(UserCommon)session.getAttribute(UserAction.USER_SESSION);
		if(userCommon !=null &&userCommon.getRule() == 2){
			if(courseMajors.isEmpty())
				return false;
			else			
			   return	courseService.addCourse(courseName,courseTest,courseIntroduce,courseMajors,userCommon.getId(),courseQuestionType);
		}
		else {
			return false;
		}
	}
	
	public String courseIndex() throws Exception{
		try {
			if (courseId != 0) {
				  commonUtil.p("课程显示模式"+moth);
					co=courseService.getCourseById(courseId);
					UserCommon userCommon = (UserCommon) session.get(UserAction.USER_SESSION);
					if (userCommon !=null && userCommon.getRule() ==1) {
						return "courseIndex";		
					}
					else {
						return ERROR;
					}		
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
	
	public String courseInfo(){
		try {
			if (courseId != 0) {
					co=courseService.getCourseById(courseId);
					UserCommon userCommon = (UserCommon) session.get(UserAction.USER_SESSION);
					if (userCommon !=null && userCommon.getRule() ==2) {
						return "courseInfo";		
					}
					else {
						return ERROR;
					}		
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
	/********************以下为set get方法***************************/
	public CourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;	
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public Course getCo() {
		return co;
	}

	public void setCo(Course co) {
		this.co = co;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}


	public WorkJournalService getWorkJournalService() {
		return workJournalService;
	}

	public void setWorkJournalService(WorkJournalService workJournalService) {
		this.workJournalService = workJournalService;
	}

	public int getMoth() {
		return moth;
	}

	public void setMoth(int moth) {
		this.moth = moth;
	}


   
     
 	
	
}
