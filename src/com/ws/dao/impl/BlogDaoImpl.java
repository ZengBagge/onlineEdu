package com.ws.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.common.commonUtil;
import com.ws.dao.BlogDao;
import com.ws.po.Blog;

@Component("blogDao")
public class BlogDaoImpl implements BlogDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public boolean insert(Blog blog) throws Exception {
		 Session session =null;
		   try {
			   commonUtil.p("日志记录，添加博文"+blog.getTitleString());
			   session = sessionFactory.getCurrentSession();
			   session.saveOrUpdate(blog);
			   return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> getIndexList(int offset,int length) throws Exception {
		Session session =null;
		   try {
			session = sessionFactory.getCurrentSession();
		    Query q = session.createQuery("From Blog b where b.isIndex=1 order by b.host DESC ,b.publishDate Desc");
		    q.setFirstResult(offset);
		    q.setMaxResults(length);
		    return q.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(Blog blog) throws Exception {
		 Session session =null;
		   try {
			session = sessionFactory.getCurrentSession();
			   session.delete(blog);
			   return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> getListByType(int offset,int length,long typeId) throws Exception {
		Session session =null;
		   try {
			session = sessionFactory.getCurrentSession();
		    Query q = session.createQuery("From Blog b where b.blogType.id=? order by b.host desc ,b.publishDate Desc");
		    q.setFirstResult(offset);
		    q.setMaxResults(length);
		    q.setLong(0, typeId);
		    return q.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void addHost(long blogId) throws Exception {
		Session session =null;
		   try {
			session = sessionFactory.getCurrentSession();
		    Blog b = (Blog) session.get(Blog.class, blogId);
		    b.setHost(b.getHost()+1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean setIndex(long blogId) throws Exception {
		 Session session =null;
		   try {
			   
			session = sessionFactory.getCurrentSession();
		    Blog b = (Blog) session.get(Blog.class, blogId);
		    b.setIsIndex(1);
		    session.getTransaction().commit(); 
		    return true;
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
	public Blog getBlogById(long blogId) throws Exception {
		Session session =null;
		   try {
			session = sessionFactory.getCurrentSession();
		    Blog b = (Blog) session.get(Blog.class, blogId);
		    return b;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getIndexNumber() throws Exception {
		Session session=null;
		 try {
				session = sessionFactory.getCurrentSession();
			    Query q = session.createQuery("From Blog b where b.isIndex=1 order by b.host DESC , b.publishDate Desc");
			    return q.list().size();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
	}

	@Override
	public int getTypeNumber(long typeId) throws Exception {
		Session session =null;
		   try {
			session = sessionFactory.getCurrentSession();
		    Query q = session.createQuery("From Blog b where b.blogType.id=? order by b.host desc , b.publishDate Desc");
		    q.setLong(0, typeId);
		    return q.list().size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getTagNumber(int tagId) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Blog> getListByTag(int offset, int length, int tagId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getUserIndexNumber(Long id) throws Exception {
		Session session=null;
		 try {
				session = sessionFactory.getCurrentSession();
			    Query q = session.createQuery("From Blog b where b.isIndex=1 and b.userCommon.id=? order by b.host DESC ,b.publishDate Desc");
			    q.setLong(0, id);
			    return q.list().size();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> getUserIndexList(Long id, int offset, int length) {
		Session session=null;
		 try {
				session = sessionFactory.getCurrentSession();
			    Query q = session.createQuery("From Blog b where b.isIndex=1 and b.userCommon.id=? order by b.host DESC ,b.publishDate Desc");
			    q.setLong(0, id);
			    return q.list();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}

	
}
