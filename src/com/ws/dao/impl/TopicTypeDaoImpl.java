package com.ws.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.dao.TopicTypeDao;
import com.ws.po.TopicType;

@Component("topicTypeDao")
public class TopicTypeDaoImpl implements TopicTypeDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TopicType> getTopicTypeList(int id) throws Exception {
		Session session = null;
		try {
			session =sessionFactory.getCurrentSession();
			Query q = session.createQuery("From TopicType t where t.teacher.id=? order by t.sort DESC");
			q.setInteger(0, id);
			return q.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean insert(TopicType topicType) throws Exception {
		
		Session session = null;
		try {
			session =sessionFactory.getCurrentSession();
			session.saveOrUpdate(topicType);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public TopicType getTopicType(int tid) throws Exception {
		Session session = null;
		try {
			session =sessionFactory.getCurrentSession();
			return (TopicType) session.get(TopicType.class, tid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(TopicType topicType) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session =sessionFactory.getCurrentSession();
			session.delete(topicType);
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

}
