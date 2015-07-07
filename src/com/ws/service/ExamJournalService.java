package com.ws.service;

import java.util.List;

import com.ws.po.ExamJournal;

public interface ExamJournalService {

	/**
	 * 获取完成情况
	 * @param courseId
	 * @param id
	 * @return
	 */
	public List<ExamJournal> getExamJournalsByCourseAndUserCommon(int courseId, Long id)throws Exception;

	/**
	 * 添加记录
	 * @param workId
	 * @param userCommonId
	 * @return
	 */
	public Boolean addExamJournal(int ExamId,long userCommonId,int times,int[] result)throws Exception;
	
	/**
	 * 验证某同学是否完成某任务
	 * @param ExamId
	 * @param userCommonId
	 * @return
	 * @throws Exception
	 */
	public Boolean isExamJournal(int ExamId,long userCommonId)throws Exception;
}
