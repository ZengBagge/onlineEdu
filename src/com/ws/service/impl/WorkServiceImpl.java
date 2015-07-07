package com.ws.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ws.common.commonUtil;
import com.ws.dao.StudentUserDao;
import com.ws.dao.TopicCheckBoxDao;
import com.ws.dao.TopicDao;
import com.ws.dao.TopicFillDao;
import com.ws.dao.TopicJudgeDao;
import com.ws.dao.TopicRedioDao;
import com.ws.dao.WorkDao;
import com.ws.dao.WorkTopicDao;
import com.ws.po.Classes;
import com.ws.po.QuestionType;
import com.ws.po.StudentUser;
import com.ws.po.Topic;
import com.ws.po.Work;
import com.ws.po.WorkTopic;
import com.ws.service.WorkService;

@Component("workService")
public class WorkServiceImpl implements WorkService {

	@Resource
	private WorkDao workDao;
	@Resource
	private StudentUserDao studentUserDao;
	@Resource
	private TopicRedioDao topicRedioDao;
	@Resource
	private TopicCheckBoxDao topicCheckBoxDao;
	@Resource
	private TopicFillDao topicFillDao;
	@Resource
	private TopicJudgeDao topicJudgeDao;
	@Resource
	private WorkTopicDao workTopicDao;
	@Resource
	private TopicDao topicDao;
	
	@Override
	public List<Work> getWorkingByCourseId(int courseId) throws Exception {
		
		List<Work>works= workDao.getWorking(courseId,new Date());
		return works;
	}

	@Override
	public List<Work> getWorkedByCourseId(int courseId) throws Exception {
		
		return workDao.getWorked(courseId,new Date());
	}
	@Override
	public WorkTopic getWorkTopicFromWork(int workId) throws Exception {
		
		try {
			Work work = workDao.getWorkById(workId);
			if (work != null) {
				return work.getWorkTopic();
			}
			else {
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public Work getWorkById(int workId) throws Exception {
		// TODO Auto-generated method stub
		return workDao.getWorkById(workId);
	}

	@Override
	public Boolean isMajorWork(Long id,int workId) throws Exception {
		
		StudentUser studentUser =  studentUserDao.getStudentUserByUserCommon(id);
		Classes myMajor =studentUser.getClasses();
		Work work = workDao.getWorkById(workId);
		List<Classes> majors =work.getClasses();
		for (Classes m:majors) {
			if (m.getId() == myMajor.getId()) {
				return true;
			}
		}
		return false;
	}


	@Override
	public List<Object> getTopicFormat(WorkTopic workTopic) throws Exception {

		List<Topic>topics = workTopic.getTopicList();
		List<Object>results=new ArrayList<Object>();
	
		commonUtil.removeDuplicate(topics);

		for(Topic t:topics){
			long topicId= t.getId();
            int type=t.getType().getId();
           Object topic = null;
         if (type == 1) {
        	 topic = topicRedioDao.getRedioByTopic(topicId);
		}else if (type == 2) {
			  topic = topicCheckBoxDao.getCheckBoxByTopic(topicId);
		}else if (type == 3) {
			  topic = topicFillDao.getFillByTopic(topicId);
		}else if (type == 4) {
			  topic = topicJudgeDao.getJudgeByTopic(topicId);
		}else {
			return null;
		}
           results.add(topic);

		}
		return results;
	}

	@Override
	public WorkTopic getRandomTopic(int courseId) throws Exception{
		
		try {
			WorkTopic workTopic = new WorkTopic();
			List<Topic>topics = new ArrayList<Topic>();
			List<QuestionType>questionTypes = new ArrayList<QuestionType>();
			List<Topic>topics2 = topicDao.getTopics(courseId);
			int sum = topics2.size();
			int min =1; int max= (int) (sum*Math.random());
			List<Integer>old = new ArrayList<Integer>();
			commonUtil.p("生成随机数的个数为"+max);
			for(int i=0;i<max;i++)
			{
			Random random = new Random();  
			int randomNumber =  random.nextInt(sum)%(sum-min+1) + min;
			 if(!old.contains(randomNumber))
			old.add(randomNumber);
			 }
			commonUtil.p("生成随机数的个数为"+old.size());
			for(Integer i:old){
				topics.add(topics2.get(i));
			}
			for(Topic t:topics){
				QuestionType questionType=t.getType();
				if (!questionTypes.contains(questionType)) {
					questionTypes.add(questionType);
				}		
			}
			workTopic.setTopicList(topics);
			workTopic.setQuestionTypes(questionTypes);
			return workTopic;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	

	public WorkDao getWorkDao() {
		return workDao;
	}

	public void setWorkDao(WorkDao workDao) {
		this.workDao = workDao;
	}

	
	public StudentUserDao getStudentUserDao() {
		return studentUserDao;
	}

	public void setStudentUserDao(StudentUserDao studentUserDao) {
		this.studentUserDao = studentUserDao;
	}
	

	public TopicRedioDao getTopicRedioDao() {
		return topicRedioDao;
	}

	public void setTopicRedioDao(TopicRedioDao topicRedioDao) {
		this.topicRedioDao = topicRedioDao;
	}

	public TopicCheckBoxDao getTopicCheckBoxDao() {
		return topicCheckBoxDao;
	}

	public void setTopicCheckBoxDao(TopicCheckBoxDao topicCheckBoxDao) {
		this.topicCheckBoxDao = topicCheckBoxDao;
	}

	public TopicFillDao getTopicFillDao() {
		return topicFillDao;
	}

	public void setTopicFillDao(TopicFillDao topicFillDao) {
		this.topicFillDao = topicFillDao;
	}

	public TopicJudgeDao getTopicJudgeDao() {
		return topicJudgeDao;
	}

	public void setTopicJudgeDao(TopicJudgeDao topicJudgeDao) {
		this.topicJudgeDao = topicJudgeDao;
	}

	public WorkTopicDao getWorkTopicDao() {
		return workTopicDao;
	}

	public void setWorkTopicDao(WorkTopicDao workTopicDao) {
		this.workTopicDao = workTopicDao;
	}

	public TopicDao getTopicDao() {
		return topicDao;
	}

	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}




	
	
}
