package com.ws.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.common.commonUtil;
import com.ws.dao.BlogTypeDao;
import com.ws.po.BlogType;
@Component("blogTypeDao")
public class BlogTypeDaoImpl implements BlogTypeDao {

	@Resource
  private SessionFactory sessionFactory;
  

	@Override
	public boolean insert(com.ws.po.BlogType blogType) throws Exception {
		Session session =null;
		   try {
			   commonUtil.p("日志记录，添加分类"+blogType.getTypename());
			session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(blogType);
			   return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(com.ws.po.BlogType blogType) throws Exception {
		Session session =null;
		   try {
			session = sessionFactory.getCurrentSession();
			   session.delete(blogType);
			   return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<com.ws.po.BlogType> getTypeList(long userCommon)
			throws Exception {
		Session session =null;
		   try {
			session = sessionFactory.getCurrentSession();
		    Query q = session.createQuery("From BlogType b where b.userCommon.id=? order by b.sortrank ASC");
		    q.setLong(0, userCommon);
		    return q.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public BlogType getBlogTypeById(long blogTypeId) throws Exception {
		Session session =null;
		   try {
			session = sessionFactory.getCurrentSession();
			BlogType b = (BlogType) session.get(BlogType.class, blogTypeId);
		    return b;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BlogType> getNextTypeList(long typeId) throws Exception {
		Session session =null;
		   try {
			session = sessionFactory.getCurrentSession();
		    Query q = session.createQuery("From BlogType b where b.father.id=? order by b.sortrank ASC");
		    q.setLong(0, typeId);
		    return q.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
}
