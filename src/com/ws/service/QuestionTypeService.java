package com.ws.service;

import java.util.List;

import com.ws.po.QuestionType;

public interface QuestionTypeService {
	
	/**
	 * 添加体型
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public boolean addType(String title) throws Exception;

	/**
	 * 获取题型
	 * @return
	 */
	public List<QuestionType> getTypeList() throws Exception;

	/**
	 * 根据课程ID获取题型
	 * @param course
	 * @return
	 */
	public List<QuestionType> getTypeListByCourseId(int course)throws Exception;

}
