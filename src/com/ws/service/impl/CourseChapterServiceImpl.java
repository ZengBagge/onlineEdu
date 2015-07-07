package com.ws.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ws.common.commonUtil;
import com.ws.dao.CourseChapterDao;
import com.ws.dao.CourseDao;
import com.ws.po.Course;
import com.ws.po.CourseChapter;
import com.ws.service.CourseChapterService;

@Component("courseChapterService")
public class CourseChapterServiceImpl implements CourseChapterService {

	@Resource
	private CourseChapterDao courseChapterDao;
	@Resource
	private CourseDao courseDao;
	
	@Override
	public List<CourseChapter> getChapters(int courseId) throws Exception {
		// TODO Auto-generated method stub
		Course course = courseDao.getCourseById(courseId);
		commonUtil.p("获取课程章节全部信息");
		if(course != null){
			return courseChapterDao.getChapters(course.getId());
		}else {
			return null;
		}
	}

	@Override
	public boolean add(String titleString, String shortMessage, int sort,
			int courseId) throws Exception {
		commonUtil.p("添加章节");
		if(titleString != null && shortMessage !=null){
		 CourseChapter chapter=new CourseChapter();
		String title=commonUtil.trimInnerSpaceStr(titleString);
		 String message=commonUtil.trimInnerSpaceStr(shortMessage);
		 chapter.setShortMessage(message);
		 chapter.setTitle(title);
		 Course course = courseDao.getCourseById(courseId);
		 if(course == null)
			 return false;
		 else{
		 chapter.setCourse(course);
		 chapter.setSort(sort);
		 return courseChapterDao.insert(chapter);
		 }
		}else {
			return false;
		} 
	}

	@Override
	public boolean del(int chapterId) throws Exception {
		// TODO Auto-generated method stub
		CourseChapter chapter = courseChapterDao.getChapter(chapterId);
		if (chapter != null) {
			return courseChapterDao.delete(chapter);
		}else
		return false;
	}

	@Override
	public boolean update(String titleString, String shortMessage, int sort,
			int cid) throws Exception {
    commonUtil.p("修改章节");
		 CourseChapter chapter=courseChapterDao.getChapter(cid);
		 commonUtil.trimInnerSpaceStr(titleString);
		 commonUtil.trimInnerSpaceStr(shortMessage);
		 chapter.setShortMessage(shortMessage);
		 chapter.setTitle(titleString);
		 chapter.setSort(sort);
		 return courseChapterDao.insert(chapter);
	}

	@Override
	public CourseChapter getCourseChapter(int chapterId) throws Exception {
		// TODO Auto-generated method stub
		return courseChapterDao.getChapter(chapterId);
	}

}
