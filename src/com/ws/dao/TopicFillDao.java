package com.ws.dao;

import java.util.List;

import com.ws.po.CheckBoxTopic;
import com.ws.po.FillTopic;

public interface TopicFillDao {

	/**
	 * 添加填空题
	 * @param fillTopic
	 * @return
	 */
	public boolean inset(FillTopic fillTopic) throws Exception;

	/**
	 * 获取某课程填空题
	 * @param courseId
	 * @return
	 */
	public List<CheckBoxTopic> getList(int offset, int length, int courseId)throws Exception;

	/**
	 * 获取总行数
	 * @param courseId
	 * @return
	 */
	public int getAllRow(int courseId)throws Exception;

	/**
	 * 根据Topic获取填空题
	 * @param topicId
	 * @return
	 */
	public Object getFillByTopic(long topicId)throws Exception;

	/**
	 * 根据ID获取对象
	 * @param tidInt
	 * @return
	 */
	public FillTopic getFillTopicById(long tidInt)throws Exception;

	/**
	 * 删除
	 * @param fillTopic
	 * @return
	 */
	public boolean delete(FillTopic fillTopic)throws Exception;

	

}
