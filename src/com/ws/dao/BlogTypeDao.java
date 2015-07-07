package com.ws.dao;

import java.util.List;

import com.ws.po.BlogType;


public interface BlogTypeDao {

	/**
	 * 添加栏目
	 * @param blog
	 * @return
	 * @throws Exception
	 */
	public boolean insert(BlogType blogType)throws Exception;
	/**
	 * 获取栏目
	 * @return
	 * @throws Exception
	 */
	public List<BlogType>getTypeList(long userCommon)throws Exception;
	/**
	 * 删除
	 * @param blog
	 * @return
	 * @throws Exception
	 */
	public boolean delete(BlogType blogType)throws Exception;
    /**
     * 获取对象
     * @param blogTypeId
     * @return
     * @throws Exception
     */
	public BlogType getBlogTypeById(long blogTypeId)throws Exception;
	
	/**
	  * 获取下级目录
	  * @param typeId
	  * @return
	  * @throws Exception
	  */
	public List<BlogType> getNextTypeList(long typeId)throws Exception;
}
