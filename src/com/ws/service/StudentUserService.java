package com.ws.service;

import java.util.Set;

import com.ws.po.StudentUser;

public interface StudentUserService {

	/**
	 * 获取同专业联系人
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Set<StudentUser> getMajorUserListByUsercommon(Long id)throws Exception;

	/**
	 * 获取教师教授专业学生
	 * @param id
	 * @return
	 */
	public Set<StudentUser> getMajorUserListByTeacher(Long id)throws Exception;

}
