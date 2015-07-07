package com.ws.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.dao.MajorDao;
import com.ws.po.Major;
@Component("majorDao")
public class MajorDaoImpl implements MajorDao {

	@Resource
	private SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")
	@Override
	public List<Major> getMajors() throws Exception {

		Session session = null;
		List<Major> majors = null;

		try {
			session = sessionFactory.getCurrentSession();
			org.hibernate.Query q = session
					.createQuery("from Major m order by m.majorUid desc");
			majors = q.list();
			return majors;
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
	public Major getMajorByMajorUid(String majorUid) throws Exception {
		
		Session session = null;
		Major major = null;

		try {
			session = sessionFactory.getCurrentSession();
			org.hibernate.Query q = session
					.createQuery("from Major m where m.majorUid=?");
			q.setString(0, majorUid);
			major=(Major) q.uniqueResult();
			  return major;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
		
	}

	@Override
	public Major getMajor(int majorId) throws Exception {
		
		try {
			Session session = sessionFactory.getCurrentSession();
   Major major=	(Major) session.get(Major.class, majorId);
			return major;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
}
