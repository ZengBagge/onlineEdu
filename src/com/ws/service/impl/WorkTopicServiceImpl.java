package com.ws.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ws.dao.CourseDao;
import com.ws.dao.QuestionTypeDao;
import com.ws.dao.TeacherUserDao;
import com.ws.dao.TopicDao;
import com.ws.dao.WorkTopicDao;
import com.ws.po.Course;
import com.ws.po.QuestionType;
import com.ws.po.TeacherUser;
import com.ws.po.Topic;
import com.ws.po.WorkTopic;
import com.ws.service.WorkTopicService;

@Component("workTopicService")
public class WorkTopicServiceImpl implements WorkTopicService {

	@Resource
	private TopicDao topicDao;
	@Resource
	private QuestionTypeDao questionTypeDao;
	@Resource
	private TeacherUserDao teacherUserDao;
	@Resource 
	private WorkTopicDao workTopicDao;
	@Resource 
	private CourseDao courseDao;
	@Override
	public boolean addWork(String name, String topicIds, String typeIds,
			Long userCommonId,int courseId) throws Exception {
		//创建对象
		  WorkTopic workTopic =new WorkTopic();
		  
		  if(name.length()>1){
			  workTopic.setTitleString(name);
			  String[]topicStrings=topicIds.split(",");
			  String[]typeStrings=typeIds.split(",");
			  
			  List<QuestionType>questionTypes =new ArrayList<QuestionType>();
			  List<Topic>topics=new ArrayList<Topic>();
			  try {
				  //遍历取得题目对象
				for (int i = 0; i < topicStrings.length; i++) {
					 Long topicId=Long.parseLong(topicStrings[i]);
					 Topic topic=topicDao.getTopicById(topicId);
					 if(topic !=null)
					 topics.add(topic);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			  try {
				for (int i = 0; i < typeStrings.length; i++) {
					//遍历取得题型对象
					int typeId=Integer.parseInt(typeStrings[i]);
					QuestionType questionType=questionTypeDao.getQuestionTypeById(typeId);
					if(questionType !=null)
					questionTypes.add(questionType);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			  try {
				  System.out.print("本此组卷添加题型个数：" +questionTypes.size() +"添加题目个数"+topics.size());
				  //存入题目，题型对象集
				  workTopic.setQuestionTypes(questionTypes);
				  workTopic.setTopicList(topics);
				  //取回教师对象
				  TeacherUser teacherUser = teacherUserDao.getTeacherUserByUserId(userCommonId);
				  //取回课程对象
				  Course course = courseDao.getCourseById(courseId);
				  if(teacherUser !=null && course != null){
				  try {
					  //存入教师与课程对象
					workTopic.setTeacherUser(teacherUser);
					  workTopic.setCourse(course);
					  workTopic.setAddDate(new Date());
					return   workTopicDao.inset(workTopic);
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
		  else {
			return false;
		}
		   
	}

	@Override
	public List<WorkTopic> getWorkTopicByUserCommonId(long userCommonId)
			throws Exception {
		
		try {
			TeacherUser teacherUser = teacherUserDao.getTeacherUserByUserId(userCommonId);
			
			return workTopicDao.getWorkTopicByTeacherUserId(teacherUser.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String[][] getWorkTopicByCourseId(int course) throws Exception {
		
		try {
			List<WorkTopic>workTopicList= workTopicDao.getWorkTopicByCourseId(course);
	
			if(workTopicList !=null){
		String[][] workTopicString = new String[workTopicList.size()][2];
      for (int i = 0; i < workTopicList.size(); i++) {
			WorkTopic workTopic =workTopicList.get(i);
			if(workTopic !=null)
			{
			workTopicString[i][0]=Integer.toString(workTopic.getId());
			workTopicString[i][1]=workTopic.getTitleString();
			}
           }
      return workTopicString;
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
	
	public TopicDao getTopicDao() {
		return topicDao;
	}

	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}

	public QuestionTypeDao getQuestionTypeDao() {
		return questionTypeDao;
	}

	public void setQuestionTypeDao(QuestionTypeDao questionTypeDao) {
		this.questionTypeDao = questionTypeDao;
	}

	public TeacherUserDao getTeacherUserDao() {
		return teacherUserDao;
	}

	public void setTeacherUserDao(TeacherUserDao teacherUserDao) {
		this.teacherUserDao = teacherUserDao;
	}

	public WorkTopicDao getWorkTopicDao() {
		return workTopicDao;
	}

	public void setWorkTopicDao(WorkTopicDao workTopicDao) {
		this.workTopicDao = workTopicDao;
	}

	@Override
	public boolean delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return workTopicDao.delete(id);
	}

	

	
}
