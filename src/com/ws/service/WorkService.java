package com.ws.service;

import java.util.List;

import com.ws.po.Work;
import com.ws.po.WorkTopic;

public interface WorkService {

	/**
	 * 获取课程进行中练习任务
	 * @param courseId
	 * @return
	 * @throws Exception
	 */
	public List<Work> getWorkingByCourseId(int courseId) throws Exception;

	/**
	 * 获取课程过去的任务
	 * @param courseId
	 * @return
	 * @throws Exception
	 */
	public List<Work> getWorkedByCourseId(int courseId)throws Exception;

	/**
	 * 获取任务对象
	 * @param workId
	 * @return
	 * @throws Exception
	 */
	public Work getWorkById(int workId)throws Exception;

	/**
	 * 获取题组从任务对象中
	 * @param workId
	 * @return
	 * @throws Exception
	 */
	public WorkTopic getWorkTopicFromWork(int workId) throws Exception;

	/**
	 * 验证是否属于任务中的专业
	 * @param id
	 * @param workId 
	 * @return
	 * @throws Exception
	 */
	public Boolean isMajorWork(Long id, int workId) throws Exception;

	/**
	 * 获取相关组卷，题型下的题目信息，返回对象集
	 * @param id
	 * @param id2
	 * @return
	 */
	public List<Object> getTopicFormat(WorkTopic workTopic) throws Exception;

	/**
	 * 产生随机题练习
	 * @return
	 */
	public WorkTopic getRandomTopic(int courseId)throws Exception;

}
