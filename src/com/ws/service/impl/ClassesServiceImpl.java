package com.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ws.dao.ClassesDao;
import com.ws.dao.MajorDao;
import com.ws.dao.StudentUserDao;
import com.ws.dao.TeacherUserDao;
import com.ws.po.Classes;
import com.ws.po.Major;
import com.ws.po.StudentUser;
import com.ws.po.TeacherUser;
import com.ws.po.UserCommon;
import com.ws.service.ClassesService;
@Component("classesService")
public class ClassesServiceImpl implements ClassesService {

	@Resource
	private StudentUserDao studentUserDao;
	@Resource
	private TeacherUserDao teacherUserDao;
	@Resource
	private ClassesDao classesDao;
	@Resource
	private MajorDao majorDao;
	@Override
	public List<Classes> getClassesListByUsercommon(Long id) throws Exception {
		
		List<Classes>classes = new ArrayList<Classes>();
		StudentUser studentUser =studentUserDao.getStudentUserByUserCommon(id);
		if (studentUser != null) {
			Major major =studentUser.getMajor();
			if (major != null) {
				classes = classesDao.getClassesByMajor(major.getId());
			}
		}
		return classes;
	}
	@Override
	public List<Classes> getClassesListByTeacher(Long id) throws Exception {
		TeacherUser teacherUser =teacherUserDao.getTeacherUserByUserId(id);
		if (teacherUser != null) {
			return teacherUser.getClasses();
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
	public TeacherUserDao getTeacherUserDao() {
		return teacherUserDao;
	}
	public void setTeacherUserDao(TeacherUserDao teacherUserDao) {
		this.teacherUserDao = teacherUserDao;
	}
	public ClassesDao getClassesDao() {
		return classesDao;
	}
	public void setClassesDao(ClassesDao classesDao) {
		this.classesDao = classesDao;
	}
	@Override
	public Classes getClassesByUserCommonId(Long id) throws Exception {
	       StudentUser studentUser =studentUserDao.getStudentUserByUserCommon(id);
	       if (studentUser != null) {
		       Classes classes =studentUser.getClasses();
		       return classes;			
		}else {
			return null;
		}

	}
	@Override
	public boolean addClasses(String classUid, String name, int majorId,
			UserCommon userCommon) throws Exception {
		
		TeacherUser teacherUser=teacherUserDao.getTeacherUserByUserId(userCommon.getId());
		if (teacherUser==null) {
			return false;
		}
		Classes classes = classesDao.getClassesByUid(classUid);
		if (classes !=null) {
		
            List<TeacherUser>teacherUsers = classes.getTeachers();
			for (TeacherUser teacherUser2 :teacherUsers) {
				if (teacherUser2.getId()==teacherUser.getId()) {
					return false;
				}
			}
			teacherUsers.add(teacherUser);
			classes.setTeachers(teacherUsers);
		    return 	classesDao.update(classes);
		}else {
			Classes classes2 = new Classes();
			classes2.setClassUid(classUid);
			classes2.setName(name);
			Major major = majorDao.getMajor(majorId);
			if (major == null) {
				return false;
			}
			List<TeacherUser>teacherUsers=new ArrayList<TeacherUser>();
			teacherUsers.add(teacherUser);
			classes2.setTeachers(teacherUsers);
			classes2.setMajor(major);
		    return 	classesDao.save(classes2);
		}
	}

	
}
