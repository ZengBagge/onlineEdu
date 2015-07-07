package com.ws.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.dao.CollectTopicDao;
import com.ws.po.CollectTopic;

@Component("collectTopicDao")
public class CollectTopicDaoImpl implements CollectTopicDao {

	@Resource
	private SessionFactory sessionFactory;
	@Override
	public boolean insert(CollectTopic collectTopic) throws Exception {
	
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			long integer= (Long) session.save(collectTopic);
			if (integer != 0) {
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

	@Override
	public boolean isCollected(long tid, long id) throws Exception {
	
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("From CollectTopic c where c.studentUser.id=? and c.topic.id=?");
			query.setLong(0, id);
			query.setLong(1, tid);
			if (query.list().size()>0) {
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

	
}
