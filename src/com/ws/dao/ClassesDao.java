package com.ws.dao;

import java.util.List;

import com.ws.po.Classes;

public interface ClassesDao {

	/**
	 * 获取同专业班级
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Classes> getClassesByMajor(int id)throws Exception;

	/**
	 * 根据代码获取班级
	 * @param classUid
	 * @return
	 * @throws Exception
	 */
	public Classes getClassesByUid(String classUid)throws Exception;

	/**
	 * 保存
	 * @param classes2
	 * @return
	 * @throws Exception
	 */
	public boolean save(Classes classes2)throws Exception;

	/**
	 * 更新
	 * @param classes
	 * @return
	 * @throws Exception
	 */
	public boolean update(Classes classes)throws Exception;
	
	

}
