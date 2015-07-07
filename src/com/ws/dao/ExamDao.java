package com.ws.dao;

import java.util.Date;
import java.util.List;

import com.ws.po.Exam;

public interface ExamDao {

	/**
	 * 获取进行中模考
	 * @param courseId
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public List<Exam> getExaming(int courseId, Date date)throws Exception;

	/**
	 * 获取过去考试任务
	 * @param courseId
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public List<Exam> getExamed(int courseId, Date date)throws Exception;

	/**
	 * 获取对象
	 * @param examId
	 * @return
	 * @throws Exception
	 */
	public Exam getExamById(int examId)throws Exception;

	/**
	 * 完成人数加一
	 * @param exam
	 * @throws Exception
	 */
	public void setWan(Exam exam)throws Exception;

}
