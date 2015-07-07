package com.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ws.common.PageBean;
import com.ws.common.commonUtil;
import com.ws.dao.CourseChapterDao;
import com.ws.dao.CourseDao;
import com.ws.dao.FillAnswerDao;
import com.ws.dao.QuestionTypeDao;
import com.ws.dao.TopicDao;
import com.ws.dao.TopicFillDao;
import com.ws.dao.TopicTypeDao;
import com.ws.po.CheckBoxTopic;
import com.ws.po.Course;
import com.ws.po.CourseChapter;
import com.ws.po.FillAnswer;
import com.ws.po.FillTopic;
import com.ws.po.QuestionType;
import com.ws.po.Topic;
import com.ws.po.TopicType;
import com.ws.service.TopicFillService;

@Component("topicFillService")
public class TopicFillServiceImpl implements TopicFillService {

	@Resource
	private TopicDao topicDao;
	@Resource
	private CourseDao courseDao;
	@Resource
	private QuestionTypeDao questionTypeDao;
	@Resource
	private TopicFillDao topicFillDao;
	@Resource
	private CourseChapterDao courseChapterDao;
	@Resource
	private TopicTypeDao topicTypeDao;
	@Resource
	private FillAnswerDao fillAnswerDao;
	@Override
	public PageBean getList(int pageSize, int page, int courseId)
			throws Exception {
		try {
			int allRow=topicFillDao.getAllRow(courseId);  //获取总行数
			int totalPage=PageBean.countTotalPage(pageSize, allRow);  //设置总页数
			final int offset = PageBean.countOffset(pageSize, page);//当前页开始记录
            final int length = pageSize;//每页记录数
            final int currentPage = PageBean.countCurrentPage(page);
            List<CheckBoxTopic> checkTopicList =topicFillDao.getList(offset,length,courseId);
            PageBean pageBean = new PageBean();
			pageBean.setPageSize(pageSize);
			pageBean.setCurrentPage(currentPage);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(checkTopicList);
			pageBean.init();
			return pageBean;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public boolean addTopic(String title, String[] answer, String decription,
			int courseId, int i, int topicType, String topicSource, int courseChapter,String tid) throws Exception {
		commonUtil.p("添加填空题service");
		Topic topic =new Topic();
		FillTopic fillTopic=new FillTopic();
		topic.setTitleString(title);
		topic.setDescription(decription);
		topic.setSourceString(topicSource);
		CourseChapter chapter=courseChapterDao.getChapter(courseChapter);
		if(chapter == null)
			return false;
		topic.setChapter(chapter);
 		if(tid.matches("\\d\\d-\\d\\d-\\d\\d\\d\\d")) //判断是否符合格式
 	    	topic.setTid(tid);//设置自编编号
 		else {
			return false;
		}
		TopicType topicType2=topicTypeDao.getTopicType(topicType);
		if (topicType2 == null) {
			return false;
		}
		topic.setTopicType(topicType2);
		try {
			Course course =courseDao.getCourseById(courseId);
			QuestionType questionType=questionTypeDao.getQuestionTypeById(i);
			if(course !=null && questionType !=null){
				topic.setCourse(course);
				topic.setType(questionType);
				try {
					Topic topic2=topicDao.insetTopic(topic);
					if(topic2 != null)
					{
						fillTopic.setTopic(topic2);
						List<FillAnswer>fillAnswers=new ArrayList<FillAnswer>();
						for (int j = 0; j < answer.length; j++) {
							FillAnswer fillAnswer=new FillAnswer();
							fillAnswer.setBody(answer[j]);
							fillAnswer.setOrderInt(j);
							fillAnswer.setFillTopic(fillTopic);
							fillAnswers.add(fillAnswer);		
						}
						System.out.print("填空题答案对象个数"+fillAnswers.size());
						fillTopic.setAnswers(fillAnswers);
						return topicFillDao.inset(fillTopic);
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
	public Boolean batchImport(String[][] result, String courseId)
			throws Exception {
		
		try {
			boolean re[] =new boolean[result.length];
			String answer[] =new String[result[0].length-2];
			if(courseId.length()>0)
			{
				int courseIdInt=Integer.parseInt(courseId);
				for (int i = 0; i < result.length; i++) {
					if(result[i][0].equals(""))
						continue;
					String tid=result[i][0];
					String title=result[i][1];
					String topicTypeString=result[i][2];
					int topicType =Integer.parseInt(topicTypeString);
					String chapterString =result[i][3];
					int chapter =Integer.parseInt(chapterString);
					String descriptionString =result[i][4];
					String topicSource = result[i][5];
					int k=0;
				    for (int j = 6; j < result[i].length; j++) {
						answer[k]=result[i][j];
						k++;
					}
						if(title.length()>1)
						{
							re[i]=this.addTopic(title, answer, descriptionString, courseIdInt, 3, topicType, topicSource, chapter, tid);
						}
					
				}
				for (int i = 0; i < re.length; i++) {
					if(!re[i])
						return false;
				}
				return true;
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
	public boolean[] checkAnswer(String[] answer, int tidInt) throws Exception {
		
        boolean[] result = new boolean[answer.length];
		FillTopic fillTopic = topicFillDao.getFillTopicById(tidInt);
		long topic = fillTopic.getTopic().getId();
		topicDao.addsum(topic);
		List<FillAnswer>fillAnswers =fillTopic.getAnswers();
		commonUtil.p("填空题查询到答案个数为"+fillAnswers.size());
		for (int i = 0; i < answer.length; i++) {	
               String answerBody = fillAnswers.get(i).getBody();
               if (answer[i].equals(answerBody)) {
				result[i]=true;
			}
             else {
				result[i] = false;
			}
		}
	if (result.equals(false)) {
				topicDao.addError(topic);	
	  }
	   return result;	
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

	public TopicFillDao getTopicFillDao() {
		return topicFillDao;
	}

	public void setTopicFillDao(TopicFillDao topicFillDao) {
		this.topicFillDao = topicFillDao;
	}
	@Override
	public boolean delete(long tid) throws Exception {
		// TODO Auto-generated method stub
		FillTopic fillTopic = topicFillDao.getFillTopicById(tid);
		if (fillTopic!= null) {
			return topicFillDao.delete(fillTopic);
		}else {
			return false;
		}
	}
	@Override
	public boolean update(long id, String title, String[] answer,
			String decription, int i,int topicType,String topicSource,int courseChapter,String tid) throws Exception {

		FillTopic fillTopic=topicFillDao.getFillTopicById(id); 
		commonUtil.p("修改Id为"+fillTopic.getId()+"填空题");
		Topic topic =fillTopic.getTopic();
		topic.setTitleString(title);
		topic.setDescription(decription);
		topic.setSourceString(topicSource);
		CourseChapter chapter=courseChapterDao.getChapter(topicType);
		if(chapter == null)
			return false;
		topic.setChapter(chapter);
 		if(tid.matches("\\d\\d-\\d\\d-\\d\\d\\d\\d")) //判断是否符合格式
 	    	topic.setTid(tid);//设置自编编号
 		else {
			return false;
		}
		TopicType topicType2=topicTypeDao.getTopicType(topicType);
		if (topicType2 == null) {
			return false;
		}
		topic.setTopicType(topicType2);
		try {
						fillTopic.setTopic(topic);
						List<FillAnswer>oldAnswers=fillTopic.getAnswers();
						for(FillAnswer f:oldAnswers){
							fillAnswerDao.delete(f);
						}
						fillTopic.setAnswers(null);//删除原有答案
						List<FillAnswer>fillAnswers=new ArrayList<FillAnswer>();
						for (int j = 0; j < answer.length; j++) {
							FillAnswer fillAnswer=new FillAnswer();
							fillAnswer.setBody(answer[j]);
							fillAnswer.setOrderInt(j);
							fillAnswer.setFillTopic(fillTopic);
							fillAnswers.add(fillAnswer);	
						}
                        commonUtil.p("修改填空题");
						fillTopic.setAnswers(fillAnswers);
						return topicFillDao.inset(fillTopic);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public FillTopic getFillTopic(long fillTopicId) throws Exception {
		// TODO Auto-generated method stub
		return topicFillDao.getFillTopicById(fillTopicId);
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
