package com.ws.action;

import org.apache.log4j.Logger;
import com.ws.po.UserCommon;
import com.ws.service.TopicCheckNotesService;
import com.ws.service.impl.TopicCheckNotesServiceImpl;

public class TopicNotesAction  implements Runnable{

	 private UserCommon userCommon;
	 private int state;
	 private long tid;
	 private int workId;
	 private static Logger logger = Logger.getLogger("TopicNotesAction");
	 private TopicCheckNotesService topicCheckNotesService= new TopicCheckNotesServiceImpl();
	 
	 
	 public TopicCheckNotesService getTopicCheckNotesService() {
		return topicCheckNotesService;
	}
	public void setTopicCheckNotesService(
			TopicCheckNotesService topicCheckNotesService) {
		this.topicCheckNotesService = topicCheckNotesService;
	}
	public TopicNotesAction(UserCommon userCommon,int state,long tid,int workId){
	     this.userCommon = userCommon;
	     this.state = state;
	     this.tid = tid;
	     this.workId =workId;
	 }
	@Override
	public void run() {
		try {
			logger.info("答题结果记录线程启动");
			topicCheckNotesService.add(userCommon,state,tid,workId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
}	
