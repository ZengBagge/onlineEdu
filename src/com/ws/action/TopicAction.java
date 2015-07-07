package com.ws.action;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.common.commonUtil;
import com.ws.po.Topic;
import com.ws.service.TopicCheckBoxService;
import com.ws.service.TopicFillService;
import com.ws.service.TopicJudgeService;
import com.ws.service.TopicRedioService;
import com.ws.service.TopicService;

@Component("topicAction")
@Scope("prototype")
public class TopicAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int mode; //选择列表模式
	private int addMode;//添加模板模式
	private int addCourse;//提交题目所属课程
	private List<Topic>topics; //单选题库
	private HttpServletRequest request;
	@Resource
	private TopicRedioService topicRedioService;
	@Resource
	private TopicCheckBoxService topicCheckBoxService;
	@Resource
	private TopicFillService topicFillService;
	@Resource
	private TopicJudgeService topicJudgeService;
	@Resource
	private TopicService topicService;
	
	public String index(){
		return "index";
	}
	public String list() throws Exception{
		System.out.print("模式ID为："+mode);
		System.out.print("课程ID为："+addCourse);
		 switch(mode){
	        case 1 : {return "redio";}
	        case 2:{return "checkBox";}
	        case 3:return "fill";
	        case 4: return "judge";
	        default:return null;
	        }
	}
	public String add(){
		System.out.print("添加模式ID为："+addMode);
		System.out.print("添加课程ID为："+addCourse);
        switch(addMode){
        case 1 : {return "redioAdd";}
        case 2:{return "checkBoxAdd";}
        case 3:return "fillAdd";
        case 4: return "judgeAdd";
        default:return null;
        }
        
	}
	/**
	 * 批量上传处理
	 * @param fileTransfer
	 * @return
	 * @throws Exception 
	 */
  public Boolean batchTopic(String fileName,String courseId,String typeId ) throws Exception{
	  String path="/usr/local/tomcat/webapps/WebStudy/upload/"+fileName;
	  System.out.print("导入文件路径："+path);
	  String[][] result=null;
	try {
		result = commonUtil.excelTopicForm(path);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   if(result == null)
		   return false;
	   else {
		   System.out.print("导入数据行数："+result.length+"导入数据列数"+result[0].length);
		   switch (typeId) {
	         case "1": {return topicRedioService.batchImport(result, courseId);}
	         case "2":{return topicCheckBoxService.batchImport(result, courseId);}
	         case "3":{return topicFillService.batchImport(result, courseId);}
	         case "4":{return topicJudgeService.batchImport(result, courseId);}
	default:{
		return false;}
	}
	}
  }
  /**
   * 根据课程ID获取题目
   * @return
 * @throws Exception 
   */
  public String getTopicByCourseId() throws Exception{
	  String courseIdString= (String) request.getAttribute("courseId");
	  System.out.print("查询题库，课程Id为"+courseIdString);
	  if(!courseIdString.equals("undefined"))
	    {
		  int courseId=Integer.parseInt(courseIdString);
		  topics= topicService.getTopicByCourseIdAndByTypeId(courseId);
	    }
	  return null;
  }
  
  
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public int getAddMode() {
		return addMode;
	}
	public void setAddMode(int addMode) {
		this.addMode = addMode;
	}
	public int getAddCourse() {
		return addCourse;
	}
	public void setAddCourse(int addCourse) {
		this.addCourse = addCourse;
	}
   
	public List<Topic> getTopics() {
		return topics;
	}
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
	public TopicRedioService getTopicRedioService() {
		return topicRedioService;
	}
	public void setTopicRedioService(TopicRedioService topicRedioService) {
		this.topicRedioService = topicRedioService;
	}
	public TopicCheckBoxService getTopicCheckBoxService() {
		return topicCheckBoxService;
	}
	public void setTopicCheckBoxService(TopicCheckBoxService topicCheckBoxService) {
		this.topicCheckBoxService = topicCheckBoxService;
	}
	public TopicFillService getTopicFillService() {
		return topicFillService;
	}
	public void setTopicFillService(TopicFillService topicFillService) {
		this.topicFillService = topicFillService;
	}
	public TopicJudgeService getTopicJudgeService() {
		return topicJudgeService;
	}
	public void setTopicJudgeService(TopicJudgeService topicJudgeService) {
		this.topicJudgeService = topicJudgeService;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request=arg0;
	}
	public TopicService getTopicService() {
		return topicService;
	}
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
   
	
}
