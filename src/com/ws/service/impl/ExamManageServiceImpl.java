package com.ws.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.ws.common.PageBean;
import com.ws.dao.ClassesDao;
import com.ws.dao.CourseDao;
import com.ws.dao.ExamManageDao;
import com.ws.dao.ExamTopicDao;
import com.ws.dao.StudentUserDao;
import com.ws.dao.TeacherUserDao;
import com.ws.po.Classes;
import com.ws.po.Course;
import com.ws.po.Exam;
import com.ws.po.ExamTopic;
import com.ws.po.TeacherUser;
import com.ws.po.UserCommon;
import com.ws.service.ExamManageService;

@Component("examManageService")
public class ExamManageServiceImpl implements ExamManageService {

	@Resource
	private CourseDao courseDao;
	@Resource
	private ClassesDao classesDao;
	
	@Resource
	private TeacherUserDao teacherUserDao;
	
	@Resource 
	private ExamTopicDao examTopicDao;
	
	@Resource 
	private ExamManageDao examManageDao;
	
	@Resource
	private StudentUserDao studentUserDao;
	
	@Override
	public List<Exam> getExamingByTeacher(UserCommon userCommon)
			throws Exception {
		try {
			TeacherUser teacherUser = teacherUserDao.getTeacherUserByUserId(userCommon.getId());
			if(teacherUser !=null)
			return examManageDao.getExamingByTeacher(teacherUser.getId(),new Date());
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
	public PageBean getExamedByTeacher(UserCommon userCommon,int page,int pageSize)
			throws Exception {
	
		try {
			TeacherUser teacherUser = teacherUserDao.getTeacherUserByUserId(userCommon.getId());
			if(teacherUser !=null)
			{
				int allRow=examManageDao.getAllRow(teacherUser.getId());  //获取总行数
				int totalPage=PageBean.countTotalPage(pageSize, allRow);  //设置总页数
				final int offset = PageBean.countOffset(pageSize, page);//当前页开始记录
	            final int length = pageSize;//每页记录数
	            final int currentPage = PageBean.countCurrentPage(page);
	            List<Exam> exams =examManageDao.getExamedByTeacher(teacherUser.getId(),new Date(),offset,length);
	            PageBean pageBean = new PageBean();
				pageBean.setPageSize(pageSize);
				pageBean.setCurrentPage(currentPage);
				pageBean.setAllRow(allRow);
				pageBean.setTotalPage(totalPage);
				pageBean.setList(exams);
				pageBean.init();
				return pageBean;	
				
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
	public boolean addExam(String titleString, String workMajorString,
			String courseId, String startTime, String endTime,
			String workTopicId, String description, UserCommon userCommon,
			String examTimes) throws Exception {
		 try {
				Exam exam =new Exam();
				  exam.setAddDate(new Date());
				   exam.setTitleString(titleString);
				   exam.setDescription(description);
				   if(examTimes.length()>1)
				   {
					  int examTimesInt=Integer.parseInt(examTimes);
				      exam.setExamTimes(examTimesInt);
				   }
				   else {
					return false;
				}
				   String[] majors= workMajorString.split(",");
				  try {
					 List< Classes>majorList=new ArrayList<Classes>();
					 int studentNumber=0;  //任务总人数
					for (int i = 0; i < majors.length; i++) {
						Classes major=classesDao.getClassesByUid(majors[i]);
						studentNumber =studentNumber + studentUserDao.getNumberByClasses(major.getId());
						majorList.add(major);
					}
					exam.setClasses(majorList);//考试班级
					System.out.println("任务总人数："+studentNumber);
					exam.setZong(studentNumber);
					System.out.println("任务班级个数为"+majorList.size());
					try {
						int courseIdInt=Integer.parseInt(courseId);
						Course course =courseDao.getCourseById(courseIdInt);
						exam.setCourse(course);
						int workTopicIdInt=Integer.parseInt(workTopicId);
						ExamTopic examTopic = examTopicDao.getExamTopicById(workTopicIdInt);
						exam.setExamTopic(examTopic);
		     			SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm" );  //设置时间转化格式
						System.out.println(sdf.format(new Date()));
						Date startDate =sdf.parse(startTime);
						Date endDate=sdf.parse(endTime);
					    exam.setStartDate(startDate);
					    exam.setEndDate(endDate);
					    TeacherUser teacherUser = teacherUserDao.getTeacherUserByUserId(userCommon.getId());
					    if(teacherUser !=null)
					    	exam.setTeacherUser(teacherUser);
					    return examManageDao.insert(exam);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
	}

	@Override
	public Exam getExameById(int examId) throws Exception {
		// TODO Auto-generated method stub
		return examManageDao.getExamById(examId);
	}

	@Override
	public boolean delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return examManageDao.delete(id);
	}

	@Override
	public boolean updateExam(int id,String titleString, String workMajorString,
			String courseId, String startTime, String endTime,
			String workTopicId, String description, UserCommon userCommon,
			String examTimes) throws Exception {
	
		 try {
				Exam exam =examManageDao.getExamById(id);
				  exam.setAddDate(new Date());
				   exam.setTitleString(titleString);
				   exam.setDescription(description);
				   if(examTimes.length()>1)
				   {
					  int examTimesInt=Integer.parseInt(examTimes);
				      exam.setExamTimes(examTimesInt);
				   }
				   else {
					return false;
				}
				   String[] majors= workMajorString.split(",");
				  try {
						 List< Classes>majorList=new ArrayList<Classes>();
						 int studentNumber=0;  //任务总人数
						for (int i = 0; i < majors.length; i++) {
							Classes major=classesDao.getClassesByUid(majors[i]);
							studentNumber =studentNumber + studentUserDao.getNumberByClasses(major.getId());
							majorList.add(major);
						}
						exam.setClasses(majorList);//考试班级
					System.out.println("任务总人数："+studentNumber);
					exam.setZong(studentNumber);
					System.out.println("任务专业个数为"+majorList.size());
					try {
						int courseIdInt=Integer.parseInt(courseId);
						Course course =courseDao.getCourseById(courseIdInt);
						exam.setCourse(course);
						int workTopicIdInt=Integer.parseInt(workTopicId);
						ExamTopic examTopic = examTopicDao.getExamTopicById(workTopicIdInt);
						exam.setExamTopic(examTopic);
		     			SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm" );  //设置时间转化格式
						System.out.println(sdf.format(new Date()));
						Date startDate =sdf.parse(startTime);
						Date endDate=sdf.parse(endTime);
					    exam.setStartDate(startDate);
					    exam.setEndDate(endDate);
					    TeacherUser teacherUser = teacherUserDao.getTeacherUserByUserId(userCommon.getId());
					    if(teacherUser !=null)
					    	exam.setTeacherUser(teacherUser);
					    return examManageDao.insert(exam);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
	}


}
