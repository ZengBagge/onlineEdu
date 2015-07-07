package com.ws.dao;

import java.util.List;

import com.ws.po.ExamJournal;

public interface ExamJournalDao {

	/**
	 * 完成度
	 * @param courseId
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<ExamJournal> getExamJournalByCourseAndStudent(int courseId, Long id)throws Exception;

	/**
	 * 添加考试记录
	 * @param examJournal
	 * @return
	 * @throws Exception
	 */
	public boolean insert(ExamJournal examJournal)throws Exception;

	/**
	 * 是否完成
	 * @param examId
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Boolean getIsExamJournal(int examId, Long id)throws Exception;


}
