package com.ws.service;

import java.util.List;

import com.ws.common.PageBean;
import com.ws.po.BlogTag;

public interface BlogTagService {

	/**
	 * 获取云标签
	 * @return
	 * @throws Exception
	 */
	List<BlogTag> getCloudTags()throws Exception;

	/**
	 * 获取标签博文
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	PageBean getBlogListByTag(long tid,int pageSize,int page)throws Exception;

}
