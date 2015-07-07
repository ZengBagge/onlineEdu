package com.ws.service;

import com.ws.common.PageBean;
import com.ws.po.DiscuzMessage;
import com.ws.po.UserCommon;

public interface DiscuzService {

	/**
	 * 获取题目帖子
	 * @param i
	 * @param page
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	public PageBean getInvitation(int i, int page, int tid)throws Exception;
    
	/**
	 * 发表题目帖子
	 * @param body
	 * @param userCommon
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	public DiscuzMessage publishInvitation(String body,UserCommon userCommon,long tid)throws Exception;
	
	/**
	 * 删除帖子
	 */
	public boolean delInvitation(long aid)throws Exception;
	
	/**
	 * 获取帖子
	 * @param i
	 * @param page
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	public PageBean getCourseInvitation(int i, int page, int cid)throws Exception;
    
	/**
	 * 发表课程帖子
	 * @param body
	 * @param userCommon
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	public DiscuzMessage publishCourseInvitation(String body,UserCommon userCommon,int cid)throws Exception;

	/**
	 * 赞
	 * @param id
	 * @return
	 */
	public boolean zan(long id)throws Exception;
	
	
	
}
