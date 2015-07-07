package com.ws.service;

import java.util.List;
import java.util.Set;

import com.ws.po.Course;

public interface CourseService {
	
	/**
	 * 教师获取自己的课程
	 * @return
	 * @throws Exception
	 */
	public List<Course> getCourseByUid(String uid) throws Exception;
    
	/**
	 * 添加课程
	 * @param courseName
	 * @param courseTest
	 * @param courseIntroduce
	 * @param courseMajors
	 * @param id 
	 * @return
	 */
	public boolean addCourse(String courseName, String courseTest,
			String courseIntroduce, String courseMajors, Long id,String courseQuestionType) throws Exception;

	/**
	 * 获取一个课程对象
	 * @return
	 */
	public Course getCourseById(int id) throws Exception;


	/**
	 * 更新课程信息
	 * @param courseId 
	 * @param courseName
	 * @param courseTest
	 * @param courseIntroduce
	 * @param courseMajors
	 * @param courseQuestionType 
	 * @param id
	 * @return
	 */
	public boolean setCourse(int id, String courseName, String courseTest,
			String courseIntroduce, String courseMajors,String courseQuestionType) throws Exception;

	/**
	 * 学生获取课程信息
	 * @param id
	 * @return
	 */
	public Set<Course> getCourseByStudentUser(Long id) throws Exception;

}
