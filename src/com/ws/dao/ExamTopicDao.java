package com.ws.dao;

import java.util.List;

import com.ws.po.ExamTopic;


public interface ExamTopicDao {

	/**
	 * 插入考试组卷并返回对象
	 * @param examTopic
	 * @return
	 * @throws Exception
	 */
	public ExamTopic insert(ExamTopic examTopic)throws Exception;

	/**
	 * 根据教师ID获取考试组卷
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<ExamTopic> getExamTopicList(int id)throws Exception;

	/**
	 * 根据课程ID获取考试组卷
	 * @param course
	 * @return
	 */
	public List<ExamTopic> getExamTopicByCourseId(int course)throws Exception;
	
	/**
	 * 获取考试组卷
	 * @param workTopicIdInt
	 * @return
	 * @throws Exception
	 */
	public ExamTopic  getExamTopicById(int workTopicIdInt) throws Exception;

	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delete(int id)throws Exception;

}
