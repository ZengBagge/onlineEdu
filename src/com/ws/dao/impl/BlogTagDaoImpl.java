package com.ws.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.common.commonUtil;
import com.ws.dao.BlogTagDao;
import com.ws.po.BlogTag;
@Component("blogTagDao")
public class BlogTagDaoImpl implements BlogTagDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public boolean insert(BlogTag blogTag) throws Exception {
		 Session session =null;
		   try {
			   commonUtil.p("日志记录，添加标签"+blogTag.getName());
			session = sessionFactory.getCurrentSession();
			   session.saveOrUpdate(blogTag);
			   return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<BlogTag> getIndexTags() throws Exception {
		Session session =null;
		   try {
			session = sessionFactory.getCurrentSession();
		    Query q = session.createQuery("From BlogTag b order by b.number DESC");
		    q.setFirstResult(0);
		    q.setMaxResults(20);
		    return q.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void addNumber(long blogTagId) throws Exception {
		 Session session =null;
		   try {
			session = sessionFactory.getCurrentSession();
            BlogTag blogTag = (BlogTag) session.get(BlogTag.class, blogTagId);
            blogTag.setNumber(blogTag.getNumber()+1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean isExist(String name) throws Exception {
		Session session =null;
		   try {
			session = sessionFactory.getCurrentSession();
		    Query q = session.createQuery("From BlogTag b where b.name=?");
		    q.setString(0, name);
		     if (q.list().size()>0) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	public BlogTag getBlogTagByName(String name) throws Exception {
		Session session =null;
		   try {
			session = sessionFactory.getCurrentSession();
		    Query q = session.createQuery("From BlogTag b where b.name=?");
		    q.setString(0, name);
		    if(q.list().size()>0)
             return (BlogTag) q.list().get(0);
		    else {
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public BlogTag getBlogTagById(long blogTagId) throws Exception {
		Session session =null;
		   try {
			session = sessionFactory.getCurrentSession();
			BlogTag b = (BlogTag) session.get(BlogTag.class, blogTagId);
		    return b;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
}
