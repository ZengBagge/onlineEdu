package com.ws.service;

import com.ws.common.PageBean;
import com.ws.po.RedioTopic;


public interface TopicRedioService {

	/**
	 * 添加单选题
	 * @param title
	 * @param asA
	 * @param asB
	 * @param asC
	 * @param asD
	 * @param asE 
	 * @param as
	 * @param topicSource 
	 * @param topicType 
	 * @param chapter 
	 * @param decription
	 * @return
	 */
	public boolean addTopic(String title, String asA, String asB, String asC,
			String asD, String asE, int as, String decriptionint,int courseId,int typeId, int topicType, String topicSource, int chapter,String tid) throws Exception;

	/**
	 * 获取某课程单选题库
	 * @param courseId
	 * @return
	 */
	public PageBean getList(int pagesize,int page,int courseId) throws Exception;

	/**
	 * 批量导入单选题
	 * @param result
	 * @param courseId 
	 * @return
	 */
	public Boolean batchImport(String[][] result, String courseId);

	/**
	 * 验证单选题答案
	 * @param answerInt
	 * @param tidInt
	 * @return
	 * @throws Exception
	 */
	public boolean checkAnswer(int answerInt, int tidInt)throws Exception;

	/**
	 * 删除
	 * @param tid
	 * @return
	 */
	public boolean delete(long tid)throws Exception;

	/**
	 * 更新信息
	 * @param tid
	 * @param title
	 * @param asA
	 * @param asB
	 * @param asC
	 * @param asD
	 * @param asE 
	 * @param as
	 * @param decriptionint
	 * @param topicSource 
	 * @param topicType 
	 * @param courseId
	 * @return
	 * @throws Exception
	 */
	public boolean update(long id,String title, String asA, String asB, String asC,
			String asD, String asE, int as, String decriptionint,int type, int topicType, String topicSource, int chapter,String tid) throws Exception;

	/**
	 * 获取单选题对象
	 * @param redioTopicId
	 * @return
	 */
	public RedioTopic getRedioTopic(long redioTopicId)throws Exception;

}
