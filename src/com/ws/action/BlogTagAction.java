package com.ws.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.common.PageBean;
import com.ws.po.BlogTag;
import com.ws.service.BlogTagService;

@Component("blogTagAction")
@Scope("prototype")
public class BlogTagAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 @Resource
	 private BlogTagService blogTagService;

	 private List<BlogTag>blogTags;
	 private PageBean pageBean;
	 private int page;
	 private long tid;
	public void getCloudTags() throws Exception{
		
		this.blogTags=blogTagService.getCloudTags();
	}
	
	public String list() throws Exception{ //标签博文list
		if( tid!=0)
		{
		this.page=1;
		this.pageBean= blogTagService.getBlogListByTag(tid,5,page);
		return "list";
		}else {
			return ERROR;
		}
		
	}
	
	
	
	
	
	public BlogTagService getBlogTagService() {
		return blogTagService;
	}
	public void setBlogTagService(BlogTagService blogTagService) {
		this.blogTagService = blogTagService;
	}
	public List<BlogTag> getBlogTags() {
		return blogTags;
	}
	public void setBlogTags(List<BlogTag> blogTags) {
		this.blogTags = blogTags;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}
	
	
}
