package com.ws.service;

import com.ws.po.UserCommon;

public interface TopicCheckNotesService {

	/**
	 * 添加
	 * @param userCommon
	 * @param state
	 * @param tid
	 * @param workId
	 */
	void add(UserCommon userCommon, int state, long tid, int workId)throws Exception;

}
