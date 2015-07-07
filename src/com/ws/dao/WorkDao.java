package com.ws.dao;

import java.util.Date;
import java.util.List;

import com.ws.po.Work;

public interface WorkDao {

	/**
	 * 获取进行中课程任务
	 * @param courseId
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public List<Work> getWorking(int courseId, Date date)throws Exception;

	/**
	 * 获取已结束课程任务
	 * @param courseId
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public List<Work> getWorked(int courseId, Date date)throws Exception;

	/**
	 * 获取任务对象
	 * @param workId
	 * @return
	 * @throws Exception
	 */
	public Work getWorkById(int workId)throws Exception;

	/**
	 * 完成人数+1
	 * @throws Exception
	 */
	public void setWan(Work work)throws Exception;

    /**
	 * 获取某课程题目总数
	 * @param courseId
	 * @return
	 *//*
	public int getTopicSumByCourse(int courseId)throws Exception;*/

}
