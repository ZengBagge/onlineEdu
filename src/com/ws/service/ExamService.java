package com.ws.service;

import java.util.List;

import com.ws.po.Exam;
import com.ws.po.ExamTopic;
import com.ws.po.Topic;

public interface ExamService {

	/**
	 * 获取进行中考试任务
	 * @param courseId
	 * @return
	 * @throws Exception
	 */
	public List<Exam> getExamingByCourseId(int courseId)throws Exception;

	/**
	 * 获取过去的考试
	 * @param courseId
	 * @return
	 */
	public List<Exam> getExamedByCourseId(int courseId)throws Exception;

	/**
	 * 获取考试任务
	 * @param examId
	 * @return
	 */
	public Exam getExamById(int examId)throws Exception;

	/**
	 * 是否属于考试专业
	 * @param id
	 * @param examId
	 * @return
	 * @throws Exception
	 */
	public Boolean isMajorExam(Long id, int examId)throws Exception;

	/**
	 * 获取考试题库
	 * @param examId
	 * @return
	 * @throws Exception
	 */
	public List<Topic> getExamTopicFromExam(int examId)throws Exception;

	/**
	 * 获取分类题库
	 * @param examTopic
	 * @return
	 */
	public List<Object> getTopicFormat(List<Topic> examTopic)throws Exception;


	
}
