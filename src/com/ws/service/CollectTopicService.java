package com.ws.service;

public interface CollectTopicService {

	/**
	 * 添加收藏
	 * @param tid
	 * @param id
	 * @return
	 */
	public boolean addCollect(long tid, Long id)throws Exception;
	
	/**
	 * 验证是否已收藏
	 * @param tid
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean isCollected(long tid,Long id)throws Exception;

}
