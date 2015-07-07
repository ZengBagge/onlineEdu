package com.ws.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ws.dao.TopicDao;
import com.ws.po.Topic;
import com.ws.service.TopicService;

@Component("topicServiceImpl")
public class TopicServiceImpl implements TopicService {

	@Resource
	private TopicDao topicDao;
	@Override
	public List<Topic> getTopicByCourseIdAndByTypeId(int courseId)
			throws Exception {
		// TODO Auto-generated method stub
		return topicDao.getTopics(courseId);
	}
	public TopicDao getTopicDao() {
		return topicDao;
	}
	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}

	
}
