package com.ws.service;

import java.util.List;
import com.ws.common.PageBean;
import com.ws.po.MessageReceiveNote;

public interface MessageReceiveService {

	/**
	 * 获取新消息数量
	 * @param id
	 * @return
	 */
	public int getNewReceiveNumber(Long id)throws Exception;

	/**
	 * 获取用户与某对象的最新消息
	 * @param id
	 * @param id2
	 * @return
	 * @throws Exception
	 */
	public List<MessageReceiveNote> getReceiveByUser(Long id, long id2)throws Exception;

	/**
	 * 获取收件箱信息
	 * @param i
	 * @param receivePage
	 * @param id
	 * @return
	 */
	public PageBean getReceiveBox(int i, int receivePage, Long id)throws Exception;

	/**
	 * 更新已读信息
	 * @param senderId
	 */
	public void setReaded(long senderId,long receiverId)throws Exception;
	
	/**
	 * 送垃圾箱
	 * @param mid
	 * @return
	 * @throws Exception
	 */
    public boolean putRubbishBox(long mid)throws Exception;	
 
    /**
     * 删除
     * @param mid
     * @return
     * @throws Exception
     */
    public boolean del(long mid)throws Exception;
}
