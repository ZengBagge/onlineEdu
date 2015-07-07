package com.ws.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.common.commonUtil;
import com.ws.dao.ExamJournalDao;
import com.ws.po.ExamJournal;

@Component("examJOurnalDao")
public class ExamJournalDaoImpl implements ExamJournalDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExamJournal> getExamJournalByCourseAndStudent(int courseId,
			Long id) throws Exception {
		List<ExamJournal>workJournals = null;
		Session session = null;
		
		try {
			session =sessionFactory.getCurrentSession();
			Query query=session.createQuery("From ExamJournal w where w.studentUser.id=? and w.exam.course.id=?");
			query.setLong(0, id);
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
	public boolean insert(ExamJournal examJournal) throws Exception {
		Session session = null;
		try{
			session =  sessionFactory.getCurrentSession(); 
			    session.save(examJournal);
			 return true;
		}catch(Exception e){e.printStackTrace();return false;}
	}

	@Override
	public Boolean getIsExamJournal(int examId, Long id) throws Exception {
Session session = null;
		commonUtil.p("验证是否已经完成考试");
		try {
			session =sessionFactory.getCurrentSession();
			Query query=session.createQuery("From ExamJournal w where w.studentUser.id=? and w.exam.id=?");
			query.setLong(0, id);
			query.setInteger(1, examId);
			if (query.list().size()>0) {
				ExamJournal workJournal =(ExamJournal) query.list().get(0);
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
