package com.ws.dao;

import com.ws.po.Message;

public interface MessageDao {

	/**
	 * 添加消息
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public Message insert(Message message)throws Exception;

}
