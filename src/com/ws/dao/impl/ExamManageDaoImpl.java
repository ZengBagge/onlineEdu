package com.ws.dao.impl;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.dao.ExamManageDao;
import com.ws.po.Exam;

@Component("examManageDao")
public class ExamManageDaoImpl implements ExamManageDao{

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public boolean insert(Exam exam) throws Exception {
		Session session =null;
		try {
			session =sessionFactory.getCurrentSession();
			session.saveOrUpdate(exam);
		return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exam> getExamingByTeacher(int id, Date date) throws Exception {
		Session session=null;
		List<Exam>exams=null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query =session.createQuery("from Exam w where w.endDate>? and w.teacherUser.id=? order by w.startDate asc");
			query.setDate(0, date);
			query.setInteger(1, id);
			exams=query.list();
			return exams;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exam> getExamedByTeacher(int id, Date date,int offset,int length) throws Exception {
		Session session=null;
		List<Exam>exams=null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query =session.createQuery("from Exam w where w.endDate<? and w.teacherUser.id=? order by w.startDate asc");
			query.setDate(0, date);
			query.setInteger(1, id);
			query.setFirstResult(offset);
			query.setMaxResults(length);
			exams=query.list();
			return exams;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public int getAllRow(int id) throws Exception {
		// TODO Auto-generated method stub
		Session session=null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query =session.createQuery("from Exam w where w.endDate<? and w.teacherUser.id=? order by w.startDate asc");
			query.setDate(0, new Date());
			query.setInteger(1, id);
			return query.list().size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Exam getExamById(int examId) throws Exception {
		Session session =null;
		try {
			session =sessionFactory.getCurrentSession();
			Exam exam=(Exam) session.get(Exam.class, examId);
	  	return exam;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(int id) throws Exception {
		// TODO Auto-generated method stub
		Session session =null;
		try {
			session =sessionFactory.getCurrentSession();
			Exam exam=(Exam) session.get(Exam.class, id);
			session.delete(exam);
		return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}


}
