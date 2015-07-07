package com.ws.service;

import java.util.List;

import com.ws.common.PageBean;
import com.ws.po.Exam;
import com.ws.po.UserCommon;

public interface ExamManageService {

	/**
	 * 获取进行中考试
	 * @param userCommon
	 * @return
	 * @throws Exception
	 */
	public List<Exam> getExamingByTeacher(UserCommon userCommon)throws Exception;

	/**
	 * 获取已结束考试
	 * @param userCommon
	 * @return
	 * @throws Exception
	 */
	public PageBean getExamedByTeacher(UserCommon userCommon,int page,int pageSize)throws Exception;

	/**
	 * 添加考试任务
	 * @param titString
	 * @param workMajorString
	 * @param courseId
	 * @param startTime  //考试任务开始时间
	 * @param endTime
	 * @param workTopicId
	 * @param description
	 * @param userCommon  
	 * @param examTimes  //考试时间
	 * @return
	 */
	public boolean addExam(String titString, String workMajorString,
			String courseId, String startTime, String endTime,
			String workTopicId, String description, UserCommon userCommon, String examTimes)throws Exception;

	/**
	 * 获取对象
	 * @param examId
	 * @return
	 */
	public Exam getExameById(int examId)throws Exception;

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public boolean delete(int id)throws Exception;

	/**
	 * 更新
	 * @param titString
	 * @param workMajorString
	 * @param courseId
	 * @param startTime
	 * @param endTime
	 * @param workTopicId
	 * @param description
	 * @param userCommon
	 * @param examTimes
	 * @return
	 * @throws Exception
	 */
	public boolean updateExam(int id,String titString, String workMajorString,
			String courseId, String startTime, String endTime,
			String workTopicId, String description, UserCommon userCommon,
			String examTimes)throws Exception;

}
