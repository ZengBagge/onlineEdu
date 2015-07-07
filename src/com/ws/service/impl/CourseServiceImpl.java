package com.ws.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.ws.dao.ClassesDao;
import com.ws.dao.CourseDao;
import com.ws.dao.QuestionTypeDao;
import com.ws.dao.StudentUserDao;
import com.ws.dao.TeacherUserDao;
import com.ws.po.Classes;
import com.ws.po.Course;
import com.ws.po.QuestionType;
import com.ws.po.StudentUser;
import com.ws.po.TeacherUser;
import com.ws.service.CourseService;

@Component("courseService")
public class CourseServiceImpl implements CourseService {

	@Resource
	private CourseDao courseDao;
	@Resource
	private ClassesDao classesDao;
	@Resource
	private TeacherUserDao teacherUserDao;
	@Resource
	private QuestionTypeDao questionTypeDao;
	@Resource
	private StudentUserDao studentUserDao;
	@Override
	public List<Course> getCourseByUid(String uid) throws Exception {
		// TODO Auto-generated method stub
		return courseDao.getCourseByUid(uid);
	}
	public CourseDao getCourseDao() {
		return courseDao;
	}
	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}
	
	@Override
	public boolean addCourse(String courseName, String courseTest,
			String courseIntroduce, String courseMajors,Long id,String courseQuestionType) throws Exception {
	
		Course course = new Course();
		TeacherUser teacherUser=teacherUserDao.getTeacherUserByUserId(id);
		if(teacherUser != null)
		{
				course.setTeacherUser(teacherUser);
				course.setTitleString(courseName);
				course.setAddDate(new Date());
				course.setIntroduce(courseIntroduce);
				course.setTest(Integer.parseInt(courseTest));
				String[] majors =courseMajors.split(",");
				String[] questionStrings=courseQuestionType.split(",");
				
				Set<Classes> classesSet =new HashSet<Classes>();
				Set<Course>courseSet=new HashSet<Course>();
				Set<QuestionType>questionTypes=new HashSet<QuestionType>();
				for (int i = 0; i < majors.length; i++) {			
					Classes classes=classesDao.getClassesByUid(majors[i]);
					if(classes != null)
					{
						classesSet.add(classes);
					}	
		        }
				for (int i = 0; i < questionStrings.length; i++) {
					QuestionType questionType=questionTypeDao.getQuestionTypeById(Integer.parseInt(questionStrings[i]));
					if (questionType !=null) {
						courseSet=questionType.getCouses();
						courseSet.add(course);
						questionType.setCouses(courseSet);
						questionTypes.add(questionType);
					}
				}
			  course.setQuestionType(questionTypes);
		      course.setClasses(classesSet);
		  return courseDao.addCourse(course);
		}
		else {
			return false;
		}
	
	}
	@Override
	public Course getCourseById(int id) throws Exception {
		// TODO Auto-generated method stub
		return courseDao.getCourseById(id);
	}
	@Override
	public boolean setCourse(int id,String courseName, String courseTest,
			String courseIntroduce, String courseMajors,String courseQuestionType) throws Exception {
		        Course course = courseDao.getCourseById(id);
				course.setTitleString(courseName);
				course.setIntroduce(courseIntroduce);
				course.setTest(Integer.parseInt(courseTest));
				
		//先移除原有的课程与专业的关系 
				String[] majors =courseMajors.split(",");
				String[] questionTypeStrings= courseQuestionType.split(",");
				Set<QuestionType> questionTypes=course.getQuestionType();	
				    for (Iterator<QuestionType> iterator =questionTypes.iterator();iterator.hasNext();) {
						QuestionType questionTypeOld = iterator.next();
						questionTypeOld.getCouses().remove(course);
						iterator.remove();
					}
				    questionTypes.clear();
				
				Set<Classes>classesSet=new HashSet<Classes>();
				Set<Course>courseSetQuestion=new HashSet<Course>();
				for (int i = 0; i < majors.length; i++) {
					Classes classes = classesDao.getClassesByUid(majors[i]);
					if(classes != null)
					{
						classesSet.add(classes);
					}
		        }
				for (int i = 0; i < questionTypeStrings.length; i++) {
					QuestionType questionType=questionTypeDao.getQuestionTypeById(Integer.parseInt(questionTypeStrings[i]));
					if (questionType !=null) {
						courseSetQuestion=questionType.getCouses();
						courseSetQuestion.add(course);
						questionType.setCouses(courseSetQuestion);
					}
					questionTypes.add(questionType);
				}
		     course.setClasses(classesSet);
		      course.setQuestionType(questionTypes);
		  return courseDao.setCourse(course);
		}
	
	
	public TeacherUserDao getTeacherUserDao() {
		return teacherUserDao;
	}
	public void setTeacherUserDao(TeacherUserDao teacherUserDao) {
		this.teacherUserDao = teacherUserDao;
	}
	public QuestionTypeDao getQuestionTypeDao() {
		return questionTypeDao;
	}
	public void setQuestionTypeDao(QuestionTypeDao questionTypeDao) {
		this.questionTypeDao = questionTypeDao;
	}
	
	public StudentUserDao getStudentUserDao() {
		return studentUserDao;
	}
	public void setStudentUserDao(StudentUserDao studentUserDao) {
		this.studentUserDao = studentUserDao;
	}
	@Override
	public Set<Course> getCourseByStudentUser(Long id) throws Exception {
		Set<Course>courses = null;
		try {
			StudentUser studentUser = studentUserDao.getStudentUserByUserCommon(id);
			if (studentUser !=null) {
				Classes major = studentUser.getClasses();
				courses =major.getCourses();
				return courses;
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
	

}
