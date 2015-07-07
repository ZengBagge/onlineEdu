package com.ws.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.dao.TopicJudgeDao;
import com.ws.po.JudgeTopic;

@Component("topicJudgeDao")
public class TopicJudgeDaoImpl implements TopicJudgeDao {

	@Resource
	private SessionFactory sessionFactory;
	@Override
	public boolean insert(JudgeTopic judgeTopic) throws Exception {
		  Session session =null;
		  try {
			session =sessionFactory.getCurrentSession();
			  session.saveOrUpdate(judgeTopic);
				  return true;
		} catch (Exception e) {
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

	@SuppressWarnings("unchecked")
	@Override
	public List<JudgeTopic> getList(int offset, int length, int courseId)
			throws Exception {
		 List<JudgeTopic> lists =null;
		   Session session =null;
		   try {
			session=sessionFactory.getCurrentSession();
			   Query query =session.createQuery("from JudgeTopic r where r.topic.course.id=? order by r.topic.sum DESC");
			   query.setInteger(0, courseId);
			   query.setFirstResult(offset);
			   query.setMaxResults(length);
			   lists=query.list();
			   return lists;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getAllRow(int courseId) throws Exception {
		 Session session =null;
		   try {
			session=sessionFactory.getCurrentSession();
			   Query query =session.createQuery("from JudgeTopic r where r.topic.course.id=?");
			   query.setInteger(0, courseId);  
			   return query.list().size();
			   
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}


	@Override
	public Object getJudgeByTopic(long topicId) throws Exception {
		  Session session =null;
		   try {
			session=sessionFactory.getCurrentSession();
			   Query query =session.createQuery("from JudgeTopic r where r.topic.id=?");
			   query.setLong(0, topicId);  
			   return (JudgeTopic) query.list().get(0);
			   
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public JudgeTopic getJudgeById(long tidInt) throws Exception {
	
		Session session = null;
		try {
			session =sessionFactory.getCurrentSession();
			JudgeTopic judgeTopic = (JudgeTopic) session.get(JudgeTopic.class, tidInt);
			return judgeTopic;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public boolean delete(JudgeTopic judgeTopic) throws Exception {
		
		  Session session =null;
		  try {
			session =sessionFactory.getCurrentSession();
			  session.delete(judgeTopic);
				  return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
    
}
