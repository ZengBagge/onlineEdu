package com.ws.dao.impl;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.dao.WorkTopicDao;
import com.ws.po.WorkTopic;

@Component("workTopicServiceImpl")
public class WorkTopicDaoImpl implements WorkTopicDao {

	@Resource
	SessionFactory sessionFactory;
	@Override
	public boolean inset(WorkTopic workTopic) throws Exception {
		
		Session session =null;
		try {
			System.out.print("添加组卷开始");
			session = sessionFactory.getCurrentSession();
           session.saveOrUpdate(workTopic);
				System.out.print("日志记录，添加一个组卷");
				return true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkTopic> getWorkTopicByTeacherUserId(int id) throws Exception {
		
		List<WorkTopic>workTopics=null;
		Session session =null;
		
		try {
			session = sessionFactory.getCurrentSession();
			Query query= session.createQuery("from WorkTopic w  where  w.teacherUser.id=?");
			query.setInteger(0, id);
			workTopics=query.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workTopics;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkTopic> getWorkTopicByCourseId(int course) throws Exception {
		List<WorkTopic>workTopics=null;
		Session session =null;
		
		try {
			session = sessionFactory.getCurrentSession();
			Query query= session.createQuery("from WorkTopic w  where  w.course.id=?");
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
	public WorkTopic getWorkTopicById(int workTopicIdInt) throws Exception {
		
		Session session= null;
		try {
			session = sessionFactory.getCurrentSession();
			WorkTopic workTopic=(WorkTopic) session.get(WorkTopic.class, workTopicIdInt);
			if(workTopic != null )
				return workTopic;
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
	public boolean delete(int id) throws Exception {
		Session session= null;
		try {
			session = sessionFactory.getCurrentSession();
			WorkTopic workTopic=(WorkTopic) session.get(WorkTopic.class, id);
			session.delete(workTopic);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	
}
