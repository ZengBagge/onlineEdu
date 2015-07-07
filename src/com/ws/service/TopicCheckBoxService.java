package com.ws.service;

import com.ws.common.PageBean;
import com.ws.po.CheckBoxTopic;

public interface TopicCheckBoxService {

	/**
	 * 获取多选题
	 * @param courseId
	 * @return
	 */
	public PageBean getList(int pagesize,int page,  int courseId) throws Exception;

	/**
	 * 添加多选题
	 * @param title
	 * @param asInt
	 * @param decription
	 * @param courseId
	 * @param i
	 * @param topicSource 
	 * @param topicType 
	 * @param tid 
	 * @return
	 * @throws Exception
	 */
	public boolean addTopic(String title, String[] answerTrue,String[] answer, String decription,
			int courseId, int i, int topicType, String topicSource,int sourceChapter, String tid) throws Exception;

	/**
	 * 批量导入多选题
	 * @param result
	 * @param courseId
	 * @return
	 */
	public Boolean batchImport(String[][] result, String courseId)throws Exception;

	/**
	 * 验证多选答案
	 * @param answerInt
	 * @param tidInt
	 * @return
	 */
	public boolean checkAnswer(String[] answerInt, int tidInt)throws Exception;

	/**
	 * 更新
	 * @param tid
	 * @param title
	 * @param answerTrue
	 * @param answer
	 * @param decription
	 * @param topicSource 
	 * @param topicType 
	 * @return
	 */
	public boolean update(long tid, String title, String[] answerTrue,
			String[] answer, String decription,int type, int topicType, String topicSource, int sourceChapter,String topictid)throws Exception;

	/**
	 * 删除
	 * @param tid
	 * @return
	 */
	public boolean delete(long tid)throws Exception;

	/**
	 * 获取对象
	 * @param checkBoxTopicId
	 * @return
	 */
	public CheckBoxTopic getCheckBox(long checkBoxTopicId)throws Exception;

}
