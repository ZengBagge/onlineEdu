package com.ws.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.common.PageBean;
import com.ws.common.commonUtil;
import com.ws.po.RedioTopic;
import com.ws.po.UserCommon;
import com.ws.service.TopicRedioService;

@Component("topicRedioAction")
@Scope("prototype")
public class TopicRedioAction extends ActionSupport implements ServletRequestAware,SessionAware  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private TopicRedioService topicRedioService;
	private List<RedioTopic> redios;
	private int courseId;
	private HttpServletRequest request;
	private int page;//第几页
    private PageBean pageBean;//包含分布信息的bean
    private Map<String, Object>session;
    private int redioTopicId;
    private RedioTopic redioTopic;
    private Logger logger = Logger.getLogger("TopicRedioAction");
    
    
    
    public String index(){
    	return "index";
    }
    
	/*
	 * 添加单选题
	 */
	public boolean addForm(String title,String asA,String asB,String asC,String asD,String asE,String as,String decription,int topicType,String topicSource,int chapter,String tid,int courseId) throws Exception {
		System.out.print(title);
		try {
			int asInt=Integer.parseInt(as);
			logger.info("课程ID为"+asInt);
			if(courseId !=0)
			return topicRedioService.addTopic(title,asA,asB,asC,asD,asE,asInt,decription,courseId,1,topicType,topicSource,chapter,tid);
			else {
				logger.error("courseId为空");
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public String getRedioList() throws Exception{
		courseId=(int) request.getAttribute("courseId");
		logger.info("查询多选题库列表的课程ID是"+courseId);
		pageBean=topicRedioService.getList(10,page,courseId);
	   return "index";
	}
/**
 * 验证单选题答案
 * @throws Exception 
 */
	public String[] checkForAnswer(int answer,int tid,int workId,HttpSession session) throws Exception{
	
		String[] result =new String[2];		
		logger.info("检查单选题"+tid+"答案,所选答案是+"+answer);
	try {
		       UserCommon userCommon =	(UserCommon) session.getAttribute(UserAction.USER_SESSION);
				if(topicRedioService.checkAnswer(answer,tid))
				  {
					result[0]="1";
					result[1]="答案正确";
				  }
				else {
					result[0] = "0";
					result[1] = "答案错误";
				}
          //开新线程添加无返回记录
		   try {
			new Thread(new TopicNotesAction(userCommon, Integer.parseInt(result[0]), tid, workId)).start();
		} catch (Exception e) {
			logger.error("记录作答结果线程启动过程中出现错误");
			e.printStackTrace();
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
	
  public boolean del(int tid)throws Exception{
	  return topicRedioService.delete(tid);
  }
  public String update() throws Exception{
	  if(redioTopicId !=0)
		 this.redioTopic=  topicRedioService.getRedioTopic(redioTopicId);
	  if(redioTopic !=null)
		  return "updateRedio";
	  else {
		return ERROR;
	}
  }
  public boolean updateFrom(int id,String title,String asA,String asB,String asC,String asD,String asE,int as,String decription,int topicType,String topicSource, int chapter,String tid)throws Exception{
	  try {
		  logger.info("课程ID为"+as);
			return topicRedioService.update(id,title,asA,asB,asC,asD,asE,as,decription,1,topicType,topicSource,chapter,tid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
  }
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request=arg0;
	}
	
	public TopicRedioService getTopicRedioService() {
		return topicRedioService;
	}

	public void setTopicRedioService(TopicRedioService topicRedioService) {
		this.topicRedioService = topicRedioService;
	}


	public List<RedioTopic> getRedios() {
		return redios;
	}
	public void setRedios(List<RedioTopic> redios) {
		this.redios = redios;
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
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

	public int getRedioTopicId() {
		return redioTopicId;
	}

	public void setRedioTopicId(int redioTopicId) {
		this.redioTopicId = redioTopicId;
	}

	public RedioTopic getRedioTopic() {
		return redioTopic;
	}

	public void setRedioTopic(RedioTopic redioTopic) {
		this.redioTopic = redioTopic;
	}

	
	
}
