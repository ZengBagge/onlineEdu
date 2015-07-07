package com.ws.dao;

import java.util.List;

import com.ws.po.Blog;

public interface BlogDao {

	/**
	 * 发表博文
	 * @param blog
	 * @return
	 * @throws Exception
	 */
	public boolean insert(Blog blog)throws Exception;
	
	/**
	 * 获取首页博文
	 * @return
	 * @throws Exception
	 */
	public List<Blog>getIndexList(int offset,int length)throws Exception;
	/**
	 * 删除
	 * @param blog
	 * @return
	 * @throws Exception
	 */
	public boolean delete(Blog blog)throws Exception;
	/**
	 * 获取某栏目下博文
	 * @param typeId
	 * @return
	 */
    public List<Blog>getListByType(int offset,int length,long typeId)throws Exception;
    
    /**
     * 热度增加
     * @param blogId
     * @throws Exception
     */
    public void addHost(long blogId)throws Exception;
    /**
     * 送首页
     * @param blogId
     * @return
     * @throws Exception
     */
    public boolean setIndex(long blogId)throws Exception;
    /**
     * 获取对象
     * @param blogId
     * @return
     * @throws Exception
     */
    public Blog getBlogById(long blogId)throws Exception;

    /**
     * 获取首页博文数量
     * @return
     * @throws Exception
     */
	public int getIndexNumber()throws Exception;

	/**
	 * 分类博文总数
	 * @param typeId
	 * @return
	 * @throws Exception
	 */
	public int getTypeNumber(long typeId)throws Exception;

	/**
	 * 获取标签下博客总数
	 * @param tagId
	 * @return
	 * @throws Exception
	 */
	public int getTagNumber(int tagId)throws Exception;

	/**
	 * 获取标签下博客
	 * @param offset
	 * @param length
	 * @param tagId
	 * @return
	 */
	public List<Blog> getListByTag(int offset, int length, int tagId);

	/**
	 * 用户首页
	 * @param id
	 * @return
	 */
	public int getUserIndexNumber(Long id)throws Exception;

	/**
	 * 用户首页列表
	 * @param id
	 * @param offset
	 * @param length
	 * @return
	 */
	public List<Blog> getUserIndexList(Long id, int offset, int length);
	
	
	
}
