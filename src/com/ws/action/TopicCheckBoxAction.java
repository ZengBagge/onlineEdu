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
import com.ws.po.CheckBoxTopic;
import com.ws.service.TopicCheckBoxService;

@Component("topicCheckBoxAction")
@Scope("prototype")
public class TopicCheckBoxAction extends ActionSupport implements ServletRequestAware,SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private TopicCheckBoxService topicCheckBoxService;
	private List<CheckBoxTopic> checkBoxTopics;
	private int courseId;
    private HttpServletRequest request;
	private int page=1;//第几页
    private PageBean pageBean;//包含分布信息的bean
    private Map<String, Object>session;
    private int checkBoxTopicId;
    private CheckBoxTopic checkBoxTopic;
    
	public boolean addForm(String title,String[] answerTrue,String[] answer,String decription,int topicType,String topicSource,int courseChapter,String tid,int courseId) throws Exception {
		System.out.print(title);
		try {
			System.out.print("课程ID为"+courseId);
			return topicCheckBoxService.addTopic(title,answerTrue,answer,decription,courseId,2,topicType,topicSource,courseChapter,tid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public String getCheckList() throws Exception{
		courseId=(int) request.getAttribute("courseId");
		System.out.print("查询多选题库列表的课程ID是"+courseId);
		pageBean=topicCheckBoxService.getList(10,page,courseId);
	   return "index";
	}
	public boolean del(int tid)throws Exception{
		return topicCheckBoxService.delete(tid);
	}
	
	  public String update() throws Exception{
		  if(checkBoxTopicId !=0)
			 this.checkBoxTopic=  topicCheckBoxService.getCheckBox(checkBoxTopicId);
		  if(checkBoxTopic !=null)
			  return "updateCheckBox";
		  else {
			return ERROR;
		}
	  }
	public boolean updateFrom(int id, String title,String[] answerTrue,String[] answer,String decription,int topicType,String topicSource,int courseChapter,String tid) throws Exception {
		try {
			return topicCheckBoxService.update(id,title,answerTrue,answer,decription,2,topicType,topicSource,courseChapter,tid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 验证单选题答案
	 * @throws Exception 
	 */
		public String[] checkForAnswer(String answer[],int tid) throws Exception{
			String[] result =new String[2];		
			try {
					if(answer.length>0 )
					{	
						commonUtil.p("验证多选题答案，所选答案个数"+answer.length);
						if(topicCheckBoxService.checkAnswer(answer,tid))
						  {
							result[0]="1";
							result[1]="答案正确";
						  }
						else {
							result[0] = "0";
							result[1] = "答案错误";
						}
					
					}else {
						result[0] = "0";
						result[1] = "参数错误";
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

	public TopicCheckBoxService getTopicCheckBoxService() {
		return topicCheckBoxService;
	}
	public void setTopicCheckBoxService(TopicCheckBoxService topicCheckBoxService) {
		this.topicCheckBoxService = topicCheckBoxService;
	}
	public List<CheckBoxTopic> getCheckBoxTopics() {
		return checkBoxTopics;
	}
	public void setCheckBoxTopics(List<CheckBoxTopic> checkBoxTopics) {
		this.checkBoxTopics = checkBoxTopics;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request=arg0;
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
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session =arg0;
	}
	public int getCheckBoxTopicId() {
		return checkBoxTopicId;
	}
	public void setCheckBoxTopicId(int checkBoxTopicId) {
		this.checkBoxTopicId = checkBoxTopicId;
	}
	public CheckBoxTopic getCheckBoxTopic() {
		return checkBoxTopic;
	}
	public void setCheckBoxTopic(CheckBoxTopic checkBoxTopic) {
		this.checkBoxTopic = checkBoxTopic;
	}
	
	
}
