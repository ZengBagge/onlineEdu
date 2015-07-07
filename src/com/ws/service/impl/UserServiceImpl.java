package com.ws.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ws.common.commonUtil;
import com.ws.dao.ClassesDao;
import com.ws.dao.MajorDao;
import com.ws.dao.StudentUserDao;
import com.ws.dao.TeacherUserDao;
import com.ws.dao.UserDao;
import com.ws.po.Classes;
import com.ws.po.Major;
import com.ws.po.StudentUser;
import com.ws.po.TeacherUser;
import com.ws.po.UserCommon;
import com.ws.service.UserService;

@Component("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	@Resource
	private StudentUserDao studentUserDao;
	@Resource
	private TeacherUserDao teacherUserDao;
	@Resource
	private MajorDao majorDao;
	@Resource
	private ClassesDao classesDao;
	
	private static Logger logger = Logger.getLogger(UserServiceImpl.class.getName());  
	
	@Override
	public boolean addUser(String uid, String uname, String tname, String pwd,
			int rule, String mail) throws Exception {
		
		UserCommon userCommon= new UserCommon();
         try {
			userCommon.setUid(uid);
			userCommon.setUname(uname);
			userCommon.setTname(tname);
			userCommon.setPwd(commonUtil.getMD5Str(commonUtil.getMD5Str(pwd))); //二级加密，算法如下。
			userCommon.setRule(rule);
			
			userCommon.setMail(mail);
			userCommon.setAddTime(new Date());
				if (userCommon.getRule() == 1) {
					StudentUser studentUser = new StudentUser();
					String majorUid = userCommon.getUid().substring(2, 6);
					String classesUid = userCommon.getUid().substring(0, 8);
					Major major = majorDao.getMajorByMajorUid(majorUid);
					if (major !=null) {
						studentUser.setMajor(major);
					}else {
						logger.error("error input uid or major is not existing");
						commonUtil.p("学生学号输入错误或专业不存在");
						return false;
					}
					Classes classes = classesDao.getClassesByUid(classesUid);
					if (classes !=null) {
						studentUser.setClasses(classes);
					}else {
						commonUtil.p("学生学号输入错误或班级不存在");
						return false;
					}
					studentUser.setUserCommon(userCommon);
					return studentUserDao.addStudentUser(studentUser);
				}else if (userCommon.getRule() == 2) {
					TeacherUser teacherUser = new TeacherUser();
					teacherUser.setUserCommon(userCommon);
					return teacherUserDao.addTeacherUser(teacherUser);
				}else{
					return false;
				}

				
		} catch (Exception e) {
			System.out.println("注册数据处理错误");  
			return false;
		}
		
	}



	@Override
	public boolean delete(long id) throws Exception {
		
		try {
			return userDao.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public StudentUser getStudent(long id) throws Exception {
		// TODO Auto-generated method stub
		
		return userDao.getStudentById(id);
	}

	@Override
	public TeacherUser getTeacher( long id) throws Exception {
		// TODO Auto-generated method stub
		 return userDao.getTeacherById(id);
	}

	@Override
	public Boolean setPass(String pwd, String newPwd,String uid) throws Exception {
		System.out.print("修改密码");
		return userDao.changePassById(commonUtil.getMD5Str(commonUtil.getMD5Str(pwd)),uid,commonUtil.getMD5Str(commonUtil.getMD5Str(newPwd)));
	}

	@Override
	public boolean setLoginMessage(String ip,UserCommon userCommon) throws Exception {
		// TODO Auto-generated method stub
		userCommon.setLoginIp(ip);
		userCommon.setLoginTime(new Date());
		return userDao.updata(userCommon);
		
	}



	@Override
	public UserCommon getUserByPwd(String uid, String pwd) throws Exception {
		try {
			if (uid==null || pwd==null ||uid.equals(" ") || pwd.equals(" ")) {
				return null;
			}
			String pwdString=commonUtil.getMD5Str(commonUtil.getMD5Str(pwd)); //二级加密，算法如下。
			System.out.println("帐号ID为"+uid+"的会员尝试登录"+pwdString);
			UserCommon userCommon=userDao.getUser(uid, pwdString);
			   return userCommon;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}  

	
}
