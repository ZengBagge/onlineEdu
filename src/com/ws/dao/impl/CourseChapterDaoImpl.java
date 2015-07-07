package com.ws.dao.impl;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import com.ws.dao.CourseChapterDao;
import com.ws.po.CourseChapter;


@Component("courseChapterDao")
public class CourseChapterDaoImpl implements CourseChapterDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseChapter> getChapters(int id) throws Exception {
		List<CourseChapter> chapters=null;
		Session session = null;
		try{
			session =  sessionFactory.getCurrentSession();
			org.hibernate.Query q= session.createQuery("from CourseChapter c where c.course.id=? order by c.sort ASC"); 
		     q.setInteger(0, id);
		    chapters=q.list();
           return chapters;
		}catch(Exception e){e.printStackTrace();return null;}
	}

	@Override
	public boolean insert(CourseChapter chapter) throws Exception {
		
		Session session = null;
		try{
			session =  sessionFactory.getCurrentSession(); 
			    session.saveOrUpdate(chapter);
			 return true;
		}catch(Exception e){e.printStackTrace();return false;}
	}

	@Override
	public CourseChapter getChapter(int chapterId) throws Exception {
		Session session = null;
		try{
			session =  sessionFactory.getCurrentSession(); 
			return (CourseChapter) session.get(CourseChapter.class, chapterId);
		}catch(Exception e){e.printStackTrace();return null;}
	}

	@Override
	public boolean delete(CourseChapter chapter) throws Exception {
		Session session = null;
		try{
			session =  sessionFactory.getCurrentSession(); 
			    session.delete(chapter);
			 return true;
		}catch(Exception e){e.printStackTrace();return false;}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	


}
