package com.ws.service;

import java.util.List;

import com.ws.po.WorkTopic;


public interface WorkTopicService {

	/***
	 * 添加组卷
	 * @param name
	 * @param topicIds
	 * @param typeIds
	 * @param userCommonId 
	 * @param courseId 
	 * @return
	 */
	public boolean addWork(String name, String topicIds, String typeIds, Long userCommonId, int courseId) throws Exception;
	
	/**
	 * 教师获取组卷列表
	 * @param userCommonId
	 * @return
	 * @throws Exception
	 */
	public List<WorkTopic> getWorkTopicByUserCommonId(long userCommonId) throws Exception;

	/**
	 * 获取课程组卷，返回二位数组
	 * @param course
	 * @return
	 * @throws Exception
	 */
	public String[][] getWorkTopicByCourseId(int course)throws Exception;

	/**
	 * 删除组卷
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delete(int id)throws Exception;

}
