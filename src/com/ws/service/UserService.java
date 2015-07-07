package com.ws.service;

import com.ws.po.StudentUser;
import com.ws.po.TeacherUser;
import com.ws.po.UserCommon;


public interface UserService {
	
	/**
	 * 注册需要的参数
	 * @param uid
	 * @param uname
	 * @param tname
	 * @param pwd
	 * @param rule
	 * @param mail
	 * @throws Exception
	 */
	public boolean addUser(String uid,String uname,String tname, String pwd,int rule,String mail ) throws Exception;
	
	
	/**
	 * 删除用户
	 * @param id
	 * @throws Exception
	 */
	public boolean delete(long id ) throws Exception;

	/**
	 * 获取学生用户资料
	 * @return 
	 */
	public StudentUser getStudent(long id) throws Exception;
   
	/**
	 * 获取教师用户资料
	 * @return 
	 * @throws Exception
	 */
	public TeacherUser getTeacher(long id) throws Exception;

	/**
	 * 修改密码
	 * @param pwd
	 * @param newPwd
	 * @param uid 
	 * @return
	 */
	public Boolean setPass(String pwd, String newPwd, String uid)throws Exception;
	
	/**
	 * 设置登录信息
	 * @param ip
	 * @param userCommon
	 * @return
	 * @throws Exception
	 */
	public boolean setLoginMessage(String ip,UserCommon userCommon)throws Exception;

	/**
	 * 登录
	 * @param uid
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public UserCommon getUserByPwd(String uid, String pwd)throws Exception;
	
	

}
