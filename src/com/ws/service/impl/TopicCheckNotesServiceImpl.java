package com.ws.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import com.ws.dao.TopicCheckNotesDao;
import com.ws.dao.TopicDao;
import com.ws.dao.WorkDao;
import com.ws.po.Topic;
import com.ws.po.TopicCheckNotes;
import com.ws.po.UserCommon;
import com.ws.po.Work;
import com.ws.service.TopicCheckNotesService;

@Component("topicCheckNotesService")
public class TopicCheckNotesServiceImpl implements TopicCheckNotesService {

   @Resource
	private TopicCheckNotesDao topicCheckNotesDao; 
	@Resource
	private TopicDao topicDao;
	@Resource
	private WorkDao workDao;
	@Override
	public void add(UserCommon userCommon, int state, long tid, int workId)
			throws Exception {
     		
         Topic topic = topicDao.getTopicById(tid);
         if (topic !=null) {
			Work work = workDao.getWorkById(workId);
			if (work !=null) {
				TopicCheckNotes topicCheckNotes = new TopicCheckNotes();
				topicCheckNotes.setUserCommon(userCommon);
				topicCheckNotes.setTopic(topic);
				topicCheckNotes.setWork(work);
				topicCheckNotes.setAddDate(new Date());
				topicCheckNotesDao.save(topicCheckNotes);
			}
		}
	}

}
