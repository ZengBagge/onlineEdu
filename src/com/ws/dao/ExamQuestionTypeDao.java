package com.ws.dao;

import java.util.List;

import com.ws.po.ExamQuestionType;

public interface ExamQuestionTypeDao {

	/**
	 * 添加考试组卷题型数目
	 * @param examQuestionType
	 * @return 
	 */
	public boolean insert(ExamQuestionType examQuestionType) throws Exception;

	/**
	 * 获取组卷题目类型
	 * @param examId
	 * @return
	 */
	public List<ExamQuestionType> getListByExam(int examTopicId)throws Exception;

	/**
	 * 删除考试组卷题目类型
	 * @param id
	 * @return 
	 */
	public boolean del(int id)throws Exception;

}
