package com.ws.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.common.PageBean;
import com.ws.service.BlogService;

@Component("indexAction")
@Scope("prototype")
public class IndexAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, Object>session;
	private int page=1;
	private PageBean pageBean;
	@Resource
	private BlogService blogService;
	
	public String execute(){
				return SUCCESS;			
	}
	public String show() throws Exception{
		this.page=1;
		this.pageBean=blogService.getIndexBlogs(page, 5);
		return "show";
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session=arg0;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public BlogService getBlogService() {
		return blogService;
	}
	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	
}
