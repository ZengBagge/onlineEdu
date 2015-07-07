package com.ws.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ws.common.commonUtil;
import com.ws.dao.BlogTypeDao;
import com.ws.po.BlogType;
import com.ws.po.UserCommon;
import com.ws.service.BlogTypeService;
@Component("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService {

	@Resource
	private BlogTypeDao blogTypeDao;
	@Override
	public boolean add(UserCommon userCommon, String name, int fatherId)
			throws Exception {
		BlogType blogType = new BlogType();
		blogType.setUserCommon(userCommon);
		if (name.length()>0) {
			String nameString=commonUtil.trimInnerSpaceStr(name);	
			blogType.setTypename(nameString);
			if (fatherId !=0) {
				BlogType fatherBlogType=blogTypeDao.getBlogTypeById(fatherId);
				if (fatherBlogType != null) {
					blogType.setFather(fatherBlogType);
				}else {
					return false;
				}
			}
			
			return blogTypeDao.insert(blogType);
		}else {
			return false;
		}
	}
   
	@Override
	public List<BlogType> getTypeListByUser(UserCommon userCommon)
			throws Exception {
		return blogTypeDao.getTypeList(userCommon.getId());
	}

	@Override
	public boolean del(int blogTypeId) throws Exception {
		BlogType blogType=blogTypeDao.getBlogTypeById(blogTypeId);
		if (blogType!= null) {
			return blogTypeDao.delete(blogType);	
		}else {
			return false;
		}
	}

	public BlogTypeDao getBlogTypeDao() {
		return blogTypeDao;
	}

	public void setBlogTypeDao(BlogTypeDao blogTypeDao) {
		this.blogTypeDao = blogTypeDao;
	}

	@Override
	public BlogType getTypeById(long typeId) throws Exception {
		// TODO Auto-generated method stub
		return blogTypeDao.getBlogTypeById(typeId);
	}

	@Override
	public List<BlogType> getNextTypeList(long typeId) throws Exception {
		return blogTypeDao.getNextTypeList(typeId);
	}

   

	
}
