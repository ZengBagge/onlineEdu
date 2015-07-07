package com.ws.service;

import java.util.List;
import com.ws.po.TeacherUser;
public interface TeacherUserService {

	/**
	 * 获取教师列表
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<TeacherUser> getTeacherListByUsercommon(Long id)throws Exception;

	/**
	 * 获取同事
	 * @param id
	 * @return
	 */
	public List<TeacherUser> getGolleague(Long id)throws Exception;

}
