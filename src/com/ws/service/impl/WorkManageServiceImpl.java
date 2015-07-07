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
import com.ws.dao.StudentUserDao;
import com.ws.dao.TeacherUserDao;
import com.ws.dao.WorkManageDao;
import com.ws.dao.WorkTopicDao;
import com.ws.po.Classes;
import com.ws.po.Course;
import com.ws.po.TeacherUser;
import com.ws.po.UserCommon;
import com.ws.po.Work;
import com.ws.po.WorkTopic;
import com.ws.service.WorkManageService;

@Component("workManageService")
public class WorkManageServiceImpl implements WorkManageService {

	@Resource
	private CourseDao courseDao;
	@Resource
	private ClassesDao classesDao;
	
	@Resource
	private TeacherUserDao teacherUserDao;
	
	@Resource 
	private WorkTopicDao workTopicDao;
	
	@Resource 
	private WorkManageDao workManageDao;
	
	@Resource
	private StudentUserDao studentUserDao;
	/**
	 * 添加任务
	 */
	@Override
	public boolean addWork(String titleString, String workMajorString,
			String courseId, String startTime, String endTime,
			String workTopicId, String description,UserCommon userCommon) throws Exception {
		   try {
			Work work =new Work();
			   work.setAddDate(new Date());
			   work.setTitleString(titleString);
			   work.setDescription(description);
			  String[] majors= workMajorString.split(",");
			  try {
					 List< Classes>majorList=new ArrayList<Classes>();
					 int studentNumber=0;  //任务总人数
					for (int i = 0; i < majors.length; i++) {
						Classes major=classesDao.getClassesByUid(majors[i]);
						studentNumber =studentNumber + studentUserDao.getNumberByClasses(major.getId());
						majorList.add(major);
					}
					work.setClasses(majorList);//考试班级
				System.out.println("任务总人数："+studentNumber);
				work.setZong(studentNumber);
				System.out.println("任务班级个数为"+majorList.size());
				try {
					int courseIdInt=Integer.parseInt(courseId);
					Course course =courseDao.getCourseById(courseIdInt);
					work.setCourse(course);
					int workTopicIdInt=Integer.parseInt(workTopicId);
					WorkTopic workTopic =workTopicDao.getWorkTopicById(workTopicIdInt);
					work.setWorkTopic(workTopic);
	     			SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm" );  //设置时间转化格式
					System.out.println(sdf.format(new Date()));
					Date startDate =sdf.parse(startTime);
					Date endDate=sdf.parse(endTime);
				    work.setStartDate(startDate);
				    work.setEndDate(endDate);
				    TeacherUser teacherUser = teacherUserDao.getTeacherUserByUserId(userCommon.getId());
				    if(teacherUser !=null)
				    	work.setTeacherUser(teacherUser);
				    return workManageDao.insert(work);
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
/**
 * 获取正在进行任务
 */
	@Override
	public List<Work> getWorkingByTeacher(UserCommon userCommon)
			throws Exception {
		try {
			TeacherUser teacherUser = teacherUserDao.getTeacherUserByUserId(userCommon.getId());
			if(teacherUser !=null)
			return workManageDao.getWorkingByTeacher(teacherUser.getId(),new Date());
			else {
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
/**
 * 获取过期任务
 */
	@Override
	public PageBean getWorkedByTeacher(UserCommon userCommon,int page,int pageSize)
			throws Exception {
		try {
			TeacherUser teacherUser = teacherUserDao.getTeacherUserByUserId(userCommon.getId());
			if(teacherUser !=null)
			{
				int allRow=workManageDao.getAllRow(teacherUser.getId());  //获取总行数
				int totalPage=PageBean.countTotalPage(pageSize, allRow);  //设置总页数
				final int offset = PageBean.countOffset(pageSize, page);//当前页开始记录
	            final int length = pageSize;//每页记录数
	            final int currentPage = PageBean.countCurrentPage(page);
	            List<Work> works =workManageDao.getWorkedByTeacher(teacherUser.getId(),new Date(),offset,length);
	            PageBean pageBean = new PageBean();
				pageBean.setPageSize(pageSize);
				pageBean.setCurrentPage(currentPage);
				pageBean.setAllRow(allRow);
				pageBean.setTotalPage(totalPage);
				pageBean.setList(works);
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
	public CourseDao getCourseDao() {
		return courseDao;
	}
	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
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
	public WorkManageDao getWorkManageDao() {
		return workManageDao;
	}
	public void setWorkManageDao(WorkManageDao workManageDao) {
		this.workManageDao = workManageDao;
	}
	public StudentUserDao getStudentUserDao() {
		return studentUserDao;
	}
	public void setStudentUserDao(StudentUserDao studentUserDao) {
		this.studentUserDao = studentUserDao;
	}
	@Override
	public Work getWorkById(int workId) throws Exception {
		// TODO Auto-generated method stub
		return workManageDao.getWorkById(workId);
	}
	@Override
	public boolean delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return workManageDao.delete(id);
	}
	@Override
	public boolean setWork(int id, String titleString, String workMajorString,
			String startTime, String endTime, String workTopicId,
			String description, UserCommon userCommon,int courseId) throws Exception {
		
		 try {
				Work work =workManageDao.getWorkById(id);
				   work.setAddDate(new Date());
				   work.setTitleString(titleString);
				   work.setDescription(description);
				  String[] majors= workMajorString.split(",");
				  try {
					  List< Classes>majorList=new ArrayList<Classes>();
						 int studentNumber=0;  //任务总人数
						for (int i = 0; i < majors.length; i++) {
							Classes major=classesDao.getClassesByUid(majors[i]);
							studentNumber =studentNumber + studentUserDao.getNumberByClasses(major.getId());
							majorList.add(major);
						}
						work.setClasses(majorList);//练习班级
					System.out.println("任务总人数："+studentNumber);
					work.setZong(studentNumber);
					System.out.println("任务班级个数为"+majorList.size());
					try {
						Course course =courseDao.getCourseById(courseId);
						work.setCourse(course);
						int workTopicIdInt=Integer.parseInt(workTopicId);
						WorkTopic workTopic =workTopicDao.getWorkTopicById(workTopicIdInt);
						work.setWorkTopic(workTopic);
		     			SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm" );  //设置时间转化格式
						System.out.println(sdf.format(new Date()));
						Date startDate =sdf.parse(startTime);
						Date endDate=sdf.parse(endTime);
					    work.setStartDate(startDate);
					    work.setEndDate(endDate);
					    TeacherUser teacherUser = teacherUserDao.getTeacherUserByUserId(userCommon.getId());
					    if(teacherUser !=null)
					    	work.setTeacherUser(teacherUser);
					    return workManageDao.insert(work);
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
			
	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	}
	
	
	
}
