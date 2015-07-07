package com.ws.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.common.commonUtil;
import com.ws.dao.ExamDao;
import com.ws.po.Exam;

@Component("examDao")
public class ExamDaoImpl implements ExamDao {

	@Resource
	private SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")
	@Override
	public List<Exam> getExaming(int courseId, Date date) throws Exception {
		Session session=null;
		List<Exam>exams=null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query =session.createQuery("from Exam w where w.endDate>? and w.course.id=? order by w.startDate asc");
			query.setDate(0, date);
			query.setInteger(1, courseId);
			exams=query.list();
			return exams;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exam> getExamed(int courseId, Date date) throws Exception {
		Session session=null;
		List<Exam>exams=null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query =session.createQuery("from Exam w where w.endDate<? and w.course.id=? order by w.startDate asc");
			query.setDate(0, date);
			query.setInteger(1, courseId);
			exams=query.list();
			return exams;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Exam getExamById(int examId) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Exam exam=(Exam) session.get(Exam.class, examId);
			return exam;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void setWan(Exam exam) throws Exception {
		
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Exam exam2=(Exam) session.get(Exam.class, exam.getId());
			exam2.setWan(exam2.getWan()+1);
			commonUtil.p("任务+"+exam2.getTitleString()+"完成人数加一");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	

}
