package com.ws.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.dao.ExamTopicDao;
import com.ws.po.ExamTopic;

@Component("examTopicDao")
public class ExamTopicDaoImpl implements ExamTopicDao {

	@Resource
	private SessionFactory sessionFactory;
	@Override
	public ExamTopic insert(ExamTopic examTopic) throws Exception {
		
		Session session =null;
		try {
			session = sessionFactory.getCurrentSession();
			Integer id =(Integer) session.save(examTopic);
			ExamTopic examTopic2 = (ExamTopic) session.get(ExamTopic.class, id);
			return examTopic2;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamTopic> getExamTopicList(int id) throws Exception {

		List<ExamTopic>workTopics=null;
		Session session =null;
		
		try {
			session = sessionFactory.getCurrentSession();
			Query query= session.createQuery("from ExamTopic w  where  w.teacherUser.id=?");
			query.setInteger(0, id);
			workTopics=query.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workTopics;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamTopic> getExamTopicByCourseId(int course) throws Exception {
		
		List<ExamTopic>workTopics=null;
		Session session =null;
		
		try {
			session = sessionFactory.getCurrentSession();
			Query query= session.createQuery("from ExamTopic  w  where  w.course.id=?");
			query.setInteger(0, course);
			workTopics=query.list();
			return workTopics;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ExamTopic  getExamTopicById(int workTopicIdInt) throws Exception {
		
		Session session= null;
		try {
			session = sessionFactory.getCurrentSession();
			ExamTopic examTopic=(ExamTopic) session.get(ExamTopic.class, workTopicIdInt);
			if(examTopic != null )
				return examTopic;
			else {
				return null;
			}
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
	public boolean delete(int id) throws Exception {
		Session session= null;
		try {
			session = sessionFactory.getCurrentSession();
			ExamTopic examTopic=(ExamTopic) session.get(ExamTopic.class, id);
           session.delete(examTopic);
           return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	
}
