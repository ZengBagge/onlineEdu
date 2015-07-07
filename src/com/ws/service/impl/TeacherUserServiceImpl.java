package com.ws.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.ws.dao.StudentUserDao;
import com.ws.dao.TeacherUserDao;
import com.ws.po.Classes;
import com.ws.po.StudentUser;
import com.ws.po.TeacherUser;
import com.ws.service.TeacherUserService;

@Component("teacherUserService")
public class TeacherUserServiceImpl implements TeacherUserService {

	
	@Resource
	private TeacherUserDao teacherUserDao;
	@Resource
	private StudentUserDao studentUserDao;
	@Override
	public List<TeacherUser> getTeacherListByUsercommon(Long id)
			throws Exception {
		List<TeacherUser>teacherUsers =new ArrayList<TeacherUser>();
		StudentUser studentUser = studentUserDao.getStudentUserByUserCommon(id);
		if (studentUser !=null) {
			Classes classes = studentUser.getClasses();
			if (classes !=null) {
				teacherUsers = classes.getTeachers();
			}
		}

		return teacherUsers;
	}
	public TeacherUserDao getTeacherUserDao() {
		return teacherUserDao;
	}
	public void setTeacherUserDao(TeacherUserDao teacherUserDao) {
		this.teacherUserDao = teacherUserDao;
	}
	public StudentUserDao getStudentUserDao() {
		return studentUserDao;
	}
	public void setStudentUserDao(StudentUserDao studentUserDao) {
		this.studentUserDao = studentUserDao;
	}
	@Override
	public List<TeacherUser> getGolleague(Long id) throws Exception {
		// TODO Auto-generated method stub
		List<TeacherUser>tList=teacherUserDao.getTeacherList();
		return tList;
	}

	
}
