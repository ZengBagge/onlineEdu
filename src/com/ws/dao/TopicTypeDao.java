package com.ws.dao;

import java.util.List;

import com.ws.po.TopicType;

public interface TopicTypeDao {
 
	/**
	 * 获取分类
	 * @param id
	 * @return
	 * @throws Exception
	 */
public 	List<TopicType> getTopicTypeList(int id)throws Exception;

/**
 * 添加分类
 * @param topicType
 * @return
 * @throws Exception
 */
	public boolean insert(TopicType topicType)throws Exception;

	/**
	 * 获取对象
	 * @param tid
	 * @return
	 */
public TopicType getTopicType(int tid)throws Exception;
/**
 * 删除
 * @param topicType
 * @return
 */
public boolean delete(TopicType topicType);


}
