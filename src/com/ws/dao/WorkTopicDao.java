package com.ws.dao;

import java.util.List;

import com.ws.po.WorkTopic;

public interface WorkTopicDao {

	/*
	 * 添加组卷
	 */
	public boolean inset(WorkTopic workTopic)throws Exception;

	/**
	 * 获取某老师组卷
	 * @param id
	 * @return
	 */
	public List<WorkTopic> getWorkTopicByTeacherUserId(int id)throws Exception;

	/**
	 * 获取课程卷组
	 * @param course
	 * @return
	 * @throws Exception
	 */
	public List<WorkTopic> getWorkTopicByCourseId(int course)throws Exception;

	/**
	 * 根据ID获取卷组对象
	 * @param workTopicIdInt
	 * @return
	 */
	public WorkTopic getWorkTopicById(int workTopicIdInt) throws Exception;

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public boolean delete(int id)throws Exception;

}
