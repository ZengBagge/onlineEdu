package com.ws.dao;

import java.util.List;
import com.ws.po.MessageReceiveNote;

public interface MessageReceiveDao {
 
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
	 * 获取垃圾箱信息
	 * @param offset
	 * @param length
	 * @param id
	 * @return
	 */
	public List<MessageReceiveNote> getRubbishList(int offset, int length,
			Long id);

	/**
	 * 添加收件信息
	 * @param messageReceiveNote
	 * @throws Exception
	 */
	public void insert(MessageReceiveNote messageReceiveNote)throws Exception;

	/**
	 * 获取收件箱列表
	 * @param offset
	 * @param length
	 * @param id
	 * @return
	 */
	public List<MessageReceiveNote> getList(int offset, int length, Long id)throws Exception;

	/**
	 * 获取聊天框消息
	 * @param sender
	 * @param receiver
	 * @return
	 */
	public List<MessageReceiveNote> getReceiveBySenderAndReceive(Long sender, long receiver)throws Exception;

	/**
	 * 获取新消息行数
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int getNewReceiveNumber(Long id)throws Exception;

	/**
	 * 获取某发送者新来信列表
	 * @param senderId
	 * @return
	 */
	public List<MessageReceiveNote> getNewReceiveList(long senderId,long receiverId)throws Exception;
	
	/**
	 * 更新信息
	 * @param messageReceiveNote
	 * @throws Exception
	 */
	public boolean update(MessageReceiveNote messageReceiveNote)throws Exception;

}
