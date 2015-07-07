package com.ws.dao;

import java.util.List;
import com.ws.po.DiscuzMessage;

public interface DiscuzDao {

	/**
	 * 获取总行数
	 * @param tid
	 * @return
	 */
    public	int getAllRow(int tid)throws Exception;

    /**
     * 获取题目讨论消息
     * @param offset
     * @param length
     * @param tid
     * @return
     */
	public List<DiscuzMessage> getList(int offset, int length, int tid)throws Exception;

	/**
	 * 添加讨论
	 * @param discuzMessage
	 * @return
	 */
	public DiscuzMessage insert(DiscuzMessage discuzMessage)throws Exception;

	/**
	 * 删除消息
	 * @param aid
	 * @return
	 */
	public boolean delInvitation(long aid)throws Exception;

	/**
	 * 获取课程讨论信息
	 * @param offset
	 * @param length
	 * @param cid
	 * @return
	 */
	public List<DiscuzMessage> getListByCourse(int offset, int length, int cid)throws Exception;

	/**
	 * 获取课程总行数
	 * @param cid
	 * @return
	 */
	public int getAllRowByCourse(int cid)throws Exception;

	/**
	 * 获取对象
	 * @param id
	 * @return
	 */
	public DiscuzMessage getDiscuzMessage(long id)throws Exception;

	/**
	 * 更新信息
	 * @param discuzMessage
	 * @return
	 * @throws Exception
	 */
	public boolean Update(DiscuzMessage discuzMessage)throws Exception;
}
