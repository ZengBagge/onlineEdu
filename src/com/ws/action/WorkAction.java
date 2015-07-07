package com.ws.action;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.common.commonUtil;
import com.ws.po.Course;
import com.ws.po.Topic;
import com.ws.po.UserCommon;
import com.ws.po.Work;
import com.ws.po.WorkJournal;
import com.ws.po.WorkTopic;
import com.ws.service.CollectTopicService;
import com.ws.service.CourseService;
import com.ws.service.WorkJournalService;
import com.ws.service.WorkService;

@Component("workAction")
@Scope("prototype")
public class WorkAction extends ActionSupport implements SessionAware,ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object>session;
	private HttpServletRequest request;
	private Set<Course> courses;
	@Resource
	private CourseService courseService;
	@Resource
	private WorkService workService;
	@Resource
	private WorkJournalService workJournalService;
	@Resource
	private CollectTopicService collectTopicService;
	private int courseId=0;
	private List<Object> topicFormat;
	private List<Work>workingList;
	private List<Work>workedList;
	private int workId=0;
	private int number = 0;
	private String token;
	private Work work;
	private WorkTopic workTopic;
	private int workTopicSize;
	public static Logger logger = Logger.getLogger("WorkAction");
	
	public String classIndex() throws Exception{
		UserCommon userCommon = (UserCommon) session.get(UserAction.USER_SESSION);
		if(userCommon != null && userCommon.getRule() == 1)
		courses = courseService.getCourseByStudentUser(userCommon.getId());
		return "classIndex";
	}
	
	
	public void getWorking(){
		  try {
				UserCommon userCommon = (UserCommon) session.get(UserAction.USER_SESSION);
				if(userCommon != null && userCommon.getRule() == 1){
			     int courseId= (int) request.getAttribute("courseId");
			     logger.info("获取进行中任务课程ID"+courseId);
				List<Work>works= workService.getWorkingByCourseId(courseId);
				List<WorkJournal>workJournals = workJournalService.getWorkJournalsByCourseAndUserCommon(courseId, userCommon.getId());
				for(Work work:works){
					for (WorkJournal workJournal:workJournals) {
						if(work.getId() == workJournal.getWork().getId())
						{
							work.setIsComplate(1);
						}
					}
				}
				workingList =works;
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void getWorked(){
		 try {
			 UserCommon userCommon = (UserCommon) session.get(UserAction.USER_SESSION);
				if(userCommon != null && userCommon.getRule() == 1){
			     int courseId= (int) request.getAttribute("courseId");
			     logger.info("获取进行中任务课程ID"+courseId);
				List<Work>works= workService.getWorkedByCourseId(courseId);
				List<WorkJournal>workJournals = workJournalService.getWorkJournalsByCourseAndUserCommon(courseId, userCommon.getId());
				for(Work work:works){
					for (WorkJournal workJournal:workJournals) {
						if(work.getId() == workJournal.getWork().getId())
						{
							work.setIsComplate(1);
						}
					}
				}
				workedList =works;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public String selfIndex() throws Exception{
		UserCommon userCommon = (UserCommon) session.get(UserAction.USER_SESSION);
		if(userCommon != null && userCommon.getRule() == 1)
		courses = courseService.getCourseByStudentUser(userCommon.getId());
		return "selfIndex";
	}
	
	public String workInfo() throws Exception{
		if(workId != 0)
		{	
		this.work = workService.getWorkById(workId);
		this.token=commonUtil.getRandomString(16); //参生安全Token
		session.put("token", token);
		logger.info("产生Token+"+token);
		return "workInfo";   
		}else {
			return ERROR;
		}
	}
	public String conduct()throws Exception{
		String tokenString=(String) session.get("token");
		if(tokenString == null ||  !tokenString.equals(token))
		{
			return ERROR;
		}
		if (workId != 0) {
			UserCommon userCommon = (UserCommon) session.get(UserAction.USER_SESSION);
			if (userCommon != null && userCommon.getRule() == 1) {
		       Boolean isWorkedBoolean= workJournalService.isWorkJournal(workId, userCommon.getId());
		       Boolean isMajorWorkBoolean =workService.isMajorWork(userCommon.getId(),workId);  //验证是否属于任务专业
		       if (false == isWorkedBoolean && true == isMajorWorkBoolean) {
		    	  workTopic = workService.getWorkTopicFromWork(workId); 
                  workTopicSize =workTopic.getTopicList().size();
		    	  List<Topic> topics = workTopic.getTopicList();
				    	  int i =1;
				    	  for (Topic t: topics) {
							t.setSort(i);
							i++;
						}//设置题号
			     topicFormat = workService.getTopicFormat(workTopic); //题目集合
			     logger.info("查询到题目信息共计"+topicFormat.size()+"个。");
			     logger.info(workTopic.getTitleString());
				return "conduct";
			}
		       else {
				return ERROR;
			}
			}else {
				return ERROR;
			}
		
		}
		else {
			return ERROR;
		}
	}


	
	public boolean handPaper(int[] result,int times,int workId,HttpSession session)throws Exception{
		logger.info("交卷中，考试时间"+times+"秒，做了"+result.length+"道题目");
		try {
			UserCommon userCommon = (UserCommon) session.getAttribute(UserAction.USER_SESSION);
			if(userCommon != null && userCommon.getRule() == 1)
			 {
				if(workJournalService.addWorkJournal(workId, userCommon.getId(),times,result))
				{
					this.token=null;
					session.setAttribute("token", null);
					return true;
				}else {
					return false;
				}
			 }else{
				 return false;
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public String selfCollectTopic() throws Exception{
		if (courseId != 0) {
			workTopic = workService.getRandomTopic(courseId);
			if(workTopic != null)
			{
			topicFormat = workService.getTopicFormat(workTopic); //题目集合
			 List<Topic> topics = workTopic.getTopicList();
	    	  int i =1;
	    	  for (Topic t: topics) {
				t.setSort(i);
				i++;
			}//设置题号
			return "collectTopic";
			}else {
				return ERROR;
			}
		}else {
			return ERROR;
		}
		
	}
	

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session =arg0;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	public CourseService getCourseService() {
		return courseService;
	}
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
	public List<Work> getWorkingList() {
		return workingList;
	}
	public void setWorkingList(List<Work> workingList) {
		this.workingList = workingList;
	}
	public List<Work> getWorkedList() {
		return workedList;
	}
	public void setWorkedList(List<Work> workedList) {
		this.workedList = workedList;
	}
	public WorkService getWorkService() {
		return workService;
	}
	public void setWorkService(WorkService workService) {
		this.workService = workService;
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
	public WorkJournalService getWorkJournalService() {
		return workJournalService;
	}
	public void setWorkJournalService(WorkJournalService workJournalService) {
		this.workJournalService = workJournalService;
	}
	public WorkTopic getWorkTopic() {
		return workTopic;
	}
	public void setWorkTopic(WorkTopic workTopic) {
		this.workTopic = workTopic;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public List<Object> getTopicFormat() {
		return topicFormat;
	}
	public void setTopicFormat(List<Object> topicFormat) {
		this.topicFormat = topicFormat;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public CollectTopicService getCollectTopicService() {
		return collectTopicService;
	}
	public void setCollectTopicService(CollectTopicService collectTopicService) {
		this.collectTopicService = collectTopicService;
	}
	public int getWorkTopicSize() {
		return workTopicSize;
	}
	public void setWorkTopicSize(int workTopicSize) {
		this.workTopicSize = workTopicSize;
	}




	
}
