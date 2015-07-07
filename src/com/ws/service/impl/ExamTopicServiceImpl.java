package com.ws.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ws.dao.CourseDao;
import com.ws.dao.ExamQuestionTypeDao;
import com.ws.dao.ExamTopicDao;
import com.ws.dao.QuestionTypeDao;
import com.ws.dao.TeacherUserDao;
import com.ws.po.Course;
import com.ws.po.ExamQuestionType;
import com.ws.po.ExamTopic;
import com.ws.po.QuestionType;
import com.ws.po.TeacherUser;
import com.ws.po.UserCommon;
import com.ws.service.ExamTopicService;

@Component("examTopicService")
public class ExamTopicServiceImpl implements ExamTopicService {

	@Resource
	private TeacherUserDao teacherUserDao;
	
	@Resource
	private CourseDao courseDao;
	
	@Resource
	private QuestionTypeDao questionTypeDao;
	
	@Resource
	private ExamTopicDao examTopicDao;
	
	@Resource
	private ExamQuestionTypeDao examQuestionTypeDao;
	@Override
	public boolean addExamTopic(String title, String[][] types,
			UserCommon userCommon, int courseId) throws Exception {
	
		if (title.length()<1 || types.length<1 || userCommon ==null) {
           return false;			
		}
		ExamTopic examTopic=new ExamTopic();
		try {
			TeacherUser teacherUser =teacherUserDao.getTeacherUserByUserId(userCommon.getId());
			if (teacherUser != null) {
				examTopic.setTeacherUser(teacherUser);
				try {
					Course course =courseDao.getCourseById(courseId);
					if(course !=null)
					{
						examTopic.setCourse(course);
						examTopic.setTitleString(title);
						examTopic.setAddDate(new Date());
						ExamTopic examTopic2=examTopicDao.insert(examTopic);
						if(examTopic2 != null)
						{
							boolean[] result =new boolean[types.length];
							for (int i = 0; i < types.length; i++) {
								ExamQuestionType examQuestionType =new ExamQuestionType();
								try {
									int number =Integer.parseInt(types[i][1]);
									examQuestionType.setNumber(number);
									int questionTypeId =Integer.parseInt(types[i][0]);
									QuestionType q=questionTypeDao.getQuestionTypeById(questionTypeId);
									if (q != null) {
										examQuestionType.setQuestionType(q);
										examQuestionType.setExamTopic(examTopic2);
										result[i]=examQuestionTypeDao.insert(examQuestionType);
									}else {
										return false;
									}
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									return false;
								}
						
							}
					    for (int i = 0; i < result.length; i++) {
							if (!result[i]) {
								return false;
							}
						}
					    return true;
						}else{
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public List<ExamTopic> getExamTopicByUserCommonId(long userCommonId)
			throws Exception {
		
		try {
			TeacherUser teacherUser =teacherUserDao.getTeacherUserByUserId(userCommonId);
			if (teacherUser != null) {
				return examTopicDao.getExamTopicList(teacherUser.getId());
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
	public String[][] getExamTopicByCourseId(int course) throws Exception {
		
		try {
			List<ExamTopic>examTopicList= examTopicDao.getExamTopicByCourseId(course);
	
			if(examTopicList !=null){
		String[][] examTopicString = new String[examTopicList.size()][2];
      for (int i = 0; i < examTopicList.size(); i++) {
			ExamTopic examTopic =examTopicList.get(i);
			if(examTopic !=null)
			{
				examTopicString[i][0]=Integer.toString(examTopic.getId());
				examTopicString[i][1]=examTopic.getTitleString();
			}
           }
      return examTopicString;
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

	public TeacherUserDao getTeacherUserDao() {
		return teacherUserDao;
	}

	public void setTeacherUserDao(TeacherUserDao teacherUserDao) {
		this.teacherUserDao = teacherUserDao;
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

	public ExamTopicDao getExamTopicDao() {
		return examTopicDao;
	}

	public void setExamTopicDao(ExamTopicDao examTopicDao) {
		this.examTopicDao = examTopicDao;
	}

	public ExamQuestionTypeDao getExamQuestionTypeDao() {
		return examQuestionTypeDao;
	}

	public void setExamQuestionTypeDao(ExamQuestionTypeDao examQuestionTypeDao) {
		this.examQuestionTypeDao = examQuestionTypeDao;
	}

	@Override
	public boolean delete(int id) throws Exception {
		// TODO Auto-generated method stub
		ExamTopic examTopic =examTopicDao.getExamTopicById(id);
		if (examTopic != null ) {
	    	  if(examQuestionTypeDao.del(id))
			    return examTopicDao.delete(id);
	    	  else {
				return false;
			}
		}else {
			return false;
		}
	
	}
    
	
	
}
