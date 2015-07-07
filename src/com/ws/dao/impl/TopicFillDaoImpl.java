package com.ws.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.common.commonUtil;
import com.ws.dao.TopicFillDao;
import com.ws.po.CheckBoxTopic;
import com.ws.po.FillTopic;

@Component("topicFillDao")
public class TopicFillDaoImpl implements TopicFillDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public boolean inset(FillTopic fillTopic) throws Exception {
		  Session session =null;
		  try {
			  commonUtil.p("添加填空题");
			session =sessionFactory.getCurrentSession();
		       session.saveOrUpdate(fillTopic);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<CheckBoxTopic> getList(int offset, int length, int courseId)
			throws Exception {
		List<CheckBoxTopic> lists =null;
		   Session session =null;
		   try {
			session=sessionFactory.getCurrentSession();
			   Query query =session.createQuery("from FillTopic r where r.topic.course.id=? order by r.topic.sum DESC");
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
			   Query query =session.createQuery("from FillTopic  r where r.topic.course.id=?");
			   query.setInteger(0, courseId);  
			   return query.list().size();
			   
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}


	@Override
	public Object getFillByTopic(long topicId) {
		  Session session =null;
		   try {
			session=sessionFactory.getCurrentSession();
			   Query query =session.createQuery("from FillTopic r where r.topic.id=?");
			   query.setLong(0, topicId);  
			   return (FillTopic) query.list().get(0);
			   
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public FillTopic getFillTopicById(long tidInt) throws Exception {
		Session session = null;
		try {
			session =sessionFactory.getCurrentSession();
			commonUtil.p("日志记录，获取填空题对象");
			FillTopic fillTopic = (FillTopic) session.get(FillTopic.class, tidInt);
			return fillTopic;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public boolean delete(FillTopic fillTopic) throws Exception {
		 Session session =null;
		  try {
			session =sessionFactory.getCurrentSession();
		       session.delete(fillTopic);
				  return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		  
	}
    
	
}
