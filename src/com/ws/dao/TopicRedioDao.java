package com.ws.dao;

import java.util.List;

import com.ws.po.RedioTopic;

public interface TopicRedioDao {

	/**
	 * 添加单选题，返回成功状态
	 * @param redioTopic
	 * @return
	 * @throws Exception
	 */
	public boolean insertTopic(RedioTopic redioTopic) throws Exception;

	/**
	 * 获取指定课程单选题
	 * @param offset  //本页开始记录数
	 * @param length   //本页长度
	 * @param courseId 
	 * @return
	 */
	public List<RedioTopic> getList(int offset, int length, int courseId);

	/**
	 * 获取某课程总记录数
	 * @return
	 */
	public int getAllRow(int courseId) throws Exception;

	/**
	 * 根据Topic 获取单选题
	 * @param topicId
	 * @return
	 */
	public RedioTopic getRedioByTopic(long topicId) throws Exception;

	/**
	 * 根据ID获取对象
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	public RedioTopic getRedioTopicById(long tid)throws Exception;

	/**
	 * 删除
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	public boolean delete(long tid)throws Exception;
}
