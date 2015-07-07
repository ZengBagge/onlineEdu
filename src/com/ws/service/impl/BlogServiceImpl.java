package com.ws.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ws.common.HtmlString;
import com.ws.common.PageBean;
import com.ws.common.commonUtil;
import com.ws.dao.BlogDao;
import com.ws.dao.BlogTagDao;
import com.ws.dao.BlogTypeDao;
import com.ws.po.Blog;
import com.ws.po.BlogTag;
import com.ws.po.BlogType;
import com.ws.po.UserCommon;
import com.ws.service.BlogService;
@Component("blogService")
public class BlogServiceImpl implements BlogService {

	@Resource
	private BlogDao blogDao;
	@Resource
	private BlogTagDao blogTagDao;
	@Resource
	private BlogTypeDao blogTypeDao;
	@Override
	public boolean add(String titString, UserCommon userCommon,
			String shortMessage, String contentString, String[] tags,
			String userIpString, long typeId, String writer, String description,
			String keyword) throws Exception {
		       
		     try {
				Blog blog = new Blog();//创建对象
				    BlogType blogType = blogTypeDao.getBlogTypeById(typeId);
				    if(blogType == null)
				    {
				    	commonUtil.p("日志记录，获取博文类型失败");
				    	return false;
				    }	
				    blog.setBlogType(blogType);
				 blog.setUserCommon(userCommon);
				 blog.setPublishDate(new Date());
				 blog.setTitleString(commonUtil.trimInnerSpaceStr(titString));
				 if (writer.equals(" ")) {
					blog.setWriter(userCommon.getTname());
				}else {
					blog.setWriter(commonUtil.trimInnerSpaceStr(writer));
				}
				if (shortMessage == null||shortMessage.equals("")) {
					commonUtil.p("系统创建简介");
					String mString=HtmlString.filterHtml(contentString).substring(0, 210);
				   blog.setShortMessage(mString);
				   if(description == null||description.equals(""))
				    blog.setDescription(mString);
				}else {
					blog.setShortMessage(shortMessage);
					blog.setDescription(shortMessage);
				}
				blog.setContentString(contentString);
				blog.setUserIpString(userIpString);
				Set<BlogTag>blogTags=new HashSet<BlogTag>();
				StringBuffer key = new StringBuffer();
				for (int i = 0; i < tags.length; i++) {
					String name = commonUtil.trimInnerSpaceStr(tags[i]);
					key.append(name+",");
					BlogTag blogTag=blogTagDao.getBlogTagByName(name);
					if (blogTag!=null) {
						blogTags.add(blogTag);
						blogTagDao.addNumber(blogTag.getId());//数据加1
					}else {
						BlogTag blogTag2=new BlogTag();
						blogTag2.setName(name);
						blogTag2.setAddTime(new Date());
						blogTag2.setNumber(1);
						 if(blogTagDao.insert(blogTag2))
						  blogTags.add(blogTag2);
					}
				}
         blog.setTags(blogTags);
         blog.setKeywords(key.toString());
         if (userCommon.getRule()>2) {
				blog.setIsIndex(1);
				blog.setHost(201);
}
         return blogDao.insert(blog);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
	}

	@Override
	public boolean update(long tid, String titString, UserCommon userCommon,
			String shortMessage, String contentString, String[] tags,
			String userIpString, long typeId, String writer, String description,
			String keyword) throws Exception {
		
		 try {
			Blog blog = blogDao.getBlogById(tid);
			 if (blog !=null) {	
									 BlogType blogType = blogTypeDao.getBlogTypeById(typeId);
									 if(blogType == null)
									 	return false;
									 blog.setBlogType(blogType);
						     blog.setUserCommon(userCommon);
						     blog.setPublishDate(new Date());
						     blog.setTitleString(commonUtil.trimInnerSpaceStr(titString));
						     if (writer.equals(" ")) {
										blog.setWriter(userCommon.getTname());
									}else {
										blog.setWriter(commonUtil.trimInnerSpaceStr(writer));
									}
						    if (shortMessage.equals(" ")) {
									String mString=HtmlString.filterHtml(contentString).substring(0, 210);
									   blog.setShortMessage(mString);
									   blog.setDescription(mString);
									}else {
										blog.setShortMessage(shortMessage);
										blog.setDescription(shortMessage);
									}
						    blog.setContentString(contentString);
						    blog.setUserIpString(userIpString);
						    Set<BlogTag>blogTags=new HashSet<BlogTag>();
						    StringBuffer key = new StringBuffer();
						    for (int i = 0; i < tags.length; i++) {
										String name = commonUtil.trimInnerSpaceStr(tags[i]);
										key.append(name+",");
										BlogTag blogTag=blogTagDao.getBlogTagByName(name);
										if (blogTag!=null) {
											blogTags.add(blogTag);
										}else {
											BlogTag blogTag2=new BlogTag();
											blogTag2.setName(name);
											blogTag2.setAddTime(new Date());
											blogTag2.setNumber(1);
											 if(blogTagDao.insert(blogTag2))
											  blogTags.add(blogTag2);
										}
									}
						   blog.setTags(blogTags);
						   blog.setKeywords(key.toString());
						   if (userCommon.getRule()>2) {
									blog.setIsIndex(1);
									blog.setHost(201);
						}
						   return blogDao.insert(blog);
				}
			 else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public PageBean getIndexBlogs(int page, int pageSize) throws Exception {

		try {
			int allRow=blogDao.getIndexNumber();  //获取总行数
			int totalPage=PageBean.countTotalPage(pageSize, allRow);  //设置总页数
			final int offset = PageBean.countOffset(pageSize, page);//当前页开始记录
            final int length = pageSize;//每页记录数
            final int currentPage = PageBean.countCurrentPage(page);
            List<Blog> blogs =blogDao.getIndexList(offset, length);
            PageBean pageBean = new PageBean();
			pageBean.setPageSize(pageSize);
			pageBean.setCurrentPage(currentPage);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(blogs);
			pageBean.init();
			return pageBean;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PageBean getListByType(int page, int pageSize, long typeId)
			throws Exception {
		try {
			int allRow=blogDao.getTypeNumber(typeId);  //获取总行数
			int totalPage=PageBean.countTotalPage(pageSize, allRow);  //设置总页数
			final int offset = PageBean.countOffset(pageSize, page);//当前页开始记录
            final int length = pageSize;//每页记录数
            final int currentPage = PageBean.countCurrentPage(page);
            List<Blog> blogs =blogDao.getListByType(offset, length, typeId);
            PageBean pageBean = new PageBean();
			pageBean.setPageSize(pageSize);
			pageBean.setCurrentPage(currentPage);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(blogs);
			pageBean.init();
			return pageBean;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void addHost(long blogId) throws Exception {
		// TODO Auto-generated method stub
         blogDao.addHost(blogId);
	}

	@Override
	public boolean setIndex(long blogId) throws Exception {
		// TODO Auto-generated method stub
		return blogDao.setIndex(blogId);
	}

	@Override
	public Blog getBlogById(long blogId) throws Exception {
		// TODO Auto-generated method stub
		return blogDao.getBlogById(blogId);
	}

	public BlogDao getBlogDao() {
		return blogDao;
	}

	public void setBlogDao(BlogDao blogDao) {
		this.blogDao = blogDao;
	}

	public BlogTagDao getBlogTagDao() {
		return blogTagDao;
	}

	public void setBlogTagDao(BlogTagDao blogTagDao) {
		this.blogTagDao = blogTagDao;
	}

	@Override
	public PageBean getListByTag(int page, int pageSize, int tagId)
			throws Exception {
		try {
			int allRow=blogDao.getTagNumber(tagId);  //获取总行数
			int totalPage=PageBean.countTotalPage(pageSize, allRow);  //设置总页数
			final int offset = PageBean.countOffset(pageSize, page);//当前页开始记录
            final int length = pageSize;//每页记录数
            final int currentPage = PageBean.countCurrentPage(page);
            List<Blog> blogs =blogDao.getListByTag(offset, length, tagId);
            PageBean pageBean = new PageBean();
			pageBean.setPageSize(pageSize);
			pageBean.setCurrentPage(currentPage);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(blogs);
			pageBean.init();
			return pageBean;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PageBean getUserIndexBlogs(Long id, int page, int pageSize) {
		try {
			int allRow=blogDao.getUserIndexNumber(id);  //获取总行数
			int totalPage=PageBean.countTotalPage(pageSize, allRow);  //设置总页数
			final int offset = PageBean.countOffset(pageSize, page);//当前页开始记录
            final int length = pageSize;//每页记录数
            final int currentPage = PageBean.countCurrentPage(page);
            List<Blog> blogs =blogDao.getUserIndexList(id,offset, length);
            commonUtil.p("本页查询到博文"+blogs.size()+"篇");
            PageBean pageBean = new PageBean();
			pageBean.setPageSize(pageSize);
			pageBean.setCurrentPage(currentPage);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(blogs);
			pageBean.init();
			return pageBean;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
}
