package com.ws.dao;

import com.ws.po.Classes;

public interface ClassDao {

	/**
	 * 获取班级信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Classes getClasses(Long id) throws Exception;

}
