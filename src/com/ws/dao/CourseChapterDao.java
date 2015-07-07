package com.ws.dao;

import java.util.List;

import com.ws.po.CourseChapter;

public interface CourseChapterDao {

	/**
	 * 获取章节信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<CourseChapter> getChapters(int id)throws Exception;

	/**
	 * 添加
	 * @param chapter
	 * @return
	 * @throws Exception
	 */
	public boolean insert(CourseChapter chapter)throws Exception;

	/**
	 * 获取对象
	 * @param chapterId
	 * @return
	 */
	public CourseChapter getChapter(int chapterId)throws Exception;

	/**
	 * 删除
	 * @param chapter
	 * @return 
	 */
	public boolean delete(CourseChapter chapter)throws Exception;

}
