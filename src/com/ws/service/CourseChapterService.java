package com.ws.service;

import java.util.List;

import com.ws.po.CourseChapter;

public interface CourseChapterService {

	/**
	 * 获取课程章节信息
	 * @param courseId
	 * @return
	 * @throws Exception
	 */
	public List<CourseChapter> getChapters(int courseId)throws Exception;

	/**
	 * 添加章节
	 * @param titleString
	 * @param shortMessage
	 * @param sort
	 * @return
	 */
	public boolean add(String titleString, String shortMessage, int sort,int courseId)throws Exception;
	/**
	 * 删除
	 * @param chapterId
	 * @return
	 * @throws Exception
	 */
	public boolean del(int chapterId)throws Exception;

	/**
	 * 修改
	 * @param titleString
	 * @param shortMessage
	 * @param sort
	 * @param cid
	 * @return
	 * @throws Exception
	 */
	public boolean update(String titleString, String shortMessage, int sort,int cid)throws Exception;
	/**
	 * 获取对象
	 * @param chapterId
	 * @return
	 * @throws Exception
	 */
	public CourseChapter getCourseChapter(int chapterId)throws Exception;
	
}
