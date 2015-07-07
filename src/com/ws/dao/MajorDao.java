package com.ws.dao;

import java.util.List;

import com.ws.po.Major;

public interface MajorDao {
	
	/**
	 * 获取全部专业
	 * @return
	 * @throws Exception
	 */
	public List<Major> getMajors() throws Exception;
	
	/**
	 * 通过majorUid获取major对象
	 * @param majorUid
	 * @return
	 * @throws Exception
	 */
	public Major getMajorByMajorUid(String majorUid) throws Exception;

	/**
	 * 获取专业
	 * @param majorId
	 * @return
	 * @throws Exception
	 */
	public Major getMajor(int majorId)throws Exception;
	

}
