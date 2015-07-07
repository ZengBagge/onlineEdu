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
import com.ws.po.FillTopic;
import com.ws.service.TopicFillService;

@Component("topicFillAction")
@Scope("prototype")
public class TopicFillAction extends ActionSupport implements ServletRequestAware,SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private TopicFillService topicFillService;
	private List<FillTopic> fillTopics;
	private int courseId;
	private HttpServletRequest request;
	private int page;//第几页
    private PageBean pageBean;//包含分布信息的bean
    private Map<String, Object>session;
    private int fillTopicId;
    private FillTopic fillTopic;
    
	public boolean addForm(String title,String[] answer,String decription,int topicType ,String topicSource,int courseChapter,String tid,int courseId) throws Exception {
		System.out.print(title);
		System.out.print("填空题答案个数为："+answer.length);
		try {
			System.out.print("课程ID为"+courseId);
			return topicFillService.addTopic(title,answer,decription,courseId,3,topicType,topicSource,courseChapter,tid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public String getFillList() throws Exception{
		courseId=(int) request.getAttribute("courseId");
		pageBean=topicFillService.getList(5,page,courseId);
	   return "index";
	}
	public boolean updateFrom(int id,String title,String[] answer,String decription,int topicType,String topicSource,int courseChapter,String tid) throws Exception {
		System.out.print(title);
		System.out.print("填空题答案个数为："+answer.length);
		try {
			System.out.print("课程ID为"+courseId);
			return topicFillService.update(id,title,answer,decription,3,topicType,topicSource,courseChapter,tid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public String update() throws Exception{
		  if(fillTopicId !=0)
				 this.fillTopic=  topicFillService.getFillTopic(fillTopicId);
			  if(fillTopic !=null)
				  return "updateFill";
			  else {
				return ERROR;
			}
	}
	public boolean del(int tid)throws Exception{
		
		return topicFillService.delete(tid);
	}
	/**
	 * 验证填空题答案
	 * @throws Exception 
	 */
		public String[] checkForAnswer(String answer[],int tid) throws Exception{
			String[] result =new String[2];		
			try {
					if(answer.length>0)
					{	
					for (int i = 0; i < answer.length; i++) {
						commonUtil.p(answer[i]);	
					}
					boolean[] r =topicFillService.checkAnswer(answer,tid);
                       result[0] = "1";
                       result[1]= "";
   					for (int i = 0; i < r.length; i++) {
						if (r[i] == true) {
					     result[1] += "1,";		
						}else {
							result[1] +="0,";
						}
					}
					}else {
						result[0] = "0";
						result[1] = "参数错误";
					}
			
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				commonUtil.p(e);
				result[0] = "0";
				result[1] = "未知错误";
			}
			return result;
		}

	public TopicFillService getTopicFillService() {
		return topicFillService;
	}
	public void setTopicFillService(TopicFillService topicFillService) {
		this.topicFillService = topicFillService;
	}
	public List<FillTopic> getFillTopics() {
		return fillTopics;
	}
	public void setFillTopics(List<FillTopic> fillTopics) {
		this.fillTopics = fillTopics;
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
		request = arg0;
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
		this.session = arg0;
	}
	public int getFillTopicId() {
		return fillTopicId;
	}
	public void setFillTopicId(int fillTopicId) {
		this.fillTopicId = fillTopicId;
	}
	public FillTopic getFillTopic() {
		return fillTopic;
	}
	public void setFillTopic(FillTopic fillTopic) {
		this.fillTopic = fillTopic;
	}


	
	
}
