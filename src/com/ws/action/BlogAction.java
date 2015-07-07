package com.ws.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.common.PageBean;
import com.ws.common.commonUtil;
import com.ws.po.Blog;
import com.ws.po.BlogType;
import com.ws.po.UserCommon;
import com.ws.service.BlogService;
import com.ws.service.BlogTypeService;
@Component("blogAction")
@Scope("prototype")
public class BlogAction extends ActionSupport implements SessionAware{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Map<String, Object>session;
    private String titleString;
    private String shortMessage;
    private String contentString;
    private String tagString;
    private long typeId;
    private String writer;
    private String description;
    private String keyword;
    private int  page=1;
    private PageBean pageBean;
    @Resource
    private BlogService blogService;
    @Resource
    private BlogTypeService blogTypeService;
    private UserCommon userCommon;//列表所属人
    private int isSelf=0;
    private Blog blog;
    private long bid;
	private List<BlogType> nextBlogTypes;
    /**
     * 个人博客首页
     * @return
     * @throws Exception 
     */
    public String index() throws Exception{
		this.page = 1;
    	UserCommon userCommon =(UserCommon) session.get(UserAction.USER_SESSION);
    	this.pageBean = blogService.getUserIndexBlogs(userCommon.getId(),page, 5);
    	return "index";	
    }
    
    /**
     * 发表控制器
     * @return
     */
    public String add(){
    	return "add";
    }
    /**
     * 发表博文
     * @return
     * @throws Exception 
     */
    public String publish() throws Exception{
    	try {
			commonUtil.p("添加博文");
			UserCommon userCommon =(UserCommon) session.get(UserAction.USER_SESSION);
			String userIpString = ServletActionContext.getRequest().getRemoteAddr();
			String[] tags=tagString.split(",|，"); 
			if (userCommon != null) {
			boolean result=blogService.add(titleString, userCommon, shortMessage, contentString, tags, userIpString, typeId, writer, description, keyword);	
			if(result)
				return "ok";
			else {
				return "no";
			}
			}else {
				return "login";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
    	
    }
    
    public String list() throws Exception{
    	try {
    		if(typeId !=0)
    		{
			this.pageBean = blogService.getListByType(page, 5, typeId);
			BlogType blogType =blogTypeService.getTypeById(typeId);
			this.userCommon=blogType.getUserCommon();
			UserCommon userCommon2 = (UserCommon) session.get(UserAction.USER_SESSION);
			if (userCommon.getId() == userCommon2.getId()) { //自己浏览
				this.isSelf =1;
			}
			this.nextBlogTypes = blogTypeService.getNextTypeList(typeId);
			return "list";
    		}
    		else {
				return ERROR;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
    }
    
    public String blog() throws Exception{
    	if(bid!=0)
    	{
    		blog = blogService.getBlogById(bid);
    		return "blog";
    	}
    	else {
			return ERROR;
		}
    }
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}
	public Map<String, Object> getSession() {
		return session;
	}

	public BlogService getBlogService() {
		return blogService;
	}

	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}

	public String getTitleString() {
		return titleString;
	}

	public void setTitleString(String titleString) {
		this.titleString = titleString;
	}

	public String getShortMessage() {
		return shortMessage;
	}

	public void setShortMessage(String shortMessage) {
		this.shortMessage = shortMessage;
	}

	public String getContentString() {
		return contentString;
	}

	public void setContentString(String contentString) {
		this.contentString = contentString;
	}

	public String getTagString() {
		return tagString;
	}

	public void setTagString(String tagString) {
		this.tagString = tagString;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
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

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public long getBid() {
		return bid;
	}

	public void setBid(long bid) {
		this.bid = bid;
	}

	public BlogTypeService getBlogTypeService() {
		return blogTypeService;
	}

	public void setBlogTypeService(BlogTypeService blogTypeService) {
		this.blogTypeService = blogTypeService;
	}

	public UserCommon getUserCommon() {
		return userCommon;
	}

	public void setUserCommon(UserCommon userCommon) {
		this.userCommon = userCommon;
	}

	public int getIsSelf() {
		return isSelf;
	}

	public void setIsSelf(int isSelf) {
		this.isSelf = isSelf;
	}

	public List<BlogType> getNextBlogTypes() {
		return nextBlogTypes;
	}

	public void setNextBlogTypes(List<BlogType> nextBlogTypes) {
		this.nextBlogTypes = nextBlogTypes;
	}
   

	
}
