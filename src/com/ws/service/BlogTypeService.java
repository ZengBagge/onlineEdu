package com.ws.service;

import java.util.List;
import com.ws.po.BlogType;
import com.ws.po.UserCommon;

public interface BlogTypeService {

	/**
	 * 增加分类
	 * @return
	 * @throws Exception
	 */
	public boolean add(UserCommon userCommon,String name,int fatherId)throws Exception;
	/**
	 * 获取某人博客分类
	 * @param userCommon
	 * @return
	 * @throws Exception
	 */
	public List<BlogType> getTypeListByUser(UserCommon userCommon)throws Exception;
	/**
	 * 删除
	 * @param blogTypeId
	 * @return
	 * @throws Exception
	 */
	public boolean del(int blogTypeId)throws Exception;
	/**
	 * 获取栏目对象
	 * @param typeId
	 * @return
	 */
	public BlogType getTypeById(long typeId)throws Exception;
	/**
	 * 获取下级目录
	 * @param typeId
	 * @return
	 * @throws Exception
	 */
	public List<BlogType> getNextTypeList(long typeId)throws Exception;
}
