package com.ws.dao;

import com.ws.po.CollectTopic;

public interface CollectTopicDao {

	/**
	 * 添加收藏
	 * @param collectTopic
	 * @return
	 * @throws Exception
	 */
	public boolean insert(CollectTopic collectTopic)throws Exception;

	/**
	 * 验证是否已收藏
	 * @param tid
	 * @param id
	 * @return
	 */
	public boolean isCollected(long tid, long id)throws Exception;

}
