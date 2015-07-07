package com.ws.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.dao.TopicRedioDao;
import com.ws.po.RedioTopic;

@Component("topicRedioDao")
public class TopicRedioDaoImpl implements TopicRedioDao {

	@Resource
	private SessionFactory sessionFactory;
	@Override
	public boolean insertTopic(RedioTopic redioTopic) throws Exception {
		Session session =null;
		try {
			session=sessionFactory.getCurrentSession();
			 session.saveOrUpdate(redioTopic);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RedioTopic> getList(int offset,int pagesize, int courseId) {
	   List<RedioTopic> lists =null;
	   Session session =null;
	   try {
		session=sessionFactory.getCurrentSession();
		   Query query =session.createQuery("from RedioTopic r where r.topic.course.id=? order by r.topic.sum DESC");
		   query.setInteger(0, courseId);
		   query.setFirstResult(offset);
		   query.setMaxResults(pagesize);
		   lists=query.list();
		   return lists;
	} catch (HibernateException e) {
		e.printStackTrace();
		return null;
	}
	}

	/**
	 * 获取总行数
	 */
	@Override
	public int getAllRow(int courseId) throws Exception {
		   Session session =null;
		   try {
			session=sessionFactory.getCurrentSession();
			   Query query =session.createQuery("from RedioTopic r where r.topic.course.id=?");
			   query.setInteger(0, courseId);  
			   return query.list().size();
			   
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public RedioTopic getRedioByTopic(long topicId) throws Exception {
		
		  Session session =null;
		   try {
			session=sessionFactory.getCurrentSession();
			   Query query =session.createQuery("from RedioTopic r where r.topic.id=?");
			   query.setLong(0, topicId);  
			   return (RedioTopic) query.list().get(0);
			   
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public RedioTopic getRedioTopicById(long tid) throws Exception {
		
		Session session =null;
		
		try {
			session =sessionFactory.getCurrentSession();
		   RedioTopic redioTopic = (RedioTopic) session.get(RedioTopic.class, tid);	
		   return redioTopic;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(long tid) throws Exception {
		
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			RedioTopic redioTopic =(RedioTopic) session.get(RedioTopic.class, tid);
			session.delete(redioTopic);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

  }
}	
