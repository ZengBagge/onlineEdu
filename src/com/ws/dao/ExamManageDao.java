package com.ws.dao;

import java.util.Date;
import java.util.List;

import com.ws.po.Exam;

public interface ExamManageDao {

	/**
	 * 添加考试任务
	 * @param exam
	 * @return
	 * @throws Exception
	 */
	public boolean insert(Exam exam) throws Exception;

	/**
	 * 获取正在进行考试任务
	 * @param id  教师ID
	 * @param date
	 * @return
	 */
	public List<Exam> getExamingByTeacher(int id, Date date)throws Exception;

	/**
	 * 获取过期考试任务
	 * @param id
	 * @param date
	 * @return
	 */
	public List<Exam> getExamedByTeacher(int id, Date date,int offset,int length) throws Exception;

	/**
	 * 获取过去总行数
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int getAllRow(int id)throws Exception;

	/**
	 * 获取对象
	 * @param examId
	 * @return
	 */
	public Exam getExamById(int examId)throws Exception;

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public boolean delete(int id)throws Exception;

}
