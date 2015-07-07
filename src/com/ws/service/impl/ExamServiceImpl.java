package com.ws.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ws.common.commonUtil;
import com.ws.dao.ExamDao;
import com.ws.dao.ExamQuestionTypeDao;
import com.ws.dao.ExamTopicDao;
import com.ws.dao.StudentUserDao;
import com.ws.dao.TopicCheckBoxDao;
import com.ws.dao.TopicDao;
import com.ws.dao.TopicFillDao;
import com.ws.dao.TopicJudgeDao;
import com.ws.dao.TopicRedioDao;
import com.ws.po.Classes;
import com.ws.po.Exam;
import com.ws.po.ExamQuestionType;
import com.ws.po.ExamTopic;
import com.ws.po.QuestionType;
import com.ws.po.StudentUser;
import com.ws.po.Topic;
import com.ws.service.ExamService;

@Component("examService")
public class ExamServiceImpl implements ExamService {

	@Resource
	private ExamDao examDao;
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
	private ExamTopicDao examTopicDao;
	@Resource
	private TopicDao topicDao;
	@Resource
	private ExamQuestionTypeDao examQuestionTypeDao;
	@Override
	public List<Exam> getExamingByCourseId(int courseId) throws Exception {
		List<Exam>exams= examDao.getExaming(courseId,new Date());
		return exams;
	}

	@Override
	public List<Exam> getExamedByCourseId(int courseId) throws Exception {
		List<Exam>exams= examDao.getExamed(courseId,new Date());
		return exams;
	}
	
	@Override
	public Exam getExamById(int examId) throws Exception {

		return examDao.getExamById(examId);
	}
	
	@Override
	public List<Topic> getExamTopicFromExam(int examId) throws Exception {
		
		try {
			Exam exam=examDao.getExamById(examId);
			commonUtil.p("查询到考试任务"+exam.getTitleString());
			ExamTopic examTopic = exam.getExamTopic();
			commonUtil.p("查询到组卷"+examTopic.getTitleString());
			List<ExamQuestionType> examQuestionTypes = examQuestionTypeDao.getListByExam(examTopic.getId());
			commonUtil.p("查询到组卷题型共"+examQuestionTypes.size());
			List<Topic>topics = new ArrayList<Topic>();
			int courseId= examTopic.getCourse().getId();
			for(ExamQuestionType e:examQuestionTypes){
				QuestionType questionType=e.getQuestionType();
				int number =e.getNumber();
				int type=questionType.getId();
					List<Topic>topics2=topicDao.getTopicByRandAndCourseId(type,number,courseId);
					if (topics2 != null) {
					    topics.addAll(topics2);		
					} 
			}
			commonUtil.p("查询到题目共计topics"+topics.size());
		 return topics;	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Object> getTopicFormat(List<Topic> examTopic) throws Exception {
		
		List<Object>results=new ArrayList<Object>();
		commonUtil.p("待查询题目信息共计"+examTopic.size()+"条");
		commonUtil.removeDuplicate(examTopic);
		commonUtil.p("待查询题目信息共计"+examTopic.size()+"条");
		for(Topic t:examTopic){
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
           commonUtil.p("查询到的题目对象为"+topic);
		}
		return results;
	}

	@Override
	public Boolean isMajorExam(Long id, int examId) throws Exception {
		
		StudentUser studentUser =  studentUserDao.getStudentUserByUserCommon(id);
		Classes myMajor =studentUser.getClasses();
		Exam exam= examDao.getExamById(examId);
		List<Classes> majors =exam.getClasses();
		for (Classes m:majors) {
			if (m.getId() == myMajor.getId()) {
				return true;
			}
		}
		return false;
	}
	
	public ExamDao getExamDao() {
		return examDao;
	}

	public void setExamDao(ExamDao examDao) {
		this.examDao = examDao;
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

	public ExamTopicDao getExamTopicDao() {
		return examTopicDao;
	}

	public void setExamTopicDao(ExamTopicDao examTopicDao) {
		this.examTopicDao = examTopicDao;
	}

	public TopicDao getTopicDao() {
		return topicDao;
	}

	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}

	public ExamQuestionTypeDao getExamQuestionTypeDao() {
		return examQuestionTypeDao;
	}

	public void setExamQuestionTypeDao(ExamQuestionTypeDao examQuestionTypeDao) {
		this.examQuestionTypeDao = examQuestionTypeDao;
	}



	

	
}
