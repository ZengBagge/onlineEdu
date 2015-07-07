package com.ws.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.dao.CourseDao;
import com.ws.po.Course;

@Component("courseDao")
public class CourseDaoImpl implements CourseDao {

	@Resource
	private SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getCourseByUid(String uid) throws Exception {
		List<Course> courses=null;
		Session session = null;
		try{
			session =  sessionFactory.getCurrentSession(); 
			org.hibernate.Query q= session.createQuery("from Course c where c.teacherUser.userCommon.uid=?"); 
		     q.setString(0, uid);
		    courses=q.list();
           return courses;
		}catch(Exception e){e.printStackTrace();return null;}
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean addCourse(Course course) throws Exception {
		
		Session session = null;
		try{
			session =  sessionFactory.getCurrentSession(); 
			    session.saveOrUpdate(course);

			 return true;
		}catch(Exception e){e.printStackTrace();return false;}
	}
		
		@Override
		public boolean setCourse(Course course) throws Exception {
			
			Session session = null;
			try{
				session =  sessionFactory.getCurrentSession(); 
				   session.update(course);
				 return true;
			}catch(Exception e){e.printStackTrace();return false;}
	}
	@Override
	public Course getCourseById(int id) throws Exception {
	
		 Session session =null;
		try {
			 session =sessionFactory.getCurrentSession();
           Course course =(Course)session.get(Course.class, id);
			if(course != null)
				return course;
			else {
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	

}
