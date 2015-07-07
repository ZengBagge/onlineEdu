package com.ws.dao;

import com.ws.po.StudentUser;
import com.ws.po.TeacherUser;
import com.ws.po.UserCommon;

public interface UserDao {

	/**
	 * 用户注册
	 * @param userCommon
	 * @throws Exception
	 */
	public boolean addUserCommon(UserCommon userCommon) throws Exception;
	
	
	
	/**
	 * 会员删除
	 * @param id
	 * @throws Exception
	 */
	public boolean  delete(long id) throws Exception;
	
	
	/**
	 * 更新信息
	 * @param userCommon
	 * @return 
	 * @throws Exception
	 */
	public boolean updata(UserCommon userCommon) throws Exception;

	/**
	 * 根据common_id获取学生用户
	 * @param id
	 * @return
	 */
	public StudentUser getStudentById(long id) throws Exception;

	/**
	 * 根据common_id获取教师用户
	 * @param id
	 * @return
	 */
	public TeacherUser getTeacherById(long id) throws Exception;

	/**
	 * 修改密码
	 * @param pwd
	 * @param uid
	 * @param newPwd
	 * @return
	 */
	public Boolean changePassById(String pwd, String uid, String newPwd)throws Exception;

	/**
	 * 获取公共对象
	 * @param userId
	 * @return
	 */
	public UserCommon getUserCommon(long userId)throws Exception;

	/**
	 * 登录
	 * @param uid
	 * @param pwdString
	 * @return
	 * @throws Exception
	 */
	public UserCommon getUser(String uid, String pwdString)throws Exception;
	

}



















