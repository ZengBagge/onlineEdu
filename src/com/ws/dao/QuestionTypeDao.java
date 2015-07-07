package com.ws.dao;

import java.util.List;

import com.ws.po.QuestionType;

public interface QuestionTypeDao {
	
	/**
	 * 添加题型
	 * @param questionType
	 * @return
	 * @throws Exception
	 */
	public boolean  insertType(QuestionType questionType) throws Exception;

	/**
	 * 获取体型
	 * @return
	 */
	public List<QuestionType> getTypeList();

	/**
	 * 根据ID获取题型对象
	 * @param string
	 * @return
	 */
	public QuestionType getQuestionTypeById(int id);

	/**
	 * 根据课程ID获取题型
	 * @param course
	 * @return
	 */
	public List<QuestionType> getTypeListByCourseId(int course) throws Exception;

}
