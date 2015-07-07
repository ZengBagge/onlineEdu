package com.ws.service;

import com.ws.common.PageBean;
import com.ws.po.Blog;
import com.ws.po.UserCommon;

public interface BlogService {

	/**
	 * 发表博客
	 * @param titString
	 * @param userCommon
	 * @param shortMessage
	 * @param contentString
	 * @param tags
	 * @param userIpString
	 * @param typeId
	 * @param writer
	 * @param description
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public boolean add(String titString,UserCommon userCommon,String shortMessage,String contentString,String[] tags,String userIpString,
			long typeId,String writer,String description,String keyword)throws Exception;
	/**
	 * 更新
	 * @param tid
	 * @param titString
	 * @param userCommon
	 * @param shortMessage
	 * @param contentString
	 * @param tags
	 * @param userIpString
	 * @param typeId
	 * @param writer
	 * @param description
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public boolean update(long tid,String titString,UserCommon userCommon,String shortMessage,String contentString,String[] tags,String userIpString,
			long typeId,String writer,String description,String keyword)throws Exception;
	/**
	 * 获取首页博客
	 * @return
	 * @throws Exception
	 */
	public PageBean getIndexBlogs(int page,int pageSize)throws Exception;

	/**
	 * 获取某栏目下博文
	 * @param typeId
	 * @return
	 */
    public PageBean getListByType(int page,int pageSize,long typeId)throws Exception;
	
	/**
	 * 获取某栏目下博文
	 * @param typeId
	 * @return
	 */
    public PageBean getListByTag(int page,int pageSize,int tagId)throws Exception;
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
     * 用户首页
     * @param id
     * @param page
     * @param i
     * @return
     */
	public PageBean getUserIndexBlogs(Long id, int page, int i);
    
    
	
}
