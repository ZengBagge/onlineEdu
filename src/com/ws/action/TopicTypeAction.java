package com.ws.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.common.commonUtil;
import com.ws.po.TopicType;
import com.ws.po.UserCommon;
import com.ws.service.TopicTypeService;

@Component("topicTypeAction")
@Scope("prototype")
public class TopicTypeAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<TopicType>topicTypes;
	@Resource
	private TopicTypeService topictypeService;
    private Map<String, Object>session;
    
	public void getTopicType() throws Exception{
		try {
			UserCommon userCommon = (UserCommon) session.get(UserAction.USER_SESSION);
			this.topicTypes = topictypeService.getTopicTypeList(userCommon.getId());
			commonUtil.p("查询到博客类别"+topicTypes.size()+"条");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   	

	public TopicTypeService getTopictypeService() {
		return topictypeService;
	}

	public void setTopictypeService(TopicTypeService topictypeService) {
		this.topictypeService = topictypeService;
	}

	public List<TopicType> getTopicTypes() {
		return topicTypes;
	}

	public void setTopicTypes(List<TopicType> topicTypes) {
		this.topicTypes = topicTypes;
	}



	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
	   this.session = arg0;	
	}
}
