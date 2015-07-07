package com.ws.dao;

import java.util.List;

import com.ws.po.Course;

public interface CourseDao {
	
   /**
    * 获取教师自己的课程
    * @param uid
    * @return
    */
	public List<Course> getCourseByUid(String uid) throws Exception;

	/**
	 * 添加课程
	 * @param course
	 * @return
	 * @throws Exception
	 */
	public boolean addCourse(Course course) throws Exception;
	
	/**
	 * 修改课程
	 * @param course
	 * @return
	 * @throws Exception
	 */
    public boolean setCourse(Course course) throws Exception;

    /**
     * 根据ID获取一个课程对象
     * @param id
     * @return
     */
	public Course getCourseById(int id) throws Exception;




}
