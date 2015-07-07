package com.ws.dao;

import java.util.List;
import com.ws.po.BlogTag;


public interface BlogTagDao {

	/**
	 * 添加标签
	 * @param blogType
	 * @return
	 * @throws Exception
	 */
	public boolean insert(BlogTag blogTag)throws Exception;

	/**
	 * 获取标签首页
	 * @return
	 * @throws Exception
	 */
	public List<BlogTag>getIndexTags()throws Exception;
	/**
	 * 增加数量
	 * @param blogId
	 * @throws Exception
	 */
	public void addNumber(long blogTagId)throws Exception;
	/**
	 * 标签是否存在
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public boolean isExist(String name)throws Exception;
	/**
	 * 获取标签，通过名称
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public BlogTag getBlogTagByName(String name)throws Exception;
	/**
	 * 获取对象
	 * @param blogTagId
	 * @return
	 * @throws Exception
	 */
	public BlogTag getBlogTagById(long blogTagId)throws Exception;
}
