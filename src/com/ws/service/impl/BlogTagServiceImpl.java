package com.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ws.common.PageBean;
import com.ws.common.commonUtil;
import com.ws.dao.BlogDao;
import com.ws.dao.BlogTagDao;
import com.ws.po.Blog;
import com.ws.po.BlogTag;
import com.ws.service.BlogTagService;
@Component("blogTagService")
public class BlogTagServiceImpl implements BlogTagService {

	@Resource
	private BlogTagDao blogTagDao;
	@Resource 
	private BlogDao blogDao;
	@Override
	public List<BlogTag> getCloudTags() throws Exception {
		// TODO Auto-generated method stub
		return blogTagDao.getIndexTags();
	}

	@Override
	public PageBean getBlogListByTag(long tid,int pageSize,int page) throws Exception {
			
		   try {
			BlogTag blogTag = blogTagDao.getBlogTagById(tid);
				List<Blog>bolgs = blogTag.getBlogs();
				commonUtil.removeDuplicate(bolgs);
				int allRow=bolgs.size(); //获取总行数
				int totalPage=PageBean.countTotalPage(pageSize, allRow);  //设置总页数
				final int offset = PageBean.countOffset(pageSize, page);//当前页开始记录
	            final int length = pageSize;//每页记录数
	            final int currentPage = PageBean.countCurrentPage(page);
	            List<Blog> showBlogs=new ArrayList<Blog>();
	            if(bolgs.size()>pageSize){	            
	            for(int i=offset;i<offset+length;i++)
	            {
	            	showBlogs.add(bolgs.get(i));
	            }
	            }
	            else {
					showBlogs.addAll(bolgs);
				}
	            PageBean pageBean = new PageBean();
				pageBean.setPageSize(pageSize);
				pageBean.setCurrentPage(currentPage);
				pageBean.setAllRow(allRow);
				pageBean.setTotalPage(totalPage);
				pageBean.setList(showBlogs);
				pageBean.init();
				return pageBean;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public BlogTagDao getBlogTagDao() {
		return blogTagDao;
	}
	public void setBlogTagDao(BlogTagDao blogTagDao) {
		this.blogTagDao = blogTagDao;
	}

	public BlogDao getBlogDao() {
		return blogDao;
	}

	public void setBlogDao(BlogDao blogDao) {
		this.blogDao = blogDao;
	}
	
	
}
