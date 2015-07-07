package com.ws.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.ws.common.PageBean;
import com.ws.common.commonUtil;
import com.ws.dao.CourseChapterDao;
import com.ws.dao.CourseDao;
import com.ws.dao.QuestionTypeDao;
import com.ws.dao.TopicDao;
import com.ws.dao.TopicJudgeDao;
import com.ws.dao.TopicTypeDao;
import com.ws.po.Course;
import com.ws.po.CourseChapter;
import com.ws.po.JudgeTopic;
import com.ws.po.QuestionType;
import com.ws.po.Topic;
import com.ws.po.TopicType;
import com.ws.service.TopicJudgeService;

@Component("topicJudgeService")
public class TopicJudgeServiceImpl implements TopicJudgeService {
	
	@Resource
	private CourseDao courseDao;
	@Resource
	private QuestionTypeDao questionTypeDao;
	@Resource
	private TopicJudgeDao topicJudgeDao;
	
	@Resource
	private TopicDao topicDao;
	@Resource
	private CourseChapterDao chapterDao;
@Resource
private TopicTypeDao topicTypeDao;
	@Override
	public PageBean getList(int pageSize, int page, int courseId)
			throws Exception {
		try {
			int allRow=topicJudgeDao.getAllRow(courseId);  //获取总行数
			int totalPage=PageBean.countTotalPage(pageSize, allRow);  //设置总页数
			final int offset = PageBean.countOffset(pageSize, page);//当前页开始记录
            final int length = pageSize;//每页记录数
            final int currentPage = PageBean.countCurrentPage(page);
            List<JudgeTopic> checkTopicList =topicJudgeDao.getList(offset,length,courseId);
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
	public boolean addTopic(String title, int answerInt, String description,
			int courseId, int i,int topicType,String topicSource,int courseChapter,String tid) throws Exception {
		
		Topic topic =new Topic();
		JudgeTopic judgeTopic=new JudgeTopic();
		topic.setTitleString(title);
		topic.setDescription(description);
		try {
			Course course=courseDao.getCourseById(courseId);
			QuestionType questionType=questionTypeDao.getQuestionTypeById(i);
			if(course !=null && questionType !=null){
				topic.setCourse(course);
				topic.setType(questionType);
				topic.setSourceString(topicSource);
				CourseChapter chapter = chapterDao.getChapter(courseChapter);
				if(chapter == null){
					commonUtil.p("章节信息获取错误");
					return false;
				}
				topic.setChapter(chapter);
		 		if(tid.matches("\\d\\d-\\d\\d-\\d\\d\\d\\d")) //判断是否符合格式
		 		{
		 	    	topic.setTid(tid);//设置自编编号
		 		}	
		 		else {
		 			commonUtil.p("编号格式错误");
					return false;
				}
				TopicType topicType2=topicTypeDao.getTopicType(topicType);
				if (topicType2 == null) {
					commonUtil.p("题目等级获取错误");
					return false;
				}
				topic.setTopicType(topicType2);
				try {
					Topic topic2 =topicDao.insetTopic(topic);
					if(topic2 != null)
					{
						judgeTopic.setTopic(topic2);
						judgeTopic.setAnswer(answerInt);
						return topicJudgeDao.insert(judgeTopic);
					}
					else
						return false;
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


	/**
	 * 批量导入
	 */
	@Override
	public Boolean batchImport(String[][] result, String courseId)
			throws Exception {
		
		try {
			boolean re[] =new boolean[result.length];
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
					String answer=result[i][4];
					String description =result[i][5];
					String topicSource=result[i][6];
					if (answer.length()>0) {
						int answerInt =Integer.parseInt(answer);
						if(tid.length()>1)
						{
							re[i]= this.addTopic(title, answerInt, description, courseIdInt, 4, topicType, topicSource, chapter, tid);
						}
					}
					else {
					return false;
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
	public boolean checkAnswer(int answer, int tidInt) throws Exception {
		
		JudgeTopic judgeTopic = topicJudgeDao.getJudgeById(tidInt);
		long topic = judgeTopic.getTopic().getId();
		topicDao.addsum(topic);
		if (judgeTopic.getAnswer() == answer) {
			return true;
		}else {
			topicDao.addError(topic);
			return false;
			
		}
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

	public TopicJudgeDao getTopicJudgeDao() {
		return topicJudgeDao;
	}

	public void setTopicJudgeDao(TopicJudgeDao topicJudgeDao) {
		this.topicJudgeDao = topicJudgeDao;
	}

	public TopicDao getTopicDao() {
		return topicDao;
	}

	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}

	@Override
	public boolean delete(long tid) throws Exception {
		// TODO Auto-generated method stub
		JudgeTopic judgeTopic=topicJudgeDao.getJudgeById(tid);
		if (judgeTopic!=null) {
			return topicJudgeDao.delete(judgeTopic);
		}else {
			return false;
		}
	}

	@Override
	public boolean update(long id, String title, int answer, String decription,
			 int i,int topicType,String topicSource,int sourceChapter,String tid) throws Exception {

		JudgeTopic judgeTopic=topicJudgeDao.getJudgeById(id);
		Topic topic =judgeTopic.getTopic();
		topic.setTitleString(title);
		topic.setDescription(decription);
		try {
			QuestionType questionType=questionTypeDao.getQuestionTypeById(i);
			if(questionType !=null){
				topic.setType(questionType);
				topic.setSourceString(topicSource);
				CourseChapter chapter = chapterDao.getChapter(sourceChapter);
				if(chapter == null){
					return false;
				}
				topic.setChapter(chapter);
		 		if(tid.matches("\\d\\d-\\d\\d-\\d\\d\\d\\d")) //判断是否符合格式
		 		{
		 	    	topic.setTid(tid);//设置自编编号
		 		}	
		 		else {
					return false;
				}
				TopicType topicType2=topicTypeDao.getTopicType(topicType);
				if (topicType2 == null) {
					return false;
				}
				topic.setTopicType(topicType2);
				judgeTopic.setTopic(topic);
				judgeTopic.setAnswer(answer);
				return topicJudgeDao.insert(judgeTopic);
					}
					else
						return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public JudgeTopic getJudge(long judgeTopicId) throws Exception {
		// TODO Auto-generated method stub
		commonUtil.p("获取判断题对象");
		return topicJudgeDao.getJudgeById(judgeTopicId);
	}

	public CourseChapterDao getChapterDao() {
		return chapterDao;
	}

	public void setChapterDao(CourseChapterDao chapterDao) {
		this.chapterDao = chapterDao;
	}

	public TopicTypeDao getTopicTypeDao() {
		return topicTypeDao;
	}

	public void setTopicTypeDao(TopicTypeDao topicTypeDao) {
		this.topicTypeDao = topicTypeDao;
	}
	
}
