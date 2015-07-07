package com.ws.service;

import com.ws.common.PageBean;
import com.ws.po.Message;
import com.ws.po.UserCommon;

public interface MessageService {

	/**
	 * 获取垃圾箱信息，包括发件箱的和收件箱
	 * @param i
	 * @param rubbishPage
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PageBean getRubbishMessage(int i, int rubbishPage, Long id)throws Exception;

	/**
	 * 发表信息（支持群发）
	 * @param content
	 * @param receivers
	 * @param userCommon
	 * @return
	 * @throws Exception
	 */
	public Message addMessage(String content, long[] receivers, UserCommon userCommon,int typeid)throws Exception;

}
