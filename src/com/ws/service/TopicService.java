package com.ws.service;

import java.util.List;

import com.ws.po.Topic;

public interface TopicService {

	/**
	 * 通过课程与题型获取题目
	 * @param courseId
	 * @param typeId
	 * @return
	 */
	public List<Topic> getTopicByCourseIdAndByTypeId(int courseId)throws Exception;

}
