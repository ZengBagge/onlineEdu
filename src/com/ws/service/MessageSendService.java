package com.ws.service;

import com.ws.common.PageBean;

public interface MessageSendService {

	/**
	 * 获取发件箱
	 * @param i
	 * @param sendPage
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PageBean getSendBox(int i, int sendPage, Long id)throws Exception;

}
