package com.ws.service;


import com.ws.common.PageBean;
import com.ws.po.JudgeTopic;

public interface TopicJudgeService {

	/**
	 * 添加判断题
	 * @param title
	 * @param answerInt
	 * @param decription
	 * @param courseId
	 * @param i
	 * @param topicSource 
	 * @param topicType 
	 * @param courseChapter 
	 * @return
	 */
	public boolean addTopic(String title, int answerInt, String decription,
			int courseId, int i, int topicType, String topicSource, int courseChapter,String tid)throws Exception;

	/**
	 * 获取本课程判断题
	 * @param courseId
	 * @param courseId2 
	 * @param page 
	 * @return
	 */
	public PageBean  getList(int courseId, int page, int courseId2) throws Exception;

	/**
	 * 批量导入判断题
	 * @param result
	 * @param courseId
	 * @return
	 */
	public Boolean batchImport(String[][] result, String courseId)throws Exception;

	/**
	 * 验证答案
	 * @param answer
	 * @param tidInt
	 * @return
	 */
	public boolean checkAnswer(int answer, int tidInt)throws Exception;

	/*
	 * 删除
	 */
	public boolean delete(long tid)throws Exception;

	/**
	 * 更新
	 * @param tid
	 * @param title
	 * @param answer
	 * @param decription
	 * @param topicSource 
	 * @param topicType 
	 * @param courseId
	 * @return
	 */
	public boolean update(long id, String title, int answer, String decription,
			int typeId, int topicType, String topicSource, int courseChapter,String tid)throws Exception;

	/**
	 * 获取对象
	 * @param judgeTopicId
	 * @return
	 */
	public JudgeTopic getJudge(long judgeTopicId)throws Exception;

}
