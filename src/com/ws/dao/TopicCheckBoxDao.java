package com.ws.dao;

import java.util.List;

import com.ws.po.CheckBoxTopic;

public interface TopicCheckBoxDao {

	/**
	 * 添加多选题
	 * @param checkBoxTopic
	 * @return
	 */
	public boolean insert(CheckBoxTopic checkBoxTopic) throws Exception;

	/**
	 * 获取某课程多选题
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
	 * 根据Topic获取多选题
	 * @param topicId
	 * @return
	 * @throws Exception
	 */
	public Object getCheckBoxByTopic(long topicId) throws Exception;

	/**
	 * 根据ID获取对象
	 * @param tidInt
	 * @return
	 */
	public CheckBoxTopic getCheckBoxById(long tidInt)throws Exception;

	/**
	 * 删除
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	public boolean delete(long tid)throws Exception;


}
