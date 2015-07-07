package com.ws.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ws.dao.TeacherUserDao;
import com.ws.dao.TopicTypeDao;
import com.ws.po.TeacherUser;
import com.ws.po.TopicType;
import com.ws.service.TopicTypeService;

@Component("topicTypeService")
public class TopicTypeServiceImpl implements TopicTypeService {

	@Resource
	private TeacherUserDao teacherUserDao;
	@Resource
	private TopicTypeDao topicTypeDao;
	
	@Override
	public List<TopicType> getTopicTypeList(Long userCommon) throws Exception {
		
		TeacherUser teacherUser = teacherUserDao.getTeacherUserByUserId(userCommon);
		if(teacherUser !=null){
			return topicTypeDao.getTopicTypeList(teacherUser.getId());
		}else {
			return null;
		}
	}

	@Override
	public boolean addType(String name, int sort, long UserCommon)
			throws Exception {
		TeacherUser teacherUser = teacherUserDao.getTeacherUserByUserId(UserCommon);
		if(teacherUser !=null){
			TopicType topicType = new TopicType();
			topicType.setTeacher(teacherUser);
			if (name.length()>0) {
				topicType.setName(name);
			}else {
				return false;
			}
		 topicType.setSort(sort);
		 return topicTypeDao.insert(topicType);
		}else {
			return false;
		}
	}

	@Override
	public boolean updateType(int tid,String name, int sort, long UserCommon)
			throws Exception {
		TeacherUser teacherUser = teacherUserDao.getTeacherUserByUserId(UserCommon);
		if(teacherUser !=null){
			TopicType topicType = topicTypeDao.getTopicType(tid);
			topicType.setTeacher(teacherUser);
			if (name.length()>0) {
				topicType.setName(name);
			}else {
				return false;
			}
		 topicType.setSort(sort);
		 return topicTypeDao.insert(topicType);
		}else {
			return false;
		}
	}

	@Override
	public boolean del(int tid) throws Exception {
		TopicType topicType= topicTypeDao.getTopicType(tid);
		if (topicType !=null) {
			return topicTypeDao.delete(topicType);
		}else {
			return false;
		}
	}

	public TeacherUserDao getTeacherUserDao() {
		return teacherUserDao;
	}

	public void setTeacherUserDao(TeacherUserDao teacherUserDao) {
		this.teacherUserDao = teacherUserDao;
	}

	public TopicTypeDao getTopicTypeDao() {
		return topicTypeDao;
	}

	public void setTopicTypeDao(TopicTypeDao topicTypeDao) {
		this.topicTypeDao = topicTypeDao;
	}

	
}
