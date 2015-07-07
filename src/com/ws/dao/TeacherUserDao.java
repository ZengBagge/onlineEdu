package com.ws.dao;

import java.util.List;

import com.ws.po.TeacherUser;

public interface TeacherUserDao {
	
	
	public boolean addTeacherUser(TeacherUser teacherUser)throws Exception;
	/**
	 * 通过公共帐号ID获取教师用户
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public TeacherUser getTeacherUserByUserId(Long id) throws Exception;

	/**
	 * 获取所有教师用户
	 * @return
	 */
	public List<TeacherUser> getTeacherList()throws Exception;

}
