package com.ws.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ws.dao.CollectTopicDao;
import com.ws.dao.StudentUserDao;
import com.ws.dao.TopicDao;
import com.ws.po.CollectTopic;
import com.ws.po.StudentUser;
import com.ws.po.Topic;
import com.ws.service.CollectTopicService;

@Component("collectTopicService")
public class CollectTopicServiceImpl implements CollectTopicService {

	@Resource
	private CollectTopicDao collectTopicDao;
	@Resource
	private StudentUserDao studentUserDao;
	@Resource
	private TopicDao topicDao;
	@Override
	public boolean addCollect(long tid, Long id) throws Exception {
		
		StudentUser studentUser = studentUserDao.getStudentUserByUserCommon(id);
		Topic topic =  topicDao.getTopicById(tid);
		if (studentUser != null && topic !=null) {
			CollectTopic collectTopic = new CollectTopic();
			collectTopic.setStudentUser(studentUser);
			collectTopic.setAddDate(new Date());
			collectTopic.setTopic(topic);
			return collectTopicDao.insert(collectTopic);
		}else {
			return false;
		}
	}

	@Override
	public boolean isCollected(long tid, Long id) throws Exception {
		StudentUser studentUser = studentUserDao.getStudentUserByUserCommon(id);
		Topic topic =  topicDao.getTopicById(tid);
		if (studentUser != null && topic !=null) {
			return collectTopicDao.isCollected(tid,studentUser.getId());
		}else {
			return false;
		}
		
	}

}
