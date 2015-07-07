package com.ws.dao;

import java.util.List;

import com.ws.po.JudgeTopic;

public interface TopicJudgeDao {

	/**
	 * 添加判断题
	 * @param judgeTopic
	 * @return
	 */
	public boolean insert(JudgeTopic judgeTopic)throws Exception;

	/**
	 * 获取本课程判断题
	 * @param courseId
	 * @return
	 */
	public List<JudgeTopic> getList(int offset, int length, int courseId)throws Exception;

	/**
	 * 获取总行数
	 * @param courseId
	 * @return
	 */
	public int getAllRow(int courseId)throws Exception;

	/**
	 * 根据Topic获取判断题
	 * @param topicId
	 * @return
	 */
	public Object getJudgeByTopic(long topicId)throws Exception;

	/**
	 * 获取对象
	 * @param tidInt
	 * @return
	 */
	public JudgeTopic getJudgeById(long tidInt)throws Exception;

	/**
	 * 删除
	 * @param judgeTopic
	 * @return
	 */
	public boolean delete(JudgeTopic judgeTopic)throws Exception;



}
