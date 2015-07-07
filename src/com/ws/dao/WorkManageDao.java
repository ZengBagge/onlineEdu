package com.ws.dao;

import java.util.Date;
import java.util.List;
import com.ws.po.Work;

public interface WorkManageDao {

	/**
	 * 添加任务
	 * @param work
	 * @return
	 * @throws Exception
	 */
	public boolean insert(Work work)throws Exception;

	/**
	 * 获取进行中任务
	 * @param id
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public List<Work> getWorkingByTeacher(int id, Date date)throws Exception;

	/**
	 * 获取过时任务
	 * @param id
	 * @param date
	 * @return
	 */
	public List<Work> getWorkedByTeacher(int id, Date date,int offset,int length)throws Exception;

	/**
	 * 获取过去活动总数
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int getAllRow(int id)throws Exception;

	/**
	 * 获取活动对象
	 * @param workId
	 * @return
	 */
	public Work getWorkById(int workId)throws Exception;

	/**
	 * 删除对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delete(int id)throws Exception;


}
