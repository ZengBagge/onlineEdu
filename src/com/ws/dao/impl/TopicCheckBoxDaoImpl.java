package com.ws.dao.impl;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import com.ws.dao.TopicCheckBoxDao;
import com.ws.po.CheckBoxTopic;

@Component("topicCheckBoxDao")
public class TopicCheckBoxDaoImpl implements TopicCheckBoxDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public boolean insert(CheckBoxTopic checkBoxTopic) throws Exception {
		
		  Session session =null;
		  try {
			session =sessionFactory.getCurrentSession();
			  session.saveOrUpdate(checkBoxTopic);
				  return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		  
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CheckBoxTopic> getList(int offset,int pageSize, int courseId) {
		  List<CheckBoxTopic> lists =null;
		   Session session =null;
		   try {
			session=sessionFactory.getCurrentSession();
			   Query query =session.createQuery("from CheckBoxTopic r where r.topic.course.id=? order by r.topic.sum DESC");
			   query.setInteger(0, courseId);
			   query.setFirstResult(offset);
			   query.setMaxResults(pageSize);
			   lists=query.list();
			   return lists;
		} catch (HibernateException e) {
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
	public int getAllRow(int courseId) throws Exception {
		 Session session =null;
		   try {
			session=sessionFactory.getCurrentSession();
			   Query query =session.createQuery("from CheckBoxTopic  r where r.topic.course.id=?");
			   query.setInteger(0, courseId);  
			   return query.list().size();
			   
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Object getCheckBoxByTopic(long topicId) throws Exception {
		  Session session =null;
		   try {
			session=sessionFactory.getCurrentSession();
			   Query query =session.createQuery("from CheckBoxTopic r where r.topic.id=?");
			   query.setLong(0, topicId);  
			   return (CheckBoxTopic) query.list().get(0);
			   
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CheckBoxTopic getCheckBoxById(long tidInt) throws Exception {
	
		Session session = null;
		try {
			session =sessionFactory.getCurrentSession();
			CheckBoxTopic checkBoxTopic = (CheckBoxTopic) session.get(CheckBoxTopic.class, tidInt);
			return checkBoxTopic;
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
			CheckBoxTopic checkBoxTopic =(CheckBoxTopic) session.get(CheckBoxTopic.class, tid);
			session.delete(checkBoxTopic);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

  }
	
}
