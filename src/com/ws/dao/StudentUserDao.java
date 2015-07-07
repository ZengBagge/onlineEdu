package com.ws.dao;

import java.util.Set;

import com.ws.po.StudentUser;

public interface StudentUserDao {
	
	public boolean addStudentUser(StudentUser studentUser)throws Exception;

	/**
	 * 获取某专业总人数
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int getNumberByMajor(int majorId)throws Exception;

	/**
	 * 获取学生用户对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public StudentUser getStudentUserByUserCommon(Long id) throws Exception;

	/**
	 * 获取某专业学生对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Set<StudentUser> getListByMajor(int id)throws Exception;

	/**
	 * 获取班级学生
	 * @param id
	 * @return
	 */
	public Set<StudentUser> getListByClasses(int id);

	/**
	 * 获取班级人数
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int getNumberByClasses(int id)throws Exception;

}
