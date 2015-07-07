package com.ws.service;

import java.util.List;

import com.ws.po.Classes;
import com.ws.po.UserCommon;

public interface ClassesService {

	/**
	 * 获取同专业班级
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Classes> getClassesListByUsercommon(Long id)throws Exception;

	/**
	 * 获取教师教授班级
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public List<Classes> getClassesListByTeacher(Long id) throws Exception;

	/**
	 * 获取班级对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Classes getClassesByUserCommonId(Long id)throws Exception;

	/**
	 * 添加班级
	 * @param classUid
	 * @param name
	 * @param majorId
	 * @param userCommon
	 * @return
	 * @throws Exception
	 */
	public boolean addClasses(String classUid, String name, int majorId,
			UserCommon userCommon)throws Exception;

}
