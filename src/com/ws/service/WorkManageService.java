package com.ws.service;

import java.util.List;

import com.ws.common.PageBean;
import com.ws.po.UserCommon;
import com.ws.po.Work;

public interface WorkManageService {

	/**
	 * 添加任务
	 * @param titString  任务名
	 * @param workMajorString  //专业Id字符串
	 * @param courseId  //课程ID
	 * @param startTime  //开始时间
	 * @param endTime   //结束时间
	 * @param workTopicId  //固定组卷
	 * @param description   //任务描述
	 * @return
	 * @throws Exception
	 */
	public boolean addWork(String titString, String workMajorString, String courseId,
			String startTime, String endTime, String workTopicId,
			String description,UserCommon userCommon)throws Exception;
  /**
   * 教师获取正在进行任务
   * @param userCommon
   * @return
   * @throws Exception
   */
	public List<Work> getWorkingByTeacher(UserCommon userCommon) throws Exception;
	
	/**
	 * 教师获取已完成任务
	 * @param userCommon
	 * @return
	 * @throws Exception
	 */
	public PageBean getWorkedByTeacher(UserCommon userCommon,int page,int pageSize) throws Exception;
	/**
	 * 获取任务对象
	 * @param workId
	 * @return
	 */
	public Work getWorkById(int workId)throws Exception;
	/**
	 * 删除任务对象
	 * @param id
	 * @return
	 */
	public boolean delete(int id)throws Exception;
	/**
	 * 修改任务
	 * @param id
	 * @param titString
	 * @param workMajorString
	 * @param startTime
	 * @param endTime
	 * @param workTopicId
	 * @param description
	 * @param userCommon
	 * @param courseId 
	 * @return
	 */
	public boolean setWork(int id, String titString, String workMajorString,
			String startTime, String endTime, String workTopicId,
			String description, UserCommon userCommon, int courseId)throws Exception;

	
	
}
