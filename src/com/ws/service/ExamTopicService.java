package com.ws.service;

import java.util.List;
import com.ws.po.ExamTopic;
import com.ws.po.UserCommon;

public interface ExamTopicService {

	/**
	 * 添加考试组卷
	 * @param title
	 * @param types
	 * @param userCommon
	 * @param courseId  //课程Id
	 * @return
	 * @throws Exception
	 */
	public boolean addExamTopic(String title, String[][] types, UserCommon userCommon, int courseId)throws Exception;

	/**
	 * 教师获取组卷列表
	 * @param userCommonId
	 * @return
	 * @throws Exception
	 */
	public List<ExamTopic> getExamTopicByUserCommonId(long userCommonId) throws Exception;

	/**
	 * 获取课程组卷，返回二位数组
	 * @param course
	 * @return
	 * @throws Exception
	 */
	public String[][] getExamTopicByCourseId(int course)throws Exception;

	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delete(int id)throws Exception;
}
