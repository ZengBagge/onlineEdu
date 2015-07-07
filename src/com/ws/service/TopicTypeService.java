package com.ws.service;

import java.util.List;
import com.ws.po.TopicType;

public interface TopicTypeService {

	/**
	 * 返回题类型
	 * @param userCommon
	 * @return
	 * @throws Exception
	 */
	public List<TopicType> getTopicTypeList(Long userCommon) throws Exception;
	/**
	 * 添加分类
	 * @param name
	 * @param sort
	 * @param UserCommon
	 * @return
	 * @throws Exception
	 */
	public boolean addType(String name,int sort,long UserCommon)throws Exception;
	/**
	 * 修改
	 * @param name
	 * @param sort
	 * @param UserCommon
	 * @return
	 * @throws Exception
	 */
	public boolean updateType(int tid,String name,int sort,long UserCommon) throws Exception;
	/**
	 * 删除
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	public boolean del(int tid)throws Exception;
	
	
}
