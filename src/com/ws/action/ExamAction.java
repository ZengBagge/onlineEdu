package com.ws.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.common.ComparatorTopic;
import com.ws.common.commonUtil;
import com.ws.po.Course;
import com.ws.po.Exam;
import com.ws.po.ExamJournal;
import com.ws.po.QuestionType;
import com.ws.po.Topic;
import com.ws.po.UserCommon;
import com.ws.service.CourseService;
import com.ws.service.ExamJournalService;
import com.ws.service.ExamService;

@Component("examAction")
@Scope("prototype")
public class ExamAction extends ActionSupport implements SessionAware,ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Map<String, Object>session;
    private HttpServletRequest request;
    private Set<Course>courses;
	private List<Exam>workingList;
	private List<Exam>workedList;
	private int examId=0;
	private List<Object>topicFormat;
	private List<Topic> examTopic;
	private List<QuestionType>questionTypes;
	private String token;
	private Exam exam;
	private int topicFormatSize;
    @Resource
    private CourseService courseService;
    @Resource
    private ExamService examService;
    @Resource
    private ExamJournalService examJournalService;
    public String  index() throws Exception {
    	UserCommon userCommon = (UserCommon) session.get(UserAction.USER_SESSION);
		if(userCommon != null && userCommon.getRule() == 1)
			{
			this.courses = courseService.getCourseByStudentUser(userCommon.getId());
			return "index";
			}else {
				return ERROR;
			}
	}
    
    public void getExaming()throws Exception{
    	  try {
				UserCommon userCommon = (UserCommon) session.get(UserAction.USER_SESSION);
				if(userCommon != null && userCommon.getRule() == 1){
			     int courseId= (int) request.getAttribute("courseId");
					commonUtil.p("获取进行中任务课程ID"+courseId);
				List<Exam>exams= examService.getExamingByCourseId(courseId);
				List<ExamJournal>ExamJournals = examJournalService.getExamJournalsByCourseAndUserCommon(courseId, userCommon.getId());
				for(Exam exam:exams){
					for (ExamJournal ExamJournal:ExamJournals) {
						if(exam.getId() == ExamJournal.getExam().getId())
						{
							exam.setIsComplate(1);
						}
					}
				}
				workingList =exams;
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void getExamed(){
    	 try {
				UserCommon userCommon = (UserCommon) session.get(UserAction.USER_SESSION);
				if(userCommon != null && userCommon.getRule() == 1){
			     int courseId= (int) request.getAttribute("courseId");
					commonUtil.p("获取进行中任务课程ID"+courseId);
				List<Exam>Exams= examService.getExamedByCourseId(courseId);
				List<ExamJournal>ExamJournals = examJournalService.getExamJournalsByCourseAndUserCommon(courseId, userCommon.getId());
				for(Exam exam:Exams){
					for (ExamJournal ExamJournal:ExamJournals) {
						if(exam.getId() == ExamJournal.getExam().getId())
						{
							exam.setIsComplate(1);
						}
					}
				}
				workingList =Exams;
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public String examInfo() throws Exception{

    	if(examId != 0)
		{	
		this.exam = examService.getExamById(examId);
		this.token=commonUtil.getRandomString(16); //参生安全Token
		session.put("token", token);
		session.put("examTime", exam.getExamTimes());
		commonUtil.p("产生Token+"+token);
		return "examInfo";  
		}else {
			return ERROR;
		}
    }
    
    public String conduct()throws Exception{
    	String tokenString=(String) session.get("token");
    	int examTime=(int) session.get("examTime");
    	commonUtil.p("examTime"+examTime);
		if(tokenString == null || !tokenString.equals(token))
		{
			commonUtil.p("session验证错误");
			return ERROR;
		}
		if (examId != 0) {
			UserCommon userCommon = (UserCommon) session.get(UserAction.USER_SESSION);
			if (userCommon != null && userCommon.getRule() == 1) {
		       Boolean isExamedBoolean= examJournalService.isExamJournal(examId, userCommon.getId());  //是否已完成任务
		       Boolean isMajorExamBoolean =examService.isMajorExam(userCommon.getId(),examId);  //验证是否属于任务专业
		       if (false == isExamedBoolean && true == isMajorExamBoolean) {
		    	  examTopic = examService.getExamTopicFromExam(examId); 
				    	  ComparatorTopic comparator=new ComparatorTopic(); //设置排序根据
				    	  Collections.sort(examTopic, comparator); //排序
		    	         List<QuestionType>qList=new ArrayList<QuestionType>();
				    	  int i =1;
				    	  for (Topic t: examTopic) {
							t.setSort(i);
							i++;
							QuestionType q = t.getType();
							if(!qList.contains(q))
							{
								qList.add(q);
							}
							
						}//设置题号
				   	  
				  questionTypes=qList;  	  
			     topicFormat = examService.getTopicFormat(examTopic); //题目集合
			     topicFormatSize =topicFormat.size();
			     commonUtil.p("查询到题目信息共计"+topicFormat.size()+"个。");
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
    
    public boolean handPaper(int[] result,int timerc,int examId,HttpSession session){
            	
    	commonUtil.p("交卷中，考试时间剩余"+timerc+"秒，做了"+result.length+"道题目");
		try {
			UserCommon userCommon = (UserCommon) session.getAttribute(UserAction.USER_SESSION);
			if(userCommon != null && userCommon.getRule() == 1)
			 {
				int examTime=(int) session.getAttribute("examTime");
				int timeUse=examTime*60-timerc;
				if(examJournalService.addExamJournal(examId, userCommon.getId(),timeUse,result))
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
    
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session=arg0;
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
	public Map<String, Object> getSession() {
		return session;
	}

	public List<Exam> getWorkingList() {
		return workingList;
	}

	public void setWorkingList(List<Exam> workingList) {
		this.workingList = workingList;
	}

	public List<Exam> getWorkedList() {
		return workedList;
	}

	public void setWorkedList(List<Exam> workedList) {
		this.workedList = workedList;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public ExamService getExamService() {
		return examService;
	}

	public void setExamService(ExamService examService) {
		this.examService = examService;
	}

	public ExamJournalService getExamJournalService() {
		return examJournalService;
	}

	public void setExamJournalService(ExamJournalService examJournalService) {
		this.examJournalService = examJournalService;
	}

	
	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Object> getTopicFormat() {
		return topicFormat;
	}

	public void setTopicFormat(List<Object> topicFormat) {
		this.topicFormat = topicFormat;
	}

	public List<Topic> getExamTopic() {
		return examTopic;
	}

	public void setExamTopic(List<Topic> examTopic) {
		this.examTopic = examTopic;
	}

	public List<QuestionType> getQuestionTypes() {
		return questionTypes;
	}

	public void setQuestionTypes(List<QuestionType> questionTypes) {
		this.questionTypes = questionTypes;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	public int getTopicFormatSize() {
		return topicFormatSize;
	}

	public void setTopicFormatSize(int topicFormatSize) {
		this.topicFormatSize = topicFormatSize;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request=arg0;
	}
   
}
