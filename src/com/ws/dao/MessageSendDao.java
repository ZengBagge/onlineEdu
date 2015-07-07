package com.ws.dao;

import java.util.List;

import com.ws.po.MessageSendNote;

public interface MessageSendDao {

	/**
	 * 获取垃圾箱总行数
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int getAllRowRubbish(Long id)throws Exception;

	/**
	 * 获取总行数
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int getAllRow(Long id)throws Exception;
	
	/**
	 * 彻底删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delete(Long id)throws Exception;
	
	/**
	 * 送垃圾箱
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean sendRubbish(Long id)throws Exception;

	/**
	 * 获取垃圾信息
	 * @param offset
	 * @param length
	 * @param id
	 * @return
	 */
	public List<MessageSendNote> getRubbishList(int offset, int length, Long id);

	/**
	 * 添加发件箱信息
	 * @param messageSendNote
	 * @return 
	 */
	public boolean insert(MessageSendNote messageSendNote)throws Exception;

	/**
	 * 发件箱
	 * @param offset
	 * @param length
	 * @param id
	 * @return
	 */
	public List<MessageSendNote> getList(int offset, int length, Long id)throws Exception;
}
