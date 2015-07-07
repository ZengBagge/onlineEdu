package com.ws.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.common.PageBean;
import com.ws.common.commonUtil;
import com.ws.po.JudgeTopic;
import com.ws.service.TopicJudgeService;


@Component("topicJudgeAction")
@Scope("prototype")
public class TopicJudgeAction extends ActionSupport implements ServletRequestAware,SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private TopicJudgeService topicJudgeService;
	private List<JudgeTopic> judgeTopics;
	private int courseId;
	private HttpServletRequest request;
	private int page;//第几页
    private PageBean pageBean;//包含分布信息的bean
    private Map<String, Object>session;
    private int judgeTopicId;
    private JudgeTopic judgeTopic;
    
	public boolean addForm(String title,int answer,String decription,int topicType,String topicSource,int courseChapter,String tid,int courseId) throws Exception {
		System.out.print(title);
		try {
			System.out.print("课程ID为"+courseId+"答案为："+answer);
			return topicJudgeService.addTopic(title,answer,decription,courseId,4,topicType,topicSource,courseChapter,tid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public String getJudgeList() throws Exception{
		courseId=(int) request.getAttribute("courseId");
		pageBean=topicJudgeService.getList(10,page,courseId);
	   return "index";
	}
	public boolean del(int tid)throws Exception{
		return topicJudgeService.delete(tid);
	}
	public boolean updateFrom( int id, String title,int answer,String decription,int topicType,String topicSource, int courseChapter,String tid) {
		try {
			System.out.print("课程ID为"+courseId+"答案为："+answer);
			return topicJudgeService.update(id,title,answer,decription,4,topicType,topicSource,courseChapter,tid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public String update() throws Exception{
		  if(judgeTopicId !=0)
		  {  
			 this.judgeTopic=  topicJudgeService.getJudge(judgeTopicId);
			  if(judgeTopic !=null)
				  return "updateJudge";
			  else {
				return ERROR;
			}
		  }else {
			return ERROR;
		}		  
	  }
	/**
	 * 验证判断题答案
	 * @throws Exception 
	 */
		public String[] checkForAnswer(int answer,int tid) throws Exception{
			String[] result =new String[2];		
			try {
                   commonUtil.p("用户判断答案为"+answer);
					if(topicJudgeService.checkAnswer(answer,tid))
					  {
						result[0]="1";
						result[1]="答案正确";
					  }
					else {
						result[0] = "0";
						result[1] = "答案错误";
					}
			
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				commonUtil.p(e);
				result[0] = "0";
				result[1] = "未知错误";
			}
			return result;
		}
	
	public TopicJudgeService getTopicJudgeService() {
		return topicJudgeService;
	}
	public void setTopicJudgeService(TopicJudgeService topicJudgeService) {
		this.topicJudgeService = topicJudgeService;
	}
	public List<JudgeTopic> getJudgeTopics() {
		return judgeTopics;
	}
	public void setJudgeTopics(List<JudgeTopic> judgeTopics) {
		this.judgeTopics = judgeTopics;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request=arg0;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
	  this.session = arg0;	
	}
	public int getJudgeTopicId() {
		return judgeTopicId;
	}
	public void setJudgeTopicId(int judgeTopicId) {
		this.judgeTopicId = judgeTopicId;
	}
	public JudgeTopic getJudgeTopic() {
		return judgeTopic;
	}
	public void setJudgeTopic(JudgeTopic judgeTopic) {
		this.judgeTopic = judgeTopic;
	}
	
	
}
