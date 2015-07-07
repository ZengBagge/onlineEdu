package com.ws.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.dao.WorkManageDao;
import com.ws.po.Work;

@Component("workManageDao")
public class WorkManageDaoImpl implements WorkManageDao {

	@Resource
	private SessionFactory sessionFactory;
	@Override
	public boolean insert(Work work) throws Exception {
		
		Session session =null;
		try {
			session =sessionFactory.getCurrentSession();
		    session.saveOrUpdate(work);
		    return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Work> getWorkingByTeacher(int id, Date date) throws Exception {
		
		Session session=null;
		List<Work>works=null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query =session.createQuery("from Work w where w.endDate>? and w.teacherUser.id=? order by w.startDate asc");
			query.setDate(0, date);
			query.setInteger(1, id);
			works=query.list();
			return works;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Work> getWorkedByTeacher(int id, Date date,int offset,int length) throws Exception {
		Session session=null;
		List<Work>works=null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query =session.createQuery("from Work w where w.endDate<? and w.teacherUser.id=? order by w.endDate desc");
			query.setDate(0, date);
			query.setInteger(1, id);
			query.setFirstResult(offset);
			query.setMaxResults(length);
			works=query.list();
			return works;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public int getAllRow(int id) throws Exception {
		Session session=null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query =session.createQuery("from Work w where w.endDate<? and w.teacherUser.id=? order by w.endDate desc");
			Date date = new Date();
			query.setDate(0, date);
			query.setInteger(1, id);
			return query.list().size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Work getWorkById(int workId) throws Exception {
		Session session =null;
		try {
			session =sessionFactory.getCurrentSession();
		    Work work=(Work) session.get(Work.class,workId);
			return work;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(int id) throws Exception {
		Session session =null;
		try {
			session =sessionFactory.getCurrentSession();
			Work work=(Work) session.get(Work.class,id);
		    session.delete(work);
		    return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
