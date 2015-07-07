package com.ws.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ws.dao.MajorDao;
import com.ws.dao.StudentUserDao;
import com.ws.dao.TeacherUserDao;
import com.ws.po.Classes;
import com.ws.po.Major;
import com.ws.po.StudentUser;
import com.ws.po.TeacherUser;
import com.ws.service.StudentUserService;

@Component("studentUserService")
public class StudentUserServiceImpl implements StudentUserService {

	@Resource
	private StudentUserDao studentUserDao;
	@Resource
	private TeacherUserDao teacherUserDao;
	@Resource
	private MajorDao majorDao;
	@Override
	public Set<StudentUser> getMajorUserListByUsercommon(Long id)
			throws Exception {
		
		StudentUser studentUser = studentUserDao.getStudentUserByUserCommon(id);
		if (studentUser != null) {
			Major major = studentUser.getMajor();
			if(major != null)
			{
			Set<StudentUser>studentUsers = studentUserDao.getListByMajor(major.getId());
			 return studentUsers;
			}else {
				return null;
			}
	  }else {
			return null;
		}
	}
	public StudentUserDao getStudentUserDao() {
		return studentUserDao;
	}
	public void setStudentUserDao(StudentUserDao studentUserDao) {
		this.studentUserDao = studentUserDao;
	}
	public MajorDao getMajorDao() {
		return majorDao;
	}
	public void setMajorDao(MajorDao majorDao) {
		this.majorDao = majorDao;
	}
	@Override
	public Set<StudentUser> getMajorUserListByTeacher(Long id) throws Exception {
		Set<StudentUser>studentUsers =new HashSet<StudentUser>();
		TeacherUser teacherUser =teacherUserDao.getTeacherUserByUserId(id);
		if (teacherUser!= null) {
			List<Classes>classes=teacherUser.getClasses();
			for(Classes c:classes){
				Set<StudentUser> listSet= studentUserDao.getListByClasses(c.getId());
				studentUsers.addAll(listSet);
			}
		}
		return studentUsers;
	}

	
}
