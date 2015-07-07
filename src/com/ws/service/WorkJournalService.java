package com.ws.service;

import java.util.List;

import com.ws.po.WorkJournal;

public interface WorkJournalService {

	/**
	 * 获取某同学对某课程任务完成情况
	 * @param courseId
	 * @param userCommonId
	 * @return
	 * @throws Exception
	 */
	public List<WorkJournal> getWorkJournalsByCourseAndUserCommon(int courseId,long userCommonId)throws Exception;
	

	/**
	 * 添加记录
	 * @param workId
	 * @param userCommonId
	 * @return
	 */
	public Boolean addWorkJournal(int workId,long userCommonId,int times,int[] result)throws Exception;
	
	/**
	 * 验证某同学是否完成某任务
	 * @param workId
	 * @param userCommonId
	 * @return
	 * @throws Exception
	 */
	public Boolean isWorkJournal(int workId,long userCommonId)throws Exception;
}
