package com.ws.dao;
import java.util.List;

import com.ws.po.Topic;

public interface TopicDao {

	/**
	 * 添加题目并返回对象
	 * @param topic
	 * @return
	 */
	public Topic insetTopic(Topic topic) throws Exception;

	/**
	 * 通过课程Id与题型Id获取题目
	 * @param courseId
	 * @param typeId
	 * @return
	 * @throws Exception
	 */
	public List<Topic> getTopics(int courseId) throws Exception;

	/**
	 * 获取题目对象
	 * @param topicId
	 * @return
	 * @throws Exception
	 */
	public Topic getTopicById(long topicId)throws Exception;

	/**
	 * 练习次数加一
	 * @param topicId 
	 * @throws Exception
	 */
	public void addsum(long topicId)throws Exception;

	/**
	 * 错误次数加一
	 */
	public void addError(long topic)throws Exception;

	/**
	 * 根据题型，课程ID获取随机题库
	 * @param type
	 * @param number
	 * @param courseId
	 * @return 
	 */
	public List<Topic> getTopicByRandAndCourseId(int type, int number, int courseId)throws Exception;

}
