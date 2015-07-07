package com.ws.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.dao.WorkJournalDao;
import com.ws.po.WorkJournal;

@Component("workJOurnalDao")
public class WorkJournalDaoImpl implements WorkJournalDao {
	
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public boolean insert(WorkJournal workJournal) throws Exception {
		
		Session session = null;
		try{
			session =  sessionFactory.getCurrentSession(); 
			    session.save(workJournal);
			 return true;
		}catch(Exception e){e.printStackTrace();return false;}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkJournal> getWorkJournalByCourseAndStudent(int courseId,
			long studentUserId) throws Exception {
		
		List<WorkJournal>workJournals = null;
		Session session = null;
		
		try {
			session =sessionFactory.getCurrentSession();
			Query query=session.createQuery("From WorkJournal w where w.studentUser.id=? and w.work.course.id=?");
			query.setLong(0, studentUserId);
			query.setInteger(1, courseId);
			workJournals=query.list();
			return workJournals;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public boolean getIsWorkJournal(int workId, long studentUserId)
			throws Exception {
	Session session = null;
		
		try {
			session =sessionFactory.getCurrentSession();
			Query query=session.createQuery("From WorkJournal w where w.studentUser.id=? and w.work.id=?");
			query.setLong(0, studentUserId);
			query.setInteger(1, workId);
			if (query.list().size()>0) {
				WorkJournal workJournal =(WorkJournal) query.list().get(0);
				if (workJournal.getIsFinish()==1) {
					return true;
				}
				else {
					return false;
				}
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
