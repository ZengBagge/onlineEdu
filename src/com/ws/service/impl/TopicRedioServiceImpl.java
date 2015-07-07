package com.ws.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import com.ws.common.PageBean;
import com.ws.dao.CourseChapterDao;
import com.ws.dao.CourseDao;
import com.ws.dao.QuestionTypeDao;
import com.ws.dao.TopicDao;
import com.ws.dao.TopicRedioDao;
import com.ws.dao.TopicTypeDao;
import com.ws.po.Course;
import com.ws.po.CourseChapter;
import com.ws.po.QuestionType;
import com.ws.po.RedioTopic;
import com.ws.po.Topic;
import com.ws.po.TopicType;
import com.ws.service.TopicRedioService;

@Component("topicRedioService")
public class TopicRedioServiceImpl implements TopicRedioService  {

	@Resource
	private TopicRedioDao topicRedioDao;
	@Resource
	private TopicDao topicDao;
	@Resource
	private CourseDao courseDao;
	@Resource
	private QuestionTypeDao questionTypeDao;
	@Resource
	private CourseChapterDao courseChapterDao;
	@Resource 
	private TopicTypeDao topicTypeDao;
	
	public static Logger logger = Logger.getLogger("TopicRedioServiceImpl");
	@Override
	public boolean addTopic(String title, String asA, String asB, String asC,
			String asD, String asE, int as, String description, int courseId,
			int typeId, int topicType, String topicSource, int chapter,String tid) throws Exception {
		Topic topic=new Topic();
		RedioTopic redioTopic =new RedioTopic();
		topic.setTitleString(title);  //设置题干
		QuestionType questionType=questionTypeDao.getQuestionTypeById(typeId);//获取题型
		Course course=courseDao.getCourseById(courseId);//获取课程
		topic.setCourse(course);
		topic.setType(questionType);
		topic.setDescription(description);
		topic.setSourceString(topicSource);
		CourseChapter chapterObject=courseChapterDao.getChapter( chapter);
		if(chapterObject == null)
			return false;
		topic.setChapter(chapterObject);
		TopicType topicType2=topicTypeDao.getTopicType(topicType);
		if (topicType2 == null) {
			return false;
		}
		topic.setTopicType(topicType2);
 		if(tid.matches("\\d\\d-\\d\\d-\\d\\d\\d\\d")) //判断是否符合格式
 	    	topic.setTid(tid);//设置自编编号
 		else {
			return false;
		}
		
		 try {
			Topic topic2=topicDao.insetTopic(topic);
			if(topic2 != null){
				redioTopic.setAnswer(as);
				redioTopic.setTopic(topic2);
				redioTopic.setAnswerA(asA);
				redioTopic.setAnswerB(asB);
				redioTopic.setAnswerC(asC);
				redioTopic.setAnswerD(asD);
				redioTopic.setAnswerE(asE);
				return topicRedioDao.insertTopic(redioTopic);
			}
			else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public PageBean getList(int pageSize,int page,int courseId) throws Exception {
		// TODO Auto-generated method stub
		try {
			int allRow=topicRedioDao.getAllRow(courseId);  //获取总行数
			int totalPage=PageBean.countTotalPage(pageSize, allRow);  //设置总页数
			final int offset = PageBean.countOffset(pageSize, page);//当前页开始记录
            final int length = pageSize;//每页记录数
            final int currentPage = PageBean.countCurrentPage(page);
            List<RedioTopic> redioTopicList =topicRedioDao.getList(offset,length,courseId);
            PageBean pageBean = new PageBean();
			pageBean.setPageSize(pageSize);
			pageBean.setCurrentPage(currentPage);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(redioTopicList);
			pageBean.init();
			return pageBean;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean batchImport(String[][] result, String courseId) {
		
		int  courseIdInt=Integer.parseInt(courseId);
		boolean[] re = new boolean[result.length];
		try {
			Course course=courseDao.getCourseById(courseIdInt);
			if(course != null){
			for (int i = 0; i < result.length; i++) {
				if(result[i][0].equals(""))
					continue;
				String tid=result[i][0];
				String title=result[i][1];
				String topicTypeString=result[i][2];
				logger.info("题目级别是"+topicTypeString);
				int topicType =Integer.parseInt(topicTypeString);
				String chapterString =result[i][3];
				int chapter =Integer.parseInt(chapterString);
				String as1=result[i][4];
				String as2=result[i][5];
				String as3=result[i][6];
				String as4=result[i][7];
				String as5=result[i][8];
				String asString=result[i][9];
				int as = Integer.parseInt(asString);
				String description=result[i][10];
				String topicSource=result[i][11];
             re[i]= this.addTopic(title, as1, as2, as3, as4, as5, as, description, courseIdInt, 1, topicType, topicSource, chapter, tid);
			}
			}
			else {
				return false;
			}
			for (int i = 0; i < re.length; i++) {
				if(!re[i])
					return false;
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	@Override
	public boolean checkAnswer(int answerInt, int tidInt) throws Exception {
		
		RedioTopic redioTopic = topicRedioDao.getRedioTopicById(tidInt);
		int answerTrue = redioTopic.getAnswer();
		long topic = redioTopic.getTopic().getId();
		topicDao.addsum(topic);  //练习次数加一
		logger.info("验证单选题答案，标准答案"+answerTrue+"用户答案"
				+ answerInt);
		if (answerInt == answerTrue) {
			return true;
		}else {
			topicDao.addError(topic); //错误次数加一
			return false;
		}
	}
	
	
	
	public TopicRedioDao getTopicRedioDao() {
		return topicRedioDao;
	}

	public void setTopicRedioDao(TopicRedioDao topicRedioDao) {
		this.topicRedioDao = topicRedioDao;
	}

	public TopicDao getTopicDao() {
		return topicDao;
	}

	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}

	public CourseDao getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	public QuestionTypeDao getQuestionTypeDao() {
		return questionTypeDao;
	}

	public void setQuestionTypeDao(QuestionTypeDao questionTypeDao) {
		this.questionTypeDao = questionTypeDao;
	}

	@Override
	public boolean delete(long tid) throws Exception {
		// TODO Auto-generated method stub
		return  topicRedioDao.delete(tid);
	}

	@Override
	public boolean update(long id, String title, String asA, String asB,
			String asC, String asD, String asE, int as, String decriptionint,
			int type, int topicType, String topicSource,int courseChapter,String tid) throws Exception  {
		RedioTopic redioTopic =topicRedioDao.getRedioTopicById(id);
		if(redioTopic !=null){
		Topic topic = redioTopic.getTopic();
		topic.setTitleString(title);  //设置题干
		QuestionType questionType=questionTypeDao.getQuestionTypeById(type);//获取题型
		topic.setType(questionType);
		topic.setSourceString(topicSource);
		CourseChapter chapter = courseChapterDao.getChapter(courseChapter);
		if (chapter == null) {
			return false;
		}
		topic.setChapter(chapter);
		TopicType topicType2=topicTypeDao.getTopicType(topicType);
		if (topicType2 == null) {
			return false;
		}
		topic.setTopicType(topicType2);
 		if(tid.matches("\\d\\d-\\d\\d-\\d\\d\\d\\d")) //判断是否符合格式
 	    	topic.setTid(tid);//设置自编编号
 		else {
			return false;
		}
		topic.setDescription(decriptionint);
				redioTopic.setAnswer(as);
				redioTopic.setTopic(topic);
				redioTopic.setAnswerA(asA);
				redioTopic.setAnswerB(asB);
				redioTopic.setAnswerC(asC);
				redioTopic.setAnswerD(asD);
				redioTopic.setAnswerE(asE);
				return topicRedioDao.insertTopic(redioTopic);
		}else {
			return false;
		}
		
		}
       
	@Override
	public RedioTopic getRedioTopic(long redioTopicId) throws Exception {
		// TODO Auto-generated method stub
		return topicRedioDao.getRedioTopicById(redioTopicId);
	}

	public CourseChapterDao getCourseChapterDao() {
		return courseChapterDao;
	}

	public void setCourseChapterDao(CourseChapterDao courseChapterDao) {
		this.courseChapterDao = courseChapterDao;
	}

	public TopicTypeDao getTopicTypeDao() {
		return topicTypeDao;
	}

	public void setTopicTypeDao(TopicTypeDao topicTypeDao) {
		this.topicTypeDao = topicTypeDao;
	}




	}

