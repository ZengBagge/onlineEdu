package com.ws.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ws.po.BlogType;
import com.ws.po.UserCommon;
import com.ws.service.BlogTypeService;

@Component("blogTypeAction")
@Scope("prototype")
public class BlogTypeAction extends CommonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private List<BlogType>typeList;
    @Resource
	private BlogTypeService blogTypeService;
    
    public void getBlogType() throws Exception{
		try {
			UserCommon userCommon=(UserCommon) session.get(UserAction.USER_SESSION);
			typeList = blogTypeService.getTypeListByUser(userCommon);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    

	public List<BlogType> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<BlogType> typeList) {
		this.typeList = typeList;
	}

	public BlogTypeService getBlogTypeService() {
		return blogTypeService;
	}

	public void setBlogTypeService(BlogTypeService blogTypeService) {
		this.blogTypeService = blogTypeService;
	}
    
    
}
