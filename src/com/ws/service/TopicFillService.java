package com.ws.service;


import com.ws.common.PageBean;
import com.ws.po.FillTopic;


public interface TopicFillService {

	/**
	 * 添加填空题
	 * @param title
	 * @param answer
	 * @param decription
	 * @param courseId
	 * @param i
	 * @param topicSource 
	 * @param topicType 
	 * @param courseChapter 
	 * @return
	 */
	public boolean addTopic(String title, String[] answer, String decription,
			int courseId, int i, int topicType, String topicSource, int courseChapter,String tid) throws Exception;

	/**
	 * 获取本课程填空题
	 * @param courseId
	 * @param courseId2 
	 * @param page 
	 * @return
	 */
	public PageBean  getList(int courseId, int page, int courseId2)throws Exception;

	/**
	 * 批量导入填空题
	 * @param result
	 * @param courseId
	 * @return
	 */
	public Boolean batchImport(String[][] result, String courseId)throws Exception;

	/**
	 * 验证填空题答案
	 * @param answer
	 * @param tidInt
	 * @return
	 */
	public boolean[] checkAnswer(String[] answer, int tidInt)throws Exception;

	/**
	 *删除
	 * @param tid
	 * @return
	 */
	public boolean delete(long tid)throws Exception;

	/**
	 * 更新
	 * @param tid
	 * @param title
	 * @param answer
	 * @param decription
	 * @param courseId
	 * @return
	 * @throws Exception
	 */
	public boolean update(long id, String title, String[] answer,
			String decription, int typeId,int topicType, String topicSource,int courseChapter,String tid) throws Exception;

	/**
	 * 获取对象
	 * @param fillTopicId
	 * @return
	 */
	public FillTopic getFillTopic(long fillTopicId)throws Exception;

	
}
