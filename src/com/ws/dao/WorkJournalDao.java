package com.ws.dao;

import java.util.List;

import com.ws.po.WorkJournal;


public interface WorkJournalDao {

	/**
	 * 添加记录
	 * @param workJournal
	 * @return
	 * @throws Exception
	 */
	public boolean insert(WorkJournal workJournal) throws Exception; 
	
	/**
	 * 获取某同学某课程任务完成集合
	 * @param courseId
	 * @param studentUserId
	 * @return
	 * @throws Exception
	 */
	public List<WorkJournal> getWorkJournalByCourseAndStudent(int courseId,long studentUserId)throws Exception;
	
	/**
	 * 验证某同学是否完成某任务
	 * @param workId
	 * @param studentUserId
	 * @return
	 * @throws Exception
	 */
	public boolean getIsWorkJournal(int workId,long studentUserId)throws Exception;
	
	
}
